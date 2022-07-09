// License: GPL. For details, see LICENSE file.
package TourMaker.gui;

import static TourMaker.AppState.setCurrentProject;
import TourMaker.data.Project;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Kishan Tripathi
 */
public class CreateProject extends JPanel {

  private final JLabel projectDescriptionLabel;
  private final JLabel projectDirLabel;
  private final JTextField projectDescriptionField;
  private final JTextField projectDirField;
  private final JButton browseButton;
  private final JButton createButton;
  private final JDialog parentDialog;

  public CreateProject(JDialog jd) {
    parentDialog = jd;
    
    projectDescriptionLabel = new JLabel("Project Description:");
    projectDirLabel = new JLabel("Project Location:");
    projectDescriptionField = new JTextField(25);
    projectDescriptionField.setText("Virtual Tour");
    projectDirField = new JTextField(25);
    projectDirField.setEditable(false);
    browseButton = new JButton("Browse...");
    browseButton.addActionListener((ActionEvent e) -> {
      chooseDirectory();
    });

    createButton = new JButton("Create");
    createButton.setEnabled(false);
    createButton.addActionListener((ActionEvent e) -> {
      String projectDescription = projectDescriptionField.getText();
      String projectDirectory = projectDirField.getText();
      Project project = new Project(projectDirectory, projectDescription);
      setCurrentProject(project);
      parentDialog.dispose();
    });

    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.anchor = GridBagConstraints.LINE_START;

    add(projectDescriptionLabel, gbc);
    gbc.gridx = 1;
    add(projectDescriptionField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    add(projectDirLabel, gbc);
    gbc.gridx = 1;
    add(projectDirField, gbc);
    gbc.gridx = 2;
    add(browseButton, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;

    gbc.gridwidth = 3;
    gbc.anchor = GridBagConstraints.CENTER;
    add(createButton, gbc);
  }

  private void chooseDirectory() {

    JFileChooser fc = new JFileChooser();
    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int res = fc.showDialog(this, "Select");

    if (res == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fc.getSelectedFile();
      String dir = selectedFile.getAbsolutePath();
      projectDirField.setText(dir);
      createButton.setEnabled(true);
    }
  }
}
