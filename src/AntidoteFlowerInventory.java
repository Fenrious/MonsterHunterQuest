/**
 * This class creates an item of antidote flowers to be tracked in the Hunter's Inventory.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class AntidoteFlowerInventory extends InventoryItem{
    public AntidoteFlowerInventory(){
        super("Antidote Flower", "A flower with " +
                "strong anti-toxin properties. It is inedible in its current form. Combine with a Blue Mushroom " +
                "to create a digestible potion.",0);
    }
}
