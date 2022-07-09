// License: GPL. For details, see LICENSE file.
package TourMaker.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import net.coobird.thumbnailator.Thumbnails;

/**
 *
 * @author Kishan Tripathi
 */
public class ImageUtil {

  /**
   * Private no-args constructor for ensuring against instantiation.
   */
  private ImageUtil() {
  }

  public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
    Image img = icon.getImage();
    Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
    return new ImageIcon(resizedImage);
  }
  
  public static Icon createIcon(BufferedImage img,  int width, int height) {
    try {
      BufferedImage thumbnail = Thumbnails.of(img).size(width, height).asBufferedImage();
      return new ImageIcon(thumbnail);
    } catch (IOException ex) {
      Logger.getLogger(ImageUtil.class.getName()).log(Level.SEVERE, null, ex);
    }
    Image resizedImage = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
    return new ImageIcon(resizedImage);
  }
}
