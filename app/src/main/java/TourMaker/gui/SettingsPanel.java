// License: GPL. For details, see LICENSE file.
package TourMaker.gui;

import TourMaker.gui.boilerplate.ColoredButton;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Kishan Tripathi
 */
public class SettingsPanel extends JPanel {

  private final JLabel descriptionLabel;
  private final JTextField descriptionField;
  private final JButton saveButton;

  public SettingsPanel() {
    descriptionLabel = new JLabel("Project Description");
    descriptionField = new JTextField(20);
    saveButton = new ColoredButton(Color.green);
    saveButton.setText("Save");
    
    JPanel root = new JPanel();
    root.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    
    gbc.weightx = 1;
    gbc.weighty = 0.5;
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.insets = new Insets(10,10,10,10);
    gbc.gridy = 0;
    
    root.add(descriptionLabel, gbc);
    
    gbc.gridy = 1;
    root.add(descriptionField, gbc);
    
    gbc.gridy = 2;
    root.add(saveButton, gbc);
   
    add(root);
  }
  
}
