// License: GPL. For details, see LICENSE file.
package TourMaker;

import TourMaker.data.AssetType;
import java.io.File;

/**
 *
 * @author Kishan Tripathi
 */
public interface AssetListener {
  
  public void assetAdded(AssetType type, File Asset);
}
