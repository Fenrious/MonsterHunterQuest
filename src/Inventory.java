import java.util.ArrayList;

/**
 * This class creates an inventory for tracking how much a hunter has of each item.
 *
 * Date Last Modified: 10 / 10 / 2019
 *
 * @author Shirley Krogel
 */

public class Inventory {

    private ArrayList<InventoryItem> inventory = new ArrayList<>();

    public Inventory() {
        inventory.add(new Potion());
        inventory.add(new MegaPotion());
        inventory.add(new Antidote());
        inventory.add(new CookedMeat());
        inventory.add(new Bandage());
        inventory.add(new HerbInventory());
        inventory.add(new AntidoteFlowerInventory());
        inventory.add(new BlueMushroomInventory());
        inventory.add(new HoneyInventory());
        inventory.add(new RawMeatInventory());
    }

    /**
     * Returns the whole inventory.
     * @return
     */
    public ArrayList<InventoryItem> getInventory() {
        return inventory;
    }

    /**
     * Returns a particular item based on the name.
     * @param name
     * @return
     */
    public InventoryItem getItem(String name) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) != null && inventory.get(i).getButtonText().equals(name)) {
                return inventory.get(i);
            }
        }
        return null;
    }

    /**
     * Makes a string out of the entire inventory.
     * @return
     */
    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        temp.append("[ ");
        for (int i = 0; i < inventory.size(); i++) {
            inventory.get(i).toString();
        }
        temp.append(" ]");
        return temp.toString();
    }

    /**
     * Increases the quantity of an item in the inventory based on a name.
     * @param name
     * @param increase
     * @param mh
     * @return
     */
    public boolean addItem(String name, int increase, MonsterHunter mh) {
        if (getItem(name) != null) {
            getItem(name).increaseQuantity(increase);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Decreases the quantity of an item in the inventory based on a name.
     * @param name
     * @param decrease
     * @return
     */
    public boolean removeItem(String name, int decrease) {
        if (getItem(name) != null && getItem(name).getQuantity() > 0) {
            getItem(name).decreaseQuantity(decrease);
            return true;
        } else {
            return false;
        }
    }
}
