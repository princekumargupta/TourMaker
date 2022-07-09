// License: GPL. For details, see LICENSE file.
package TourMaker.util;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Kishan Tripathi
 */
public class Resource {

  private static final HashMap<String, BufferedImage> images = new HashMap();

  private static final HashMap<String, FlatSVGIcon> icons = new HashMap();

  private static final HashMap<String, File> files = new HashMap();

  /**
   * Private no-args constructor for ensuring against instantiation.
   */
  private Resource() {
  }

  public static File getFile(String name) {
    if (files.containsKey(name)) {
      return files.get(name);
    }
    File file = IOUtil.getFileFromResourceAsFile(name);
    files.put(name, file);
    return file;
  }

  public static BufferedImage getImage(String name) {
    if (images.containsKey(name)) {
      return images.get(name);
    }
    File file = getFile(name);
    BufferedImage img = null;
    try {
      img = ImageIO.read(file);
    } catch (IOException ex) {
      Logger.getLogger(Resource.class.getName()).log(Level.SEVERE, null, ex);
    }
    images.put(name, img);
    return img;
  }

  /**
   * Create icon from svg file.
   * @param name
   * @return
   */
  public static FlatSVGIcon getIcon(String name) {
    if (icons.containsKey(name)) {
      return icons.get(name);
    }
    File file = getFile(name);
    FlatSVGIcon flatSVGIcon = new FlatSVGIcon(file);
    icons.put(name, flatSVGIcon);
    return flatSVGIcon;
  }
}
