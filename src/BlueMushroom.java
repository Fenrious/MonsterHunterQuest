import javafx.scene.image.Image;

/**
 * This class creates a gathering node for blue mushrooms.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class BlueMushroom extends Gatherable{
    public BlueMushroom(){
        super("Blue Mushroom", new Image("/BlueMushroom.png"), new Image("/BlueMushroomHarvested.png"));
    }
}
