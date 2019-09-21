import javafx.scene.paint.Color;

/**
 * This class passes information into the Status class to affect the hunter.
 * Used as a null form of Status.
 *
 * Date Last Modified: 08 / 20 / 2019
 *
 * @author Shirley Krogel
 */

public class NoStatus extends Status {

    public NoStatus(){
        super(0, Color.BLACK, (mh) -> {});
    }
}
