// License: GPL. For details, see LICENSE file.
package TourMaker.hotspot;

/**
 *
 * @author Kishan Tripathi
 */
public class Hotspot {

  public double azimuthal;
  public double polar;
  private final HotspotType type;
  private final String value;

  public HotspotType getType() {
    return type;
  }

  public String getValue() {
    return value;
  }

  public Hotspot(double az, double po, HotspotType typ, String value) {
    this.azimuthal = az;
    this.polar = po;
    this.type = typ;
    this.value = value;
  }
}
