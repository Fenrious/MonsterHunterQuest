/**
 * This is a base for all weapon trees in the game.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class WeaponTree {
    private LinkedWeaponTreeNode currentWeapon;

    /**
     * Returns the current weapon.
     * @return
     */
    public LinkedWeaponTreeNode getCurrentWeapon() {
        return currentWeapon;
    }

    /**
     * Sets the current weapon, allowing for advancements to the weapon.
     * @param weapon
     */
    public void setCurrentWeapon(LinkedWeaponTreeNode weapon) {
        this.currentWeapon = weapon;
    }

    /**
     * Checks if the current weapon will have a choice when it is upgraded.
     * @return
     */
    public boolean nextHasChoice() {
        return currentWeapon.getLeftChild() != null;
    }

    /**
     * Checks that the weapon isn't the most upgraded it can be.
     * @return
     */
    public boolean notFinalWeapon() {
        return (nextHasChoice() || currentWeapon.getMiddleChild() != null);
    }

    /**
     * Returns the monster required for the next upgrade.
     * @return
     */
    public String getRequiredMonster() {
        if (nextHasChoice()) {
            return (currentWeapon.getLeftChild().getRequiredMonster());
        } else {
            return (currentWeapon.getMiddleChild().getRequiredMonster());
        }
    }
}
