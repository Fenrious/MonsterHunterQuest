import javafx.scene.image.Image;

/**
 * This class creates a jagras small monster.
 * Currently not used. (Need to draw images)
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class Jagras extends SmallMonster {
    public Jagras(int startingLocation, Hunter hunter, MonsterHunter mh) {
        super("Jagras", 100, 0.4,
                new Image("/GreatJagrasEnlarged.png"), new Attack("Jagras", 10,
                        1.5, 1, .75, .1, new NoStatus(),
                        new NoStatus(), 1, "The Jagras " +
                        "charges forward!", hunter, mh), new Image("/GreatJagrasRight.png"),
                new Attack("Jagras", 10, 1, 0.7,
                        .75, .3, new NoStatus(), new NoStatus(),
                        2, "The Jagras launches itself to the right!",
                        hunter, mh), new Image("/GreatJagrasBack.png"), new Attack("Jagras",
                        10, 1, .8, .75, .1,
                        new NoStatus(), new NoStatus(), 3,
                        "The Jagras leaps backwards!", hunter, mh),
                new Image("/GreatJagrasLeft.png"), new Attack("Jagras", 20 * 1.75,
                        1, 1, .75, .4, new NoStatus(),
                        new NoStatus(), 4, "The Jagras launches " +
                        "itself to the left!", hunter, mh), startingLocation, 10);
    }

}
