/**
 * This class is the base of all weapons.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class Weapon {

    private String name;
    private double damage;
    private double stun;
    private double multistrike;
    private int maxHits;
    private boolean guard;
    private int sharpness = 100;
    private int maxSharpness;
    private double poison;
    private double burn;
    private double totalDamage = 0;

    public Weapon(String name, double damage, double stun, double multistrike, int maxHits, boolean guard, int maxSharpness,
                  double poison, double burn) {
        this.name = name;
        this.damage = damage;
        this.stun = stun;
        this.multistrike = multistrike;
        this.maxHits = maxHits;
        this.guard = guard;
        this.maxSharpness = maxSharpness;
        this.poison = poison;
        this.burn = burn;
    }

    /**
     * Getters
     */
    /**
     * Reported to the defeat screen when the hunter is beaten.
     *
     * @return
     */
    public double getTotalDamage() {
        return totalDamage;
    }

    public String getName() {
        return name;
    }

    /**
     * Used to display current level of sharpness.
     *
     * @return
     */
    public String getSharpLevel() {
        if (sharpness > maxSharpness * .70) {
            return "Sharp";
        } else if (sharpness <= maxSharpness * .70 && sharpness > maxSharpness * .30) {
            return "Dull";
        } else {
            return "Nearly Broken";
        }
    }

    /**
     * Used to tell the program that the selected weapon can or cannot use the guard ability.
     *
     * @return
     */
    public boolean getGuard() {
        return guard;
    }

    /**
     * Calculates total damage for the turn.
     *
     * @return
     */
    public void attack(MonsterHunter mh) {
        double attackDamage = 0;
        if (checkHit()) {
            attackDamage = attackDamage + damage;
            sharpness = sharpness - 4;
            checkStun(mh);
            checkPoison(mh);
            checkBurning(mh);
        }

        for (int i = 0; i < maxHits; i++) {
            double multiChance = Math.random();
            if (multiChance <= multistrike) {
                if (checkHit()) {
                    attackDamage = attackDamage + damage;
                    sharpness = sharpness - 4;
                    checkStun(mh);
                    checkPoison(mh);
                    checkBurning(mh);
                }
            }
        }

        mh.updateLog(mh.getHunter().getName() + " hit the " + mh.getTargetMonster().getName() + " for " + (int) attackDamage + " damage.");
        mh.getTargetMonster().damaged(attackDamage);
        totalDamage = totalDamage + attackDamage;
    }

    /**
     * Checks if the weapon bounces off the monster's hide.
     *
     * @return
     */
    private boolean checkHit() {
        double toHit = Math.random();
        if (sharpness > maxSharpness * .70) {
            return true;
        } else if (sharpness <= maxSharpness * .70 && sharpness > maxSharpness * .30) {
            if (toHit <= 0.7) {
                return true;
            }
        } else {
            if (toHit <= 0.5) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the monster is stunned by the attack.
     * Only the hammer uses this currently.
     *
     * @param mh
     */
    private void checkStun(MonsterHunter mh) {
        double stunChance = Math.random();
        boolean stunMonster;

        if (stunChance < stun) {
            stunMonster = true;
        } else {
            stunMonster = false;
        }
        mh.getTargetMonster().setStunned(stunMonster);
    }

    /**
     * Checks if the attack caused poison.
     * @param mh
     */
    private void checkPoison(MonsterHunter mh) {
        double poisonChance = Math.random();
        boolean poisonMonster;

        if (poisonChance < poison) {
            poisonMonster = true;
        } else {
            poisonMonster = false;
        }
        mh.getTargetMonster().setPoisoned(poisonMonster, mh);
    }

    /**
     * Checks if the attack caused burning.
     * @param mh
     */
    private void checkBurning(MonsterHunter mh) {
        double burnChance = Math.random();
        boolean burnMonster;

        if (burnChance < burn) {
            burnMonster = true;
        } else {
            burnMonster = false;
        }
        mh.getTargetMonster().setBurning(burnMonster, mh);
    }

    /**
     * Sharpens the weapon, making it less likely to bounce off the monster.
     *
     * @return
     */
    public void sharpen() {
        this.sharpness = 100;
    }
}
