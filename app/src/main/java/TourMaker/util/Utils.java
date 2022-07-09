// License: GPL. For details, see LICENSE file.
package TourMaker.util;

import TourMaker.data.AssetType;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Kishan Tripathi
 */
public class Utils {

  private final static List<FileFilter> imageFilter = new ArrayList();
  private final static List<FileFilter> audioFilter = new ArrayList();
  private final static List<FileFilter> videoFilter = new ArrayList();
  private final static List<FileFilter> pdfFilter = new ArrayList();

  /**
   * Private no-args constructor for ensuring against instantiation.
   */
  private Utils() {
  }

  static {
    imageFilter.add(new FileNameExtensionFilter("Images Files", ImageIO.getReaderFileSuffixes()));
    AudioFileFormat.Type[] audioFileTypes = AudioSystem.getAudioFileTypes();

    audioFilter.add(new FileNameExtensionFilter("Mp3 Files", "mp3"));
    for (AudioFileFormat.Type e : audioFileTypes) {
      FileNameExtensionFilter fnef = new FileNameExtensionFilter(e.toString(), e.getExtension());
      audioFilter.add(fnef);
    }

    videoFilter.add(new FileNameExtensionFilter("Mp4 Files", "mp4"));

    pdfFilter.add(new FileNameExtensionFilter("Pdf Files", "pdf"));
  }

  public static void exit() {
    System.exit(0);
  }

  public static List<FileFilter> getFilterList(AssetType type) {
    switch (type) {
      case Panorama:
        return imageFilter;
      case Image:
        return imageFilter;
      case Video:
        return videoFilter;
      case Audio:
        return audioFilter;
      case Pdf:
        return pdfFilter;
      default:
        return null;
    }
  }
}
