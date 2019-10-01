/**
 * This class creates a weapon tree for dual blades.
 *
 * Date Last Modified: 10 / 01 / 2019
 *
 * @author Shirley Krogel
 */

public class DualBladesTree extends WeaponTree {
    public DualBladesTree() {
        LinkedWeaponTreeNode baseWeapon = new LinkedWeaponTreeNode(new DualBlades("Hunting Blades",
                100, 100, 0, 0), null);
        baseWeapon.setLeftChild(new LinkedWeaponTreeNode(new DualBlades("Great Jagras Blades",
                125, 100, 0, 0), "Great Jagras"));
        baseWeapon.setRightChild(new LinkedWeaponTreeNode(new DualBlades("Iron Blades", 112,
                120, 0, 0), "Great Jagras"));
        baseWeapon.getLeftChild().setLeftChild(new LinkedWeaponTreeNode(new DualBlades("Jyuratodus " +
                "Claw Cutlasses", 150, 112, 0, 0), "Jyuratodus"));
        baseWeapon.getLeftChild().setRightChild(new LinkedWeaponTreeNode(new DualBlades("Jyuratodus Fin " +
                "Blades", 130, 125, 0, 0), "Jyuratodus"));
        baseWeapon.getRightChild().setLeftChild(new LinkedWeaponTreeNode(new DualBlades("War Blades",
                140, 137, 0, 0), "Jyuratodus"));
        baseWeapon.getRightChild().setRightChild(new LinkedWeaponTreeNode(new DualBlades("Steel Cutlasses",
                135, 150, 0, 0), "Jyuratodus"));
        baseWeapon.getLeftChild().getLeftChild().setMiddleChild(new LinkedWeaponTreeNode(new DualBlades("Puk" +
                "ei Scale Scimitars", 170, 130, 0, 0), "Pukei-Pukei"));
        baseWeapon.getLeftChild().getRightChild().setMiddleChild(new LinkedWeaponTreeNode(new DualBlades("Tox" +
                "ic Cutlasses", 140, 150, 0.1, 0), "Pukei-Pukei"));
        baseWeapon.getRightChild().getLeftChild().setMiddleChild(new LinkedWeaponTreeNode(new DualBlades(
                "Flint Blades", 160, 145, 0, 0.1), "Qurupeco"));
        baseWeapon.getRightChild().getRightChild().setMiddleChild(new LinkedWeaponTreeNode(new DualBlades(
                "Quru Scale Sabers", 150, 160, 0, 0), "Qurupeco"));
        super.setCurrentWeapon(baseWeapon);
    }
}
