// License: GPL. For details, see LICENSE file.
package TourMaker.gui.boilerplate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author Kishan Tripathi
 */
public class ColoredButton extends JButton {

  private final Color color;

  public ColoredButton(Color c) {
    this(c, false);
  }
  
  public ColoredButton(Color c, boolean slim) {
    this(c, Color.white, slim);
  }

  public ColoredButton(Color bColor, Color fColor) {
    this(bColor, fColor, false);
  }
  
  public ColoredButton(Color bColor, Color fColor, boolean slim) {
    super();
    color = bColor;
    setForeground(fColor);
    setBorder(slim ? BorderFactory.createEmptyBorder(3, 4, 3, 4) : BorderFactory.createEmptyBorder(7, 10, 7, 10));
  }
  
  @Override
    protected void paintComponent(final Graphics g) {
        if (!isEnabled()) {
            g.setColor(Color.DARK_GRAY);
        } else if (getModel().isPressed()) {
            g.setColor(color.darker().darker());
        } else if (getModel().isRollover()) {
            g.setColor(color.darker());
        } else {
            g.setColor(color);
        }
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 3, 3);
        super.paintComponent(g);
    }
    
     @Override
    public boolean isContentAreaFilled() {
        return false;
    }
}
