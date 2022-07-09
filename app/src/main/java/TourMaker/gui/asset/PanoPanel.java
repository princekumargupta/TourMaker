// License: GPL. For details, see LICENSE file.
package TourMaker.gui.asset;

import TourMaker.data.AssetType;
import TourMaker.gui.MainScreen;
import TourMaker.util.AssetUtil;
import TourMaker.util.IOImport;
import TourMaker.util.Utils;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import net.coobird.thumbnailator.Thumbnails;

/**
 *
 * @author Kishan Tripathi
 */
public class PanoPanel extends AssetPanel {

  private ImageIcon thumbnail = new ImageIcon("Thumbnail");

  public PanoPanel() {
    addNewCard();
  }

  @Override
  public AssetType assetType() {
    return AssetType.Panorama;
  }

  private AssetCard addAssetCard() {
    ImageCard card = new ImageCard();
    int count = this.getComponentCount();
    add(card, Math.min(0, count - 1));
    revalidate();
    return card;
  }

  private NewCard addNewCard() {
    NewCard newCard = new NewCard();
    add(newCard);
    revalidate();
    return newCard;
  }

  private void deleteAssetCard(AssetCard assetCard) {
    remove(assetCard);
    revalidate();
  }

  @Override
  public void addAsset(File asset) {
    AssetCard card = addAssetCard();
    card.setAsset(asset);
  }

  @Override
  public void addAsset() {
    List<FileFilter> filterList = Utils.getFilterList(assetType());
    JFileChooser jfc = new JFileChooser();
    jfc.setAcceptAllFileFilterUsed(false);
    jfc.setMultiSelectionEnabled(false);
    for (FileFilter filter : filterList) {
      jfc.setFileFilter(filter);
    }
    int response = jfc.showDialog(this, "Import Images");
    if (response == JFileChooser.APPROVE_OPTION) {
      File imageFile = jfc.getSelectedFile();
      File copiedFile = IOImport.copyFile(imageFile, assetType());
      if (copiedFile != null) {
        addAsset(copiedFile);
      }
    }
  }

  private class ImageCard extends AssetCard {

    @Override
    public void deleteAsset() {
      deleteAssetCard(this);
      AssetUtil.deleteAsset(asset);
    }

    @Override
    public void viewAsset() {
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int width = (int) (screenSize.getWidth() * 0.7);
      int hieght = (int) (screenSize.getWidth() * 0.7);
      try {
        BufferedImage temp = Thumbnails.of(asset).size(width, hieght).asBufferedImage();
        JDialog dialog = new JDialog(MainScreen.getInstance(), true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setTitle(asset.getName());
        dialog.add(new JLabel(new ImageIcon(temp)));
        dialog.pack();
        dialog.setLocationByPlatform(true);
        dialog.setVisible(true);
      } catch (IOException ex) {
        String message = "Error creating thumbnail : " + asset.getAbsolutePath();
        JOptionPane.showMessageDialog(MainScreen.getInstance(), message, "IO Error",
        JOptionPane.ERROR_MESSAGE);
        Logger.getLogger(PanoPanel.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    @Override
    protected void showAssetGUI() {
      removeAll();
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();


      gbc.anchor = GridBagConstraints.CENTER;
      
      gbc.weightx = 1;
      gbc.weighty = 1;
      gbc.gridwidth = 2;
      JLabel nameLabel = new JLabel(asset.getName());
      add(nameLabel, gbc);

      gbc.gridwidth = 2;
      gbc.gridy = 1;
      JLabel thumbnailLabel = new JLabel(thumbnail);
      add(thumbnailLabel, gbc);
      gbc.gridwidth = 1;
      gbc.gridy = 2;
      add(viewButton, gbc);
      gbc.gridx = 1;
      add(deleteButton, gbc);
      revalidate();
    }

    protected void setAsset(File asset) {
      this.asset = asset;
      BufferedImage temp = null;
      try {
        temp = Thumbnails.of(asset).size(150, 150).asBufferedImage();
      } catch (IOException ex) {
        String message = "Error creating thumbnail : " + asset.getAbsolutePath();
        JOptionPane.showMessageDialog(MainScreen.getInstance(), message, "IO Error",
        JOptionPane.ERROR_MESSAGE);
        Logger.getLogger(PanoPanel.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
        if (temp != null) {
          thumbnail = new ImageIcon(temp);
          showAssetGUI();
        }
      }
    }

  }
}
