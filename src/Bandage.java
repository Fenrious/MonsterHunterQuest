import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class creates an inventory item meant to cure poison.
 *
 * Date Last Modified: 10 / 03 / 2019
 *
 * @author Shirley Krogel
 */

public class Bandage extends InventoryItem {

    public Bandage() {
        super("Bandage", "Leaves prepared to wrapped around a bleeding wound.", 0,
                (mh) -> {
            if (mh.getHunter().isBleeding()) {
                mh.getHunter().setBleeding(false);
                mh.getHunter().getInventory().getItem("Bandage").decreaseQuantity(1);
                if (mh.getHunter().getInventory().getItem("Bandage").getQuantity() > 0) {
                    mh.updateLog(mh.getHunter().getName() + " wrapped the bandage around their wound.");
                }
                if (mh.getHunter().getInventory().getItem("Bandage").getQuantity() == 0) {
                    mh.updateLog(mh.getHunter().getName() + " wrapped the last of their bandages around " +
                            "their wound.");
                }
                mh.updateLog(mh.getHunter().getName() + "'s bleeding was stopped!");
                mh.turn();
            } else {
                mh.updateLog(mh.getHunter().getName() + " is not bleeding.");
            }
        }, new ArrayList<>(Arrays.asList(new Ingredient("Herb", 2))));
    }
}
