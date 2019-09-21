/**
 * This class is one of the major weapon types in the game.
 * It is the only weapon so far with a chance to stun.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class Hammer extends Weapon {

    public Hammer(String name, int damage, int maxSharpness, double poison, double burn) {
        super(name, damage * .25, 0.1, 0, 0, false, maxSharpness, poison, burn);
    }
}
