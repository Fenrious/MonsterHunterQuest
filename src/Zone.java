/**
 * This class was made to bring area elements together to be placed in a list for maps. Includes the name of the area
 * and the three gathering nodes that are present. The area name is mainly to help keep the programmer from forgetting
 * which area the index is pointing to. Coordinates were added to automate figuring out which direction one node is
 * from another.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class Zone {

    int name;
    Monster monster;
    Gatherable slot1;
    Gatherable slot2;
    Gatherable slot3;
    Gatherable slot4;
    int x;
    int y;

    public Zone(int name, Gatherable slot1, Gatherable slot2, Gatherable slot3, int x, int y) {
        this.name = name;
        this.slot1 = slot1;
        this.slot2 = slot2;
        this.slot3 = slot3;
        this.x = x;
        this.y = y;
        monster = null;
    }

    /**
     * Getters.
     * @return
     */
    public int getName() {
        return name;
    }

    public Gatherable getSlot1() {
        return slot1;
    }

    public Gatherable getSlot2() {
        return slot2;
    }

    public Gatherable getSlot3() {
        return slot3;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Gatherable getSlot4() {
        return slot4;
    }

    /**
     * Sets the special 4th slot.
     * @param drop
     */
    public void setSlot4(Gatherable drop) {
        slot4 = drop;
    }

    /**
     * Checks if the 4th slot has been fully harvested.
     */
    public void checkSlotEmpty() {
        if (slot4.getHarvestNumber() <= 0) {
            slot4 = null;
        }
    }

    /**
     * Getters for the coordinates.
     * @return
     */
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
