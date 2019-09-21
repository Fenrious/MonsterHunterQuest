/**
 * This class creates an inventory item meant to heal for a small amount or be used in crafting.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class HerbInventory extends InventoryItem {

    public HerbInventory() {
        super("Herb", "A plant with basic healing properties. When " +
                "combined with a Blue Mushroom, this healing is intensified.", 0, (mh) -> {
            if (mh.getHunter().getHp() == mh.getHunter().getMaxHp()) {
                mh.updateLog(mh.getHunter().getName() + " is at full health and did not use any herbs.");
            } else {
                if (mh.getHunter().getHp() + 10 > mh.getHunter().getMaxHp()) {
                    mh.getHunter().setHp(mh.getHunter().getMaxHp());
                } else {
                    mh.getHunter().setHp(mh.getHunter().getHp() + 10);
                }
                mh.getHunter().getInventory().getItem("Herb").decreaseQuantity(1);
                if (mh.getHunter().getInventory().getItem("Herb").getQuantity() > 0) {
                    mh.updateLog(mh.getHunter().getName() + " applied some herbs.");
                }
                if (mh.getHunter().getInventory().getItem("Herb").getQuantity() == 0) {
                    mh.updateLog(mh.getHunter().getName() + " applied the last of their herbs.");
                }
                mh.turn();
            }
        });
    }
}
