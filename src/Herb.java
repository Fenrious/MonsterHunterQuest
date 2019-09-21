import javafx.scene.image.Image;

/**
 * This class creates a gathering node for herbs.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class Herb extends Gatherable {
    public Herb() {
        super("Herb", new Image("/Herb1.png"), new Image("/Herb1Harvested.png"));
    }
}
