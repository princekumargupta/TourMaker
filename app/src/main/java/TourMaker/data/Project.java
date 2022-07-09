// License: GPL. For details, see LICENSE file.
package TourMaker.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kishan Tripathi
 */
public class Project {
  private final String dir;
  private final String description;
  private final List<Sequence> sequences;
  
  public Project(String dir) {
    this(dir, "Virtual Tour");
  }
  
  public Project(String directory, String des) {
    dir = directory;
    description = des;
    sequences = new ArrayList();
  }
  
  public String dir() {
    return dir;
  }
  
  public String description() {
    return description;
  }
  
  public List<Sequence> getSequences() {
    return sequences;
  }

  public void addSequence(Sequence seq) {
    sequences.add(seq);
  }
}
