import javafx.scene.paint.Color;

/**
 * This class passes information into the Status class to affect the hunter.
 *
 * Date Last Modified: 08 / 20 / 2019
 *
 * @author Shirley Krogel
 */

public class Burn extends Status {

    public Burn(double chance){
        super(chance, Color.ORANGE, (mh) -> {
            if(!mh.getHunter().isBurning()){
                mh.getHunter().setBurning(true);
                mh.updateLog(mh.getHunter().getName() + " is now burning!");
            }
        });
    }
}
