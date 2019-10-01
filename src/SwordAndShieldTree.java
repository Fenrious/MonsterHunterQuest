/**
 * This class creates a weapon tree for swords and shields.
 *
 * Date Last Modified: 10 / 01 / 2019
 *
 * @author Shirley Krogel
 */

public class SwordAndShieldTree extends WeaponTree {

    public SwordAndShieldTree() {
        LinkedWeaponTreeNode baseWeapon = new LinkedWeaponTreeNode(new SwordAndShield("Hunting Knife",
                100, 100, 0, 0), null);
        baseWeapon.setLeftChild(new LinkedWeaponTreeNode(new SwordAndShield("Great Jagras Knife",
                125, 100, 0, 0), "Great Jagras"));
        baseWeapon.setRightChild(new LinkedWeaponTreeNode(new SwordAndShield("Iron Knife", 112,
                120, 0, 0), "Great Jagras"));
        baseWeapon.getLeftChild().setLeftChild(new LinkedWeaponTreeNode(new SwordAndShield("Jyuratodus Claw " +
                "Kris", 150, 112, 0, 0), "Jyuratodus"));
        baseWeapon.getLeftChild().setRightChild(new LinkedWeaponTreeNode(new SwordAndShield("Jyuratodus " +
                "Fin Machete", 130, 125, 0, 0), "Jyuratodus"));
        baseWeapon.getRightChild().setLeftChild(new LinkedWeaponTreeNode(new SwordAndShield("War Saber",
                140, 137, 0, 0), "Jyuratodus"));
        baseWeapon.getRightChild().setRightChild(new LinkedWeaponTreeNode(new SwordAndShield("Steel Knife",
                135, 150, 0, 0), "Jyuratodus"));
        baseWeapon.getLeftChild().getLeftChild().setMiddleChild(new LinkedWeaponTreeNode(new SwordAndShield(
                "Pukei Shell Machete", 170, 130, 0, 0),
                "Pukei-Pukei"));
        baseWeapon.getLeftChild().getRightChild().setMiddleChild(new LinkedWeaponTreeNode(new SwordAndShield(
                "Toxic Kris", 140, 150, 0.1, 0),
                "Pukei-Pukei"));
        baseWeapon.getRightChild().getLeftChild().setMiddleChild(new LinkedWeaponTreeNode(new SwordAndShield(
                "Flint Blade", 160, 145, 0, 0.1), "Qurupeco"));
        baseWeapon.getRightChild().getRightChild().setMiddleChild(new LinkedWeaponTreeNode(new SwordAndShield(
                "Quru Scale Saber", 150, 160, 0, 0), "Qurupeco"));
        super.setCurrentWeapon(baseWeapon);
    }

}
