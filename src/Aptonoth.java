import javafx.scene.image.Image;

/**
 * This class creates a template of an Aptonoth small monster.
 *
 * Date Last Modified: 08 / 20 / 2019
 *
 * @author Shirley Krogel
 */

public class Aptonoth extends SmallMonster {
    public Aptonoth(int startingLocation, Hunter hunter, MonsterHunter mh) {
        super("Aptonoth", 100, 0.4,
                new Image("/AptonothFront.png"), new Attack("Aptonoth", 10,
                        1.5, 1, .75, .1, new NoStatus(),
                        new NoStatus(), 1, "The Aptonoth " +
                        "headbutts the space in front of it!", hunter, mh), new Image("/AptonothLeft.png"),
                new Attack("Aptonoth", 20 * 1.75, 1, 1, .75,
                        .4, new NoStatus(), new NoStatus(), 4,
                        "The Aptonoth launches itself to the left!", hunter, mh),
                new Image("/AptonothRight.png"), new Attack("Aptonoth", 10, 1, 0.7,
                        .75, .3, new NoStatus(), new NoStatus(),
                        2, "The Aptonoth launches itself to the right!",
                        hunter, mh),
                new Image("/AptonothBack.png"), new Attack("Aptonoth",
                        10, 1, .8, .75, .1,
                        new NoStatus(), new NoStatus(), 3,
                        "The Aptonoth sweeps its tail!", hunter, mh), startingLocation, 3);
    }
}
