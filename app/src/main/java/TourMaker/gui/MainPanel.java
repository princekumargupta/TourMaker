// License: GPL. For details, see LICENSE file.
package TourMaker.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Kishan Tripathi
 */
public final class MainPanel extends JPanel {

  private WelcomeScreen welcomeScreen;
  private ProjectPanel projectPanel;

  public MainPanel() {
    welcomeScreen = new WelcomeScreen();
    projectPanel = new ProjectPanel();
    displayWelcomeScreen();
  }

  public void displayWelcomeScreen() {
    removeAll();
    add(welcomeScreen, BorderLayout.CENTER);
    revalidate();
  }

  public void displayProjectScreen() {
    removeAll();
    setLayout(new BorderLayout());
    projectPanel = new ProjectPanel();
    add(projectPanel, BorderLayout.CENTER);
    revalidate();
  }

  public ProjectPanel getProjectPanel() {
    return projectPanel;
  }
}
