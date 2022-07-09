// License: GPL. For details, see LICENSE file.
package TourMaker.gui;

import TourMaker.AppState;
import TourMaker.AppStateListener;
import TourMaker.data.Project;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 *
 * @author Kishan Tripathi
 */
public class MainScreen extends JFrame implements AppStateListener {

  private static MainScreen instance;
  private final JMenuBar menuBar;
  static final String APP_NAME = "Tour Maker";
  private final MainPanel mainPanel;

  private MainScreen() {
    setTitle(APP_NAME);
    menuBar = new MainMenuBar();
    setJMenuBar(menuBar);

    setLayout(new BorderLayout());

    mainPanel = new MainPanel();
    add(mainPanel, BorderLayout.CENTER);

    pack();
    setMinimumSize(new Dimension(800, 800));
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    AppState.addStateListener(this);
  }

  public static MainScreen getInstance() {
    if (instance == null) {
      instance = new MainScreen();
    }
    return instance;
  }

  @Override
  public void stateChanged(Project newProject) {
    mainPanel.displayProjectScreen();
  }
}
