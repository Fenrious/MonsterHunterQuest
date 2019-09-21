import javafx.scene.paint.Color;

/**
 * This class passes information into the Status class to affect the hunter.
 *
 * Date Last Modified: 08 / 20 / 2019
 *
 * @author Shirley Krogel
 */

public class MudBlight extends Status {

    public MudBlight(double chance){
        super(chance, Color.SANDYBROWN, (mh) -> {
            if(!mh.getHunter().isMuddy()){
                mh.getHunter().setMuddy(true);
                mh.updateLog(mh.getHunter().getName() + " is covered in mud!");
            }
        });
    }
}
