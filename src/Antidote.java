import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class creates an inventory item meant to cure poison.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class Antidote extends InventoryItem {

    public Antidote() {
        super("Antidote", "A liquid that greatly boosts the body's ability to fight off toxins " +
                "upon imbibing.", 0, (mh) -> {
            if (mh.getHunter().isPoisoned()) {
                mh.getHunter().setPoisoned(false);
                mh.getHunter().getInventory().getItem("Antidote").decreaseQuantity(1);
                if (mh.getHunter().getInventory().getItem("Antidote").getQuantity() > 0) {
                    mh.updateLog(mh.getHunter().getName() + " drank an antidote.");
                }
                if (mh.getHunter().getInventory().getItem("Antidote").getQuantity() == 0) {
                    mh.updateLog(mh.getHunter().getName() + " drank the last of their antidotes.");
                }
                mh.updateLog(mh.getHunter().getName() + " was cured of their poison!");
                mh.turn();
            } else {
                mh.updateLog(mh.getHunter().getName() + " is not poisoned and did not need an antidote.");
            }
        }, new ArrayList<>(Arrays.asList(new Ingredient("Antidote Flower", 1), new Ingredient("Blue Mushroom", 1))));
    }
}
