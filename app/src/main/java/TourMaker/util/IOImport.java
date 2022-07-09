// License: GPL. For details, see LICENSE file.
package TourMaker.util;

import TourMaker.AppState;
import TourMaker.data.AssetType;
import TourMaker.data.Point;
import TourMaker.data.Project;
import TourMaker.data.Sequence;
import TourMaker.gui.MainScreen;
import TourMaker.hotspot.Hotspot;
import TourMaker.hotspot.HotspotType;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Kishan Tripathi
 */
public class IOImport {

  public static File copyFile(File orignal, AssetType type) {
    String projectDir = AppState.getCurrentProject().dir();
    String assetDir = projectDir.concat("/").concat(type.name());
    String fileName = orignal.getName();
    String fileNameWithOutExt = FilenameUtils.removeExtension(fileName);
    String fileExt = FilenameUtils.getExtension(fileName);
    int index = 0;
    File destination = new File(assetDir.concat("/").concat(fileName));
    while (destination.exists()) {
      destination = new File(assetDir.concat("/").concat(fileNameWithOutExt).concat(index + ".").concat(fileExt));
      index++;
    }
    try {
      FileUtils.copyFile(orignal, destination);
      return destination;
    } catch (IOException e) {
      String message = "Error copying file : " + orignal.getAbsolutePath();
      JOptionPane.showMessageDialog(MainScreen.getInstance(), message, "IO Error",
      JOptionPane.ERROR_MESSAGE);
    }
    return null;
  }

  public static Project openProject(File projectFile) {
    Project project = null;
    String absoluteFile = projectFile.getAbsolutePath();
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    Logger.getLogger(IOImport.class.getName()).log(Level.INFO, "Opening File : {0}", absoluteFile);
    try {
      dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

      DocumentBuilder db = dbf.newDocumentBuilder();

      Document doc = db.parse(projectFile);

      doc.getDocumentElement().normalize();

      Element tour = doc.getDocumentElement();

      String description = "Virtual Tour";
      NodeList nameList = tour.getElementsByTagName("Name");
      if (nameList.getLength() > 0) {
        description = nameList.item(0).getTextContent();
      } else {
        Logger.getLogger(IOImport.class.getName()).log(Level.SEVERE, "Tour name not found : {0}", absoluteFile);
      }
      String dir = projectFile.getParent();
      project = new Project(dir, description);

      NodeList sequenceList = tour.getElementsByTagName("Sequence");
      for (int i = 0; i < sequenceList.getLength(); i++) {
        Node seqItem = sequenceList.item(i);
        if (seqItem.getNodeType() == Node.ELEMENT_NODE) {
          Sequence seq;
          Element seqElement;
          String seqId, seqName;
          seqElement = (Element) seqItem;
          seqId = seqElement.getAttribute("id");
          seqName = "Sequence " + i;
          if (!seqElement.getAttribute("name").isEmpty()) {
            seqName = seqElement.getAttribute("name");
          }
          seq = new Sequence(seqName);
          NodeList nodeList = seqElement.getElementsByTagName("Node");
          for (int j = 0; j < nodeList.getLength(); j++) {
            Node nodeItem = nodeList.item(j);
            if (nodeItem.getNodeType() == Node.ELEMENT_NODE) {
              TourMaker.data.Node node;
              String nodeName, nodeId, nodeImageName;
              double xCoord = 0, yCoord = 0;
              Element nodeElement = (Element) nodeItem;
              nodeId = nodeElement.getAttribute("id");
              nodeName = nodeElement.getAttribute("name");
              nodeImageName = nodeElement.getAttribute("imageName");
              NodeList positionList = nodeElement.getElementsByTagName("Position");
              if (positionList.getLength() > 0) {
                Node postionNode = positionList.item(0);
                if (postionNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element positionElement = (Element) postionNode;
                  String xString = positionElement.getAttribute("xCoordinate");
                  xCoord = Double.parseDouble(xString);
                  String yString = positionElement.getAttribute("yCoordinate");
                  yCoord = Double.parseDouble(yString);
                }
              }
              node = new TourMaker.data.Node(new Point(xCoord, yCoord), nodeName, nodeImageName);

              NodeList hotspotList = nodeElement.getElementsByTagName("Hotspot");
              for (int k = 0; k < hotspotList.getLength(); k++) {
                Node hotspotItem = hotspotList.item(k);
                if (hotspotItem.getNodeType() == Node.ELEMENT_NODE) {
                  Hotspot hotspot;
                  double azimuthal, polar;
                  String type, value;
                  Element hotspotElement = (Element) hotspotItem;
                  azimuthal = Double.parseDouble(hotspotElement.getAttribute("azimuthal"));
                  polar = Double.parseDouble(hotspotElement.getAttribute("polar"));
                  type = hotspotElement.getAttribute("type");
                  value = hotspotElement.getAttribute("value");
                  hotspot = new Hotspot(azimuthal, polar, HotspotType.valueOf(type), value);
                  node.addHotspot(hotspot);
                }
              }
              seq.addNode(node);
            }
          }
          project.addSequence(seq);
        }
      }
    } catch (ParserConfigurationException | SAXException | IOException ex) {
      Logger.getLogger(IOImport.class.getName()).log(Level.SEVERE, null, ex);
    }
    return project;
  }
}
