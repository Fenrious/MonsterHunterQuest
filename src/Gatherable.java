import javafx.scene.image.Image;

/**
 * This class serves as a base for gathering nodes in the game.
 *
 * Date Last Modified: 09 / 20 / 2019
 *
 * @author Shirley Krogel
 */

public class Gatherable {

    private int harvestNumber = (int) (Math.random() * 4 + 1);
    private int maxHarvestNumber = harvestNumber;
    private int regenerationTick = 0;
    private String itemName;
    private Image gatherableIcon;
    private Image harvestedIcon;

    public Gatherable(String name, Image gatherableIcon, Image harvestedIcon) {
        itemName = name;
        this.gatherableIcon = gatherableIcon;
        this.harvestedIcon = harvestedIcon;
    }


    /**
     * Returns the current number of items that can be harvested.
     *
     * @return
     */
    public int getHarvestNumber() {
        return harvestNumber;
    }

    public void addToHarvestNumber(int amount){harvestNumber += amount;}

    /**
     * Returns the appropriate icon based on if there is anything left to harvest.
     * @return
     */
    public Image getIcon() {
        if (harvestNumber >= 1) {
            return gatherableIcon;
        } else {
            return harvestedIcon;
        }
    }

    public String getItemName() {
        return itemName;
    }

    /**
     * Allows for harvesting of nodes. The last harvest is a special case, and it lets the hunter know when
     * there is no more resource left.
     *
     * @param mh
     */
    public void harvest(MonsterHunter mh) {
        if (mh.getHunter().getInventory().getItem(itemName).getQuantity() < 20) {
            if (harvestNumber - 1 > 0) {
                if (mh.getHunter().getInventory().addItem(itemName, 1, mh)) {
                    harvestNumber = harvestNumber - 1;
                    if (itemName.equals("Honey")) {
                        mh.updateLog(mh.getHunter().getName() + " harvested some " + itemName + ".");
                    } else if (itemName.startsWith("A") || itemName.startsWith("H")) {
                        mh.updateLog(mh.getHunter().getName() + " harvested an " + itemName + ".");
                    } else {
                        mh.updateLog(mh.getHunter().getName() + " harvested a " + itemName + ".");
                    }
                    mh.turn();
                } else {
                    mh.updateLog(mh.getHunter().getName() + " could not harvest any " + itemName + ".");
                }

            } else if (harvestNumber - 1 == 0) {
                if (mh.getHunter().getInventory().getItem(itemName).getQuantity() < 20
                        && mh.getHunter().getInventory().addItem(itemName, 1, mh)) {
                    harvestNumber = harvestNumber - 1;
                    if (itemName.equals("Honey")) {
                        mh.updateLog(mh.getHunter().getName() + " harvested the last bit of " + itemName + ".");
                    } else {
                        mh.updateLog(mh.getHunter().getName() + " harvested the last " + itemName + ".");
                    }
                    mh.turn();
                } else {
                    mh.updateLog(mh.getHunter().getName() + " could not harvest any " + itemName + ".");
                }
            } else {
                mh.updateLog(mh.getHunter().getName() + " failed to collect anything.");
            }
        }
        else {
            mh.updateLog(mh.getHunter().getName() + " could not hold any more " + itemName + ".");
        }
    }

    /**
     * Allows a node to regenerate over time.
     */
    public void replenish() {
        if (harvestNumber == maxHarvestNumber) {
            regenerationTick = 0;
        } else if (regenerationTick >= 20) {
            harvestNumber += 1;
            regenerationTick = 0;
        } else {
            regenerationTick += 1;
        }
    }


}
