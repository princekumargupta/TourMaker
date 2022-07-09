// License: GPL. For details, see LICENSE file.
package TourMaker.util;

import com.formdev.flatlaf.FlatDarculaLaf;
import java.awt.Color;
import javax.swing.UIManager;

/**
 *
 * @author Kishan Tripathi
 */
public class LAFUtil {

  public static void initialize() {
    try {
      UIManager.setLookAndFeel(new FlatDarculaLaf());
      UIManager.put( "TabbedPane.selectedBackground", Color.GREEN.darker() );
    } catch (Exception e) {
    }
  }
  /**
   * Private no-args constructor for ensuring against instantiation.
   */
  private LAFUtil() {
  }
}
