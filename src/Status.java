import javafx.scene.paint.Color;
import java.util.function.Consumer;

/**
 * This class holds the information for infecting the hunter with a debuff.
 *
 * Date Last Modified: 08 / 20 / 2019
 *
 * @author Shirley Krogel
 */

public class Status {

    private double chance;
    private Color color;
    private Consumer<MonsterHunter> effect;

    public Status(double chance, Color color, Consumer<MonsterHunter> effect){
        this.chance = chance;
        this.color = color;
        this.effect = effect;

    }

    public Color getColor(){return color;}

    public void checkStatus(MonsterHunter mh){
        if(Math.random() < chance){
            effect.accept(mh);
        }
    }
}
