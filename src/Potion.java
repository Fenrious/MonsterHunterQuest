import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class creates an inventory item meant to heal the hunter for a moderate amount.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class Potion extends InventoryItem {

    public Potion() {
        super("Potion", "A liquid that speeds up a body's natural healing " +
                "capabilities upon imbibing.", 2, (mh) -> {
            if (mh.getHunter().getHp() == mh.getHunter().getMaxHp()) {
                mh.updateLog(mh.getHunter().getName() + " is at full health and did not need a potion.");
            } else {
                if (mh.getHunter().getHp() + 30 > mh.getHunter().getMaxHp()) {
                    mh.getHunter().setHp(mh.getHunter().getMaxHp());
                } else {
                    mh.getHunter().setHp(mh.getHunter().getHp() + 30);
                }
                mh.getHunter().getInventory().getItem("Potion").decreaseQuantity(1);
                if (mh.getHunter().getInventory().getItem("Potion").getQuantity() > 0) {
                    mh.updateLog(mh.getHunter().getName() + " drank a potion.");
                }
                if (mh.getHunter().getInventory().getItem("Potion").getQuantity() == 0) {
                    mh.updateLog(mh.getHunter().getName() + " drank their last potion.");
                }
                mh.turn();
            }
        }, new ArrayList<>(Arrays.asList(new Ingredient("Herb", 1), new Ingredient("Blue Mushroom", 1))));
    }
}
