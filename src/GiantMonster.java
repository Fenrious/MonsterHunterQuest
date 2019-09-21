import javafx.scene.image.Image;

/**
 * This class serves as a base for giant monsters in the game.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class GiantMonster extends Monster {

    private double size;

    public GiantMonster(String name, double hp, double chase, Image front, Attack frontAttack,
                        Image rightSide, Attack rightAttack, Image backSide, Attack backAttack, Image leftSide,
                        Attack leftAttack, int startingLocation, double averageSize) {
        super(name, hp * ((Math.random() * 0.75) + 0.75), chase, front, frontAttack, rightSide, rightAttack, backSide, backAttack,
                leftSide, leftAttack, startingLocation);
        size = super.getHP() / hp;
    }

    /**
     * Returns the size of the monster. Can be used on the victory screen.
     * @return
     */
    public double getSize() {
        return size;
    }
}
