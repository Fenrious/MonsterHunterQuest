import javafx.scene.paint.Color;

/**
 * This class passes information into the Status class to affect the hunter.
 *
 * Date Last Modified: 08 / 20 / 2019
 *
 * @author Shirley Krogel
 */

public class Poison extends Status {

    public Poison(double chance){
        super(chance, Color.PURPLE, (mh) -> {
            if(!mh.getHunter().isPoisoned()){
                mh.getHunter().setPoisoned(true);
                mh.updateLog(mh.getHunter().getName() + " was poisoned!");
            }
        });
    }

}
