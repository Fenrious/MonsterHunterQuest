import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class creates an inventory item meant to heal the hunter for a large amount.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class MegaPotion extends InventoryItem {

    public MegaPotion() {
        super("Mega Potion", "A liquid that speeds up a body's natural " +
                "healing capabilities to an extreme extent upon imbibing.", 0, (mh) -> {
            if (mh.getHunter().getHp() == mh.getHunter().getMaxHp()) {
                mh.updateLog(mh.getHunter().getName() + " is at full health and did not need a potion.");
            } else {
                if (mh.getHunter().getHp() + 75 > mh.getHunter().getMaxHp()) {
                    mh.getHunter().setHp(mh.getHunter().getMaxHp());
                } else {
                    mh.getHunter().setHp(mh.getHunter().getHp() + 75);
                }
                mh.getHunter().getInventory().getItem("Mega Potion").decreaseQuantity(1);
                if (mh.getHunter().getInventory().getItem("Mega Potion").getQuantity() > 0) {
                    mh.updateLog(mh.getHunter().getName() + " drank a mega potion.");
                }
                if (mh.getHunter().getInventory().getItem("Mega Potion").getQuantity() == 0) {
                    mh.updateLog(mh.getHunter().getName() + " drank their last potion.");
                }
                mh.turn();
            }
        }, new ArrayList<>(Arrays.asList(new Ingredient("Potion", 1), new Ingredient("Honey", 1))));
    }
}
