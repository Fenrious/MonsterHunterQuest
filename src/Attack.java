/**
 * This class creates a customizable attack used by monsters.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class Attack {

    private String monsterName;
    private double baseDamage;
    private double mainModifier;
    private double mainHitChance;
    private double grazeModifier;
    private double grazeHitChance;
    private Status mainStatus;
    private Status grazeStatus;
    private int attackPosition;
    private int hunterPosition;
    private String description;
    private Hunter hunter;
    private MonsterHunter mh;

    public Attack(String monsterName, double base, double mainModifier, double mainHitChance, double grazeModifier,
                  double grazeHitChance, Status mainStatus, Status grazeStatus,
                  int attackPosition, String description, Hunter hunter, MonsterHunter mh) {
        this.monsterName = monsterName;
        this.baseDamage = base;
        this.mainModifier = mainModifier;
        this.mainHitChance = mainHitChance;
        this.grazeModifier = grazeModifier;
        this.grazeHitChance = grazeHitChance;
        this.mainStatus = mainStatus;
        this.grazeStatus = grazeStatus;
        this.attackPosition = attackPosition;
        this.description = description;
        this.hunter = hunter;
        this.mh = mh;
    }

    /**
     * Changes the base damage for purposes of changing a monster's difficulty.
     *
     * @param damage
     */
    public void setBaseDamage(double damage) {
        baseDamage = damage;
    }

    /**
     * Changes the Status/chance for status for a direct hit.
     * @param main
     */
    public void setMainStatus(Status main) {
        this.mainStatus = main;
    }

    /**
     * Changes the Status/chance for status for a graze hit.
     * @param graze
     */
    public void setGrazeStatus(Status graze) {
        this.grazeStatus = graze;
    }

    /**
     * Translates the hunter's position into a number.
     */
    private void convertHunterPosition() {
        if (hunter.getPosition().equals("Front")) {
            hunterPosition = 1;
        } else if (hunter.getPosition().equals("Right")) {
            hunterPosition = 4;
        } else if (hunter.getPosition().equals("Back")) {
            hunterPosition = 3;
        } else {
            hunterPosition = 2;
        }
    }

    /**
     * Handles the effects caused by attacking. Reports certain things tot he log in the monster hunter program.
     * @return
     */
    public double processAttack() {
        mh.updateLog(description);
        double attackDamage = 0;
        double hitChance = Math.random();
        convertHunterPosition();
        if (hunterPosition == attackPosition && hitChance <= mainHitChance) {
            attackDamage = baseDamage * mainModifier;
            mainStatus.checkStatus(mh);
        } else if ((hunterPosition == attackPosition - 1 || hunterPosition == attackPosition + 1) && hitChance <= grazeHitChance) {
            attackDamage = baseDamage * grazeModifier;
            grazeStatus.checkStatus(mh);
        } else if (attackPosition - 1 == 0) {
            if (hunterPosition == 4 && hitChance <= grazeHitChance) {
                attackDamage = baseDamage * grazeModifier;
                grazeStatus.checkStatus(mh);
            }
        } else if (attackPosition + 1 == 5) {
            if (hunterPosition == 1 && hitChance <= grazeHitChance) {
                attackDamage = baseDamage * grazeModifier;
                grazeStatus.checkStatus(mh);
            }
        }
        if (hunter.getGuarding() && hunter.checkStaminaCost(hunter.guard(attackDamage))) {
            hunter.setStamina(hunter.getStamina() - hunter.guard(attackDamage));
            mh.updateLog(hunter.getName() + " successfully blocked the attack!");
            return 0;
        } else {
            if (hunter.getGuarding()) {
                mh.updateLog(hunter.getName() + "'s guard was broken!");
            }
            if (attackDamage > 0) {
                hunter.setHp(hunter.getHp() - attackDamage);
                mh.updateLog(hunter.getName() + " was hit for " + attackDamage + " damage!");
            } else {
                mh.updateLog("The " + monsterName + " missed!");
            }
            return attackDamage;
        }
    }
}