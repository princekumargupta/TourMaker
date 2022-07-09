// License: GPL. For details, see LICENSE file.
package TourMaker.gui;

import TourMaker.AppState;
import static TourMaker.util.Utils.exit;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Kishan Tripathi
 */
public class MainMenuBar extends JMenuBar {

  private final JMenu file;
  private final JMenu helpMenu;
  private final JMenuItem newProject;
  private final JMenuItem openProject;
  private final JMenuItem openFile;
  private final JMenuItem closeProject;
  private final JMenuItem saveProject;
  private final JMenuItem saveAsProject;
  private final JMenuItem exit;
  private final JMenuItem help;

  public MainMenuBar() {
    file = new JMenu("File");
    helpMenu = new JMenu("Help");
    newProject = new JMenuItem("New Project");
    newProject.addActionListener((ActionEvent e) -> {
      AppState.newProject();
    });
    openFile = new JMenuItem("Open File");
    openProject = new JMenuItem("Open Project");
    openProject.addActionListener((ActionEvent e) -> {
      AppState.openProject();
    });
    closeProject = new JMenuItem("Close Project");
    saveProject = new JMenuItem("Save");
    saveProject.addActionListener((ActionEvent e) -> {
      AppState.save();
    });
    saveAsProject = new JMenuItem("Save As...");
    exit = new JMenuItem("Exit");
    exit.addActionListener((ActionEvent e) -> {
      exit();
    });
    help = new JMenuItem("Help");

    file.add(newProject);
    file.addSeparator();
    file.add(openProject);
    file.add(closeProject);
    file.add(openFile);
    file.addSeparator();
    file.add(saveProject);
    file.add(saveAsProject);
    file.addSeparator();
    file.add(exit);
    helpMenu.add(help);

    add(file);
    add(helpMenu);
  }

}
