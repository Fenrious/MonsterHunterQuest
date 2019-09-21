import javafx.scene.image.Image;

/**
 * This class creates a gathering node for honey.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class Honey extends Gatherable {
    public Honey() {
        super("Honey", new Image("/GroundHive.png"), new Image("/GroundHiveHarvested.png"));
    }
}
