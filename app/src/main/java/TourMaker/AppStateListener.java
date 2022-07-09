// License: GPL. For details, see LICENSE file.
package TourMaker;

import TourMaker.data.Project;

/**
 *
 * @author Kishan Tripathi
 */
public interface AppStateListener {

  public void stateChanged(Project newProject);
}
