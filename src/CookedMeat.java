import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class creates an inventory item meant to increase the hunter's stamina.
 *
 * Date Last Modified: 08 / 20 / 2019
 *
 * @author Shirley Krogel
 */

public class CookedMeat extends InventoryItem {
    public CookedMeat() {
        super("Cooked Meat", "Perfectly cooked meat on a stick. Eat to combat hunger."
                , 3, (mh) -> {
                    if (mh.getHunter().getMaxStamina() == 175) {
                        mh.updateLog(mh.getHunter().getName() + " is not hungry at the moment.");
                    } else {
                        mh.getHunter().getInventory().getItem("Cooked Meat").decreaseQuantity(1);
                        mh.getHunter().buffStamina(50);
                        if (mh.getHunter().getInventory().getItem("Cooked Meat").getQuantity() == 0) {
                            mh.updateLog(mh.getHunter().getName() + " ate the last piece of cooked meat.");
                        }
                        else {
                            mh.updateLog(mh.getHunter().getName() + " chowed down on some cooked meat and " +
                                    "feels more full.");
                        }
                        mh.turn();
                    }

                }, new ArrayList<>(Arrays.asList(new Ingredient("Raw Meat", 1),
                        new Ingredient("Herb", 1))));
    }
}
