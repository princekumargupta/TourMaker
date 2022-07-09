// License: GPL. For details, see LICENSE file.
package TourMaker.util;

import TourMaker.AppState;
import TourMaker.data.AssetType;
import TourMaker.data.Project;
import TourMaker.gui.MainScreen;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Kishan Tripathi
 */
public class AssetUtil {

  /**
   * Private no-args constructor for ensuring against instantiation.
   */
  private AssetUtil() {
  }

  public static boolean deleteAsset(File asset) {
    if (asset == null) {
      throw new IllegalArgumentException("File cannot be null");
    }
    if (!asset.exists()) {
      return false;
    }
    if (!IOUtil.isFileInDirectory(asset, new File(AppState.getCurrentProject().dir()))) {
      Logger.getLogger(IOUtil.class.getName()).log(Level.SEVERE, "File not in project directory : {0}", asset.getAbsolutePath());
      return false;
    }
    return asset.delete();
  }

  public static void loadAllAsssets(Project project) {
    String dir = project.dir();
    for (AssetType type : AssetType.values()) {
      String path = dir.concat("/").concat(type.name());
      File file = new File(path);
      if (file.exists()) {
        for (File assetFile : file.listFiles()) {
          boolean accepted = false;
          for (FileFilter filter : Utils.getFilterList(type)) {
            if (filter.accept(assetFile)) {
              accepted = true;
              break;
            }
          }
          if (accepted) {
            AppState.fireAssetAdded(type, assetFile);
          }
        }

      }
    }
  }
}
