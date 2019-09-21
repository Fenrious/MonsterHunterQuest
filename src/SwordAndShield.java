/**
 * This class is one of the major weapon types in the game.
 * It is the only weapon so far with a chance to block.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */


public class SwordAndShield extends Weapon {

    public SwordAndShield(String name, int damage, int maxSharpness, double poison, double burn) {
        super(name, damage * .10, 0, .5, 3, true, maxSharpness, poison, burn);
    }
}
