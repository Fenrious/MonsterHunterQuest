import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * This class is the base for creating and tracking the number of particular things in the Hunter's Inventory.
 *
 * Date Last Modified: 09 / 20 / 2019
 *
 * @author Shirley Krogel
 */

public class InventoryItem {

    private String buttonText;
    private String itemText;
    private int quantity;
    private Consumer<MonsterHunter> effect;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();

    // Mainly used for ingredients
    public InventoryItem(String buttonText, String itemText, int quantity) {
        this.buttonText = buttonText;
        this.itemText = itemText;
        this.quantity = quantity;
    }

    // Mainly used for instantly usable items such as herbs
    public InventoryItem(String buttonText, String itemText, int quantity, Consumer<MonsterHunter> effect) {
        this.buttonText = buttonText;
        this.itemText = itemText;
        this.quantity = quantity;
        this.effect = effect;
    }

    // Mainly used for craftable and usable items.
    public InventoryItem(String buttonText, String itemText, int quantity, Consumer<MonsterHunter> effect, ArrayList<Ingredient> ingredients) {
        this.buttonText = buttonText;
        this.itemText = itemText;
        this.quantity = quantity;
        this.effect = effect;
        this.ingredients.addAll(ingredients);
    }

    /**
     * Getters.
     *
     * @return
     */
    public String getButtonText() {
        return buttonText;
    }

    public String getItemText() {
        return itemText;
    }

    public int getQuantity() {
        return quantity;
    }

    public Consumer<MonsterHunter> getEffect() {
        return effect;
    }

    /**
     * Formats the item's text to show the item name and quantity.
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        temp.append(buttonText);
        temp.append(" (" + quantity + ")");
        return temp.toString();
    }

    /**
     * Simply increases the quantity by 1.
     */
    public void increaseQuantity(int gained) {
        quantity = quantity + gained;
    }

    /**
     * Used for both consumption and crafting. May not always decrease by just one.
     *
     * @param consumed
     */
    public void decreaseQuantity(int consumed) {
        quantity = quantity - consumed;
    }

    /**
     * The base method for an item to check if the hunter has the ingredients to make it. Different items require
     * different crafting materials.
     *
     * @param mh
     * @return
     */
    public boolean checkCraftable(MonsterHunter mh) {
        for (int i = 0; i < ingredients.size(); i++) {
            if (mh.getHunter().getInventory().getItem(ingredients.get(i).getName()).getQuantity() < ingredients.get(i).getQuantity()) {
                return false;
            }
        }
        return true;
    }

    /**
     * The base method for an item to be crafted. It will check the hunter's inventory for required ingredients.
     *
     * @param mh
     */
    public void craftItem(MonsterHunter mh) {
        if (mh.getHunter().getInventory().getItem(buttonText).getQuantity() < 20) {
            if (checkCraftable(mh)) {
                for (int i = 0; i < ingredients.size(); i++) {
                    mh.getHunter().getInventory().removeItem(ingredients.get(i).getName(), ingredients.get(i).getQuantity());
                }
                mh.getHunter().getInventory().addItem(buttonText, 1, mh);
                mh.updateLog(mh.getHunter().getName() + " successfully crafted a(n) " + buttonText + ".");
                mh.turn();
            } else {
                mh.updateLog(mh.getHunter().getName() + " lacked enough ingredients to make a(n) " + buttonText + ".");
            }
        }
        else {
            mh.updateLog(mh.getHunter().getName() + " could not hold any more " + buttonText + "s.");
        }
    }

    /**
     * Checks if the hunter has any of an item available.
     *
     * @param mh
     * @return
     */
    public boolean checkConsumable(MonsterHunter mh) {
        return (getQuantity() - 1 >= 0);
    }

    /**
     * This method uses the Consumer object that initialized the item.
     *
     * @param mh
     */
    public void useItem(MonsterHunter mh) {
        if (checkConsumable(mh)) {
            effect.accept(mh);
        } else {
            mh.updateLog(mh.getHunter().getName() + " did not have any " + buttonText + " left.");
        }
    }
}
