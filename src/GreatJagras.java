import javafx.scene.image.Image;

/**
 * This class creates a basic Great Jagras giant monster. It can be modified with the difficulty methods.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class GreatJagras extends GiantMonster {

    public GreatJagras(int startingLocation, Hunter hunter, MonsterHunter mh) {
        super("Great Jagras", 1000, 0.3,
                new Image("/GreatJagrasIdleEnlarged.png"), new Attack("Great Jagras", 20,
                        1.5, 1, .75, .1, new NoStatus(),
                        new NoStatus(), 1, "The Great Jagras " +
                        "slams down with its large belly!", hunter, mh),
                new Image("/GreatJagrasLeft.png"), new Attack("Great Jagras", 20,
                        1, 1, .75, .4, new NoStatus(),
                        new NoStatus(), 4, "The Great Jagras swipes " +
                        "to the left with its massive claws!", hunter, mh), new Image("/GreatJagrasRight.png"),
                new Attack("Great Jagras", 20, 1, 1,
                        .75, .4, new NoStatus(), new NoStatus(),
                        2, "The Great Jagras swipes to the right with its massive claws!",
                        hunter, mh),
                new Image("/GreatJagrasBack.png"), new Attack("Great Jagras",
                        20, 1.5, 1, .75, .6,
                        new NoStatus(), new NoStatus(), 3,
                        "The Great Jagras sweeps its tail!", hunter, mh), startingLocation, 1100);
    }

    /**
     * The easy version of the monster.
     */
    @Override
    public void easyMode() {
        super.setHP(750);
        super.getFrontAttack().setBaseDamage(15);
        super.getRightAttack().setBaseDamage(15);
        super.getBackAttack().setBaseDamage(15);
        super.getLeftAttack().setBaseDamage(15);
    }

    /**
     * The hard version of the monster.
     */
    @Override
    public void hardMode() {
        super.setHP(1500);
        super.getFrontAttack().setBaseDamage(30);
        super.getRightAttack().setBaseDamage(30);
        super.getBackAttack().setBaseDamage(30);
        super.getLeftAttack().setBaseDamage(30);
    }

    /**
     * The challenge version of the monster.
     */
    @Override
    public void challengeMode() {
        super.setHP(1750);
        super.getFrontAttack().setBaseDamage(35);
        super.getRightAttack().setBaseDamage(35);
        super.getRightAttack().setMainStatus(new Poison(0.4));
        super.getRightAttack().setGrazeStatus(new Poison(0.2));
        super.getBackAttack().setBaseDamage(35);
        super.getLeftAttack().setBaseDamage(35);
        super.getLeftAttack().setMainStatus(new Poison(0.4));
        super.getLeftAttack().setGrazeStatus(new Poison(0.2));
    }

}