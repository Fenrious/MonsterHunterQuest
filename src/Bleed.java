import javafx.scene.paint.Color;

/**
 * This class passes information into the Status class to affect the hunter.
 *
 * Date Last Modified: 08 / 20 / 2019
 *
 * @author Shirley Krogel
 */

public class Bleed extends Status {

    public Bleed(double chance){
        super(chance, Color.DARKRED, (mh) -> {
            if(!mh.getHunter().isBleeding()){
                mh.getHunter().setBleeding(true);
                mh.updateLog(mh.getHunter().getName() + " is now bleeding!");
            }
        });
    }
}
