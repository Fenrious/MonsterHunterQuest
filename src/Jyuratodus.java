import javafx.scene.image.Image;

/**
 * This class creates a basic Jyuratodus giant monster. It can be modified with the difficulty methods.
 *
 * Date Last Modified: 09 / 30 / 2019
 *
 * @author Shirley Krogel
 */

public class Jyuratodus extends GiantMonster {

    public Jyuratodus(int startingLocation, Hunter hunter, MonsterHunter mh) {
        super("Jyuratodus", 1200, 0.4,
                new Image("/JyuratodusFront.png"), new Attack("Jyuratodus", 25,
                        1.5, 1, .75, .4, new MudBlight(1),
                        new MudBlight(0.5), 1, "The Jyuratodus " +
                        "spits out a giant ball of mud!", hunter, mh),
                new Image("/JyuratodusLeft.png"), new Attack("Jyuratodus", 25,
                        1, 1, .6, .5, new MudBlight(0.6),
                        new MudBlight(0.2), 4, "The Jyuratodus uses its side to shove" +
                        " mud to the left!", hunter, mh), new Image("/JyuratodusRight.png"),
                new Attack("Jyuratodus", 25, 1, 1,
                        .6, .5, new MudBlight(0.6), new MudBlight(0.2),
                        2, "The Jyuratodus uses its side to shove mud to the right!",
                        hunter, mh),
                new Image("/JyuratodusBack.png"), new Attack("Jyuratodus",
                        25, 1.5, 1, .75, .6,
                        new NoStatus(), new MudBlight(0.4), 3,
                        "The Jyuratodus sweeps its tail, flinging some mud!", hunter, mh), startingLocation,
                1100);
    }

    /**
     * The easy version of the monster.
     */
    @Override
    public void easyMode() {
        super.setHP(900);
        super.getFrontAttack().setBaseDamage(18.75);
        super.getRightAttack().setBaseDamage(18.75);
        super.getBackAttack().setBaseDamage(18.75);
        super.getLeftAttack().setBaseDamage(18.75);
    }

    /**
     * The hard version of the monster.
     */
    @Override
    public void hardMode() {
        super.setHP(1800);
        super.getFrontAttack().setBaseDamage(37.5);
        super.getRightAttack().setBaseDamage(37.5);
        super.getBackAttack().setBaseDamage(37.5);
        super.getLeftAttack().setBaseDamage(37.5);
    }

    /**
     * The challenge version of the monster.
     */
    @Override
    public void challengeMode() {
        super.setHP(2100);
        super.getFrontAttack().setBaseDamage(43.75);
        super.getRightAttack().setBaseDamage(43.75);
        super.getBackAttack().setBaseDamage(43.75);
        super.getBackAttack().setMainStatus(new Bleed(0.7));
        super.getBackAttack().setDescription("The Jyuratodus uses the razor fin on its tail to slice the area " +
                "behind it while flinging mud to the sides!");
        super.getLeftAttack().setBaseDamage(43.75);
    }

}
