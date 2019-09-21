/**
 * This class creates a weapon tree for hammers.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class HammerTree extends WeaponTree {
    public HammerTree() {
        LinkedWeaponTreeNode baseWeapon = new LinkedWeaponTreeNode(new Hammer("Hunting Hammer", 100,
                100, 0, 0), null);
        baseWeapon.setLeftChild(new LinkedWeaponTreeNode(new Hammer("Great Jagras Hammer", 125,
                100, 0, 0), "Great Jagras"));
        baseWeapon.setRightChild(new LinkedWeaponTreeNode(new Hammer("Iron Hammer", 112,
                120, 0, 0), "Great Jagras"));
        baseWeapon.getLeftChild().setLeftChild(new LinkedWeaponTreeNode(new Hammer("Barroth Ridge Hammer",
                150, 112, 0, 0), "Barroth"));
        baseWeapon.getLeftChild().setRightChild(new LinkedWeaponTreeNode(new Hammer("Barroth Claw Hammer",
                130, 125, 0, 0), "Barroth"));
        baseWeapon.getRightChild().setLeftChild(new LinkedWeaponTreeNode(new Hammer("War Hammer", 140,
                137, 0, 0), "Barroth"));
        baseWeapon.getRightChild().setRightChild(new LinkedWeaponTreeNode(new Hammer("Steel Hammer",
                135, 150, 0, 0), "Barroth"));
        baseWeapon.getLeftChild().getLeftChild().setMiddleChild(new LinkedWeaponTreeNode(new Hammer("Pukei " +
                "Shell Hammer", 170, 130, 0, 0), "Pukei-Pukei"));
        baseWeapon.getLeftChild().getRightChild().setMiddleChild(new LinkedWeaponTreeNode(new Hammer("Toxin " +
                "Hammer", 140, 150, 0.2, 0), "Pukei-Pukei"));
        baseWeapon.getRightChild().getLeftChild().setMiddleChild(new LinkedWeaponTreeNode(new Hammer("Flint " +
                "Hammer", 160, 145, 0, 0.2), "Qurupeco"));
        baseWeapon.getRightChild().getRightChild().setMiddleChild(new LinkedWeaponTreeNode(new Hammer("Plume " +
                "Hammer", 150, 160, 0, 0), "Qurupeco"));
        super.setCurrentWeapon(baseWeapon);
    }
}
