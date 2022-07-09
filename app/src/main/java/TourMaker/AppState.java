// License: GPL. For details, see LICENSE file.
package TourMaker;

import TourMaker.data.AssetType;
import TourMaker.data.Project;
import TourMaker.gui.CreateProject;
import TourMaker.gui.MainScreen;
import static TourMaker.util.AssetUtil.loadAllAsssets;
import TourMaker.util.IOExport;
import TourMaker.util.IOImport;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Kishan Tripathi
 */
public class AppState {

  private static Project curProject;

  private static final Integer LOCK = 1;
  private static final List<AppStateListener> stateListeners = new ArrayList();
  private static final List<AssetListener> assetListeners = new ArrayList();

  /**
   * Private no-args constructor for ensuring against instantiation.
   */
  private AppState() {
  }

  public static Project getCurrentProject() {
    return curProject;
  }

  public static void save() {
    synchronized (LOCK) {
      if (curProject != null) {
        IOExport.saveProject(curProject);
      }
    }
  }

  public static void openProject() {
    Project  project = null;
    JFileChooser fc = new JFileChooser();
    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fc.setMultiSelectionEnabled(false);
    int res = fc.showDialog(MainScreen.getInstance(), "Open");
    if (res == JFileChooser.APPROVE_OPTION) {
      File projectFile = fc.getSelectedFile();
      project = IOImport.openProject(projectFile);
      if (project == null) {
        String message = "Error opening project file : " + projectFile.getAbsolutePath();
        JOptionPane.showMessageDialog(MainScreen.getInstance(), message, "IO Error",
        JOptionPane.ERROR_MESSAGE);
        return;
      }
      setCurrentProject(project);
      new Thread() {
        @Override
        public void run() {
          loadAllAsssets(getCurrentProject());
        }
      }.start();
    }
  }

  public static void newProject() {
    JDialog jd = new JDialog(MainScreen.getInstance(), "New Project", true);
    CreateProject createProject = new CreateProject(jd);
    jd.add(createProject);
    jd.pack();
    jd.setLocationRelativeTo(MainScreen.getInstance());
    jd.setVisible(true);
  }

  public static void setCurrentProject(Project project) {
    synchronized (LOCK) {
      curProject = project;
      fireStateChanged();
    }
  }

  public static void addStateListener(AppStateListener listener) {
    synchronized (LOCK) {
      stateListeners.add(listener);
    }
  }

  public static boolean removeStateListener(AppStateListener listener) {
    synchronized (listener) {
      return stateListeners.remove(listener);
    }
  }

  public static void addAssetListener(AssetListener listener) {
    synchronized (LOCK) {
      assetListeners.add(listener);
    }
  }

  public static boolean removeAssetListener(AssetListener listener) {
    synchronized (listener) {
      return assetListeners.remove(listener);
    }
  }

  private static void fireStateChanged() {
    synchronized (LOCK) {
      for (AppStateListener listener : stateListeners) {
        listener.stateChanged(curProject);
      }
    }
  }

  public static void fireAssetAdded(AssetType type, File asset) {
    synchronized (LOCK) {
      for (AssetListener listener : assetListeners) {
        listener.assetAdded(type, asset);
      }
    }
  }
}
