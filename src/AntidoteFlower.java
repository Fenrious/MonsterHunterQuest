import javafx.scene.image.Image;

/**
 * This class creates a gathering node for antidote flowers.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class AntidoteFlower extends Gatherable {
    public AntidoteFlower() {
        super("Antidote Flower", new Image("/AntidoteFlower.png"), new Image("/AntidoteFlowerHarvested.png"));
    }
}
