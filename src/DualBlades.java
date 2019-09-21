/**
 * This class is one of the major weapons in the game.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class DualBlades extends Weapon {

    public DualBlades(String name, int damage, int maxSharpness, double poison, double burn) {
        super(name, damage * .10, 0, .65, 4, false, maxSharpness, poison, burn);
    }
}
