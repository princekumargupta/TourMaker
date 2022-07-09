// License: GPL. For details, see LICENSE file.
package TourMaker.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kishan Tripathi
 */
public class Sequence {
  private final String name;
  private final List<Node> nodes;
  
  public Sequence(String nname) {
    name = nname;
    nodes = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public List<Node> getNodes() {
    return nodes;
  }
  
  public void addNode(Node node) {
    nodes.add(node);
  }
}
