/**
 * This class helps to form a weapon tree for purposes of upgrading a weapon.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class LinkedWeaponTreeNode {

    private LinkedWeaponTreeNode leftChild = null;
    private LinkedWeaponTreeNode middleChild = null;
    private LinkedWeaponTreeNode rightChild = null;
    private Weapon weapon;
    private String requiredMonster;

    public LinkedWeaponTreeNode(Weapon weapon, String monster) {
        this.weapon = weapon;
        this.requiredMonster = monster;
    }

    /**
     * Getters and Setters.
     * @return
     */
    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public LinkedWeaponTreeNode getLeftChild() {
        return leftChild;
    }

    public LinkedWeaponTreeNode getMiddleChild() {
        return middleChild;
    }

    public LinkedWeaponTreeNode getRightChild() {
        return rightChild;
    }

    public void setLeftChild(LinkedWeaponTreeNode left) {
        this.leftChild = left;
    }

    public void setMiddleChild(LinkedWeaponTreeNode middle) {
        this.middleChild = middle;
    }

    public void setRightChild(LinkedWeaponTreeNode right) {
        this.rightChild = right;
    }

    public String getRequiredMonster() {
        return requiredMonster;
    }
}
