// License: GPL. For details, see LICENSE file.
package TourMaker.gui;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Kishan Tripathi
 */
class WelcomeScreen extends JPanel {
  private final JLabel mainTitle;
  public WelcomeScreen() {
    mainTitle = new JLabel("Virtual Tour Maker");
    mainTitle.setFont(new Font("Serif", Font.PLAIN, 28));
    add(mainTitle);
    setPreferredSize(new Dimension(600,600));
  }
}
