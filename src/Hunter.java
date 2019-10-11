import java.util.ArrayList;

/**
 * This class creates a hunter.
 *
 * Date Last Modified: 10 / 03 / 2019
 *
 * @author Shirley Krogel
 */

public class Hunter {

    /**
     * Instance variables.
     */
    private int maxHp = 100;
    private double hp = 100;
    private int maxStamina = 100;
    private int stamina = 100;
    private String name;
    private Inventory inventory;
    private WeaponTree weaponPath;
    private Weapon weapon;
    private ArrayList<String> knownMonsters = new ArrayList<>();
    private boolean poisoned = false;
    private int poisonTick = 0;
    private boolean burning = false;
    private int burnTick = 0;
    private boolean bleeding = false;
    private int bleedTick = 0;
    private boolean muddy = false;
    private int mudBlightTick = 0;
    private int hpBuffTick = 0;
    private int staminaDrainTick = 0;
    private String position = "Front";
    private int currentLocation = 1;
    private boolean guarding = false;
    private boolean gathering = false;
    private boolean crafting = false;
    private MonsterHunter mh;

    public Hunter(String name, Inventory inventory, Weapon weapon, ArrayList<String> knownMonsters, MonsterHunter mh) {
        this.name = name;
        this.inventory = inventory;
        this.weapon = weapon;
        this.knownMonsters.addAll(knownMonsters);
        this.mh = mh;
    }

    /**
     * Getters and setters.
     *
     * @return
     */
    public int getMaxHp() {
        return maxHp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public boolean isPoisoned() {
        return poisoned;
    }

    public boolean isBurning() {
        return burning;
    }

    public boolean isBleeding(){return bleeding;}

    public boolean isMuddy(){return muddy;}

    public void setGuarding(boolean guard) {
        this.guarding = guard;
    }

    public boolean getGuarding() {
        return guarding;
    }

    public boolean getGathering() {
        return gathering;
    }

    public boolean getCrafting() {
        return crafting;
    }

    public void setGathering(boolean gathering) {
        this.gathering = gathering;
    }

    public void setCrafting(boolean crafting) {
        this.crafting = crafting;
    }

    public void setPoisoned(boolean poisoned) {
        this.poisoned = poisoned;
    }

    public void setBurning(boolean burning) {
        this.burning = burning;
    }

    public void setBleeding(boolean bleeding){this.bleeding = bleeding;}

    public void setMuddy(boolean muddy){this.muddy = muddy;}

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {this.position = position;}

    public int getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(int location) {
        this.currentLocation = location;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public double getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public int getStamina() {
        return stamina;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public WeaponTree getWeaponPath() {
        return weaponPath;
    }

    public void setWeaponTree(WeaponTree weapon) {
        this.weaponPath = weapon;
    }

    public ArrayList<String> getKnownMonsters() {
        return knownMonsters;
    }

    /**
     * Calculates stamina cost for guarding based on damage blocked.
     *
     * @return
     */
    public int guard(double damage) {
        int cost = 0;
        if (damage < 10) {
            cost = 10;
        }
        if (damage >= 10 && damage < 30) {
            cost = 20;
        }
        if (damage >= 30) {
            cost = 40;
        }
        return cost;
    }

    /**
     * Ticks poison damage for every turn the hunter is poisoned. Poison wears off after 15 turns.
     */
    private void poisonDamage() {
        if(poisoned) {
            if (poisoned && poisonTick <= 15) {
                hp -= 5;
                mh.updateLog(name + " took 5 damage from poison.");
                poisonTick++;
            } else {
                poisoned = false;
                poisonTick = 0;
                mh.updateLog(name + "'s poison wore off.");
            }
        } else {
            poisonTick = 0;
        }
    }

    /**
     * Ticks fire damage for every turn the hunter is burning. Burning wears off after 8 turns.
     */
    private void burnDamage() {
        if (burning) {
            if (burning && burnTick <= 8) {
                hp -= 10;
                mh.updateLog(name + " burned for 10 damage!");
                burnTick++;
            } else {
                burning = false;
                burnTick = 0;
                mh.updateLog(name + " managed to put out the flames.");
            }
        } else {
            burnTick = 0;
        }
    }

    public void checkBleeding() {
        if(bleeding) {
            if (bleeding && bleedTick <= 30) {
                hp -= 5;
                bleedTick++;
                mh.updateLog(name + " bled for 5 damage.");
            } else {
                bleeding = false;
                bleedTick = 0;
                mh.updateLog(name + "'s wound is closing up.");
            }
        } else {
            bleedTick = 0;
        }
    }

    public void tickBleeding() {
        if(bleeding) {
            if (bleeding && bleedTick <= 30) {
                bleedTick++;
            } else {
                bleeding = false;
                bleedTick = 0;
                mh.updateLog(name + "'s wound is closing up.");
            }
        } else {
            bleedTick = 0;
        }
    }

    private void checkMudBlight() {
        if (muddy) {
            if (muddy && mudBlightTick <= 4) {
                mudBlightTick++;
            } else {
                muddy = false;
                mudBlightTick = 0;
                mh.updateLog(name + " managed to shake off the last of the mud.");
            }
        } else {
            mudBlightTick = 0;
        }
    }

    /**
     * Checks that the hunter has enough stamina for certain actions.
     *
     * @param cost
     * @return
     */
    public boolean checkStaminaCost(int cost) {
        if ((stamina - cost) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Slowly regenerates the hunter's stamina overtime.
     */
    private void regenStamina() {
        if (stamina + 5 <= maxStamina) {
            stamina += 5;
        } else {
            stamina = maxStamina;
        }
    }

    /**
     * Slowly reduces the hunters max stamina. Can be regained by eating.
     */
    private void exhaustion() {
        if (staminaDrainTick >= 50) {
            if (maxStamina - 25 >= 50) {
                maxStamina -= 25;
            } else {
                maxStamina = 50;
            }
            staminaDrainTick = 0;
        } else {
            staminaDrainTick++;
        }
    }

    /**
     * Increases a hunter's max stamina.
     * @param buff
     */
    public void buffStamina(int buff) {
        if (maxStamina + buff <= 175) {
            maxStamina += buff;
            staminaDrainTick = 0;
        } else {
            maxStamina = 175;
            staminaDrainTick = 0;
        }
    }

    /**
     * Increases a hunter's max health.
     * @param buff
     */
    public void buffHp(int buff) {
        if (maxHp + buff <= 175) {
            maxHp += buff;
            hpBuffTick = 0;
        } else {
            maxHp = 175;
            hpBuffTick = 0;
        }
    }

    /**
     * Checks if buffs to health are expired.
     */
    private void checkHpBuff() {
        if (maxHp != 100 && hpBuffTick >= 100) {
            if (maxHp - 25 >= 100) {
                maxHp -= 25;
            } else {
                maxHp = 100;
            }
            if (hp > maxHp) {
                hp = maxHp;
            }
        } else {
            hpBuffTick++;
        }
    }

    /**
     * Updates the hunter's weapon to the active node in the weapon tree.
     */
    public void updateWeapon() {
        weapon = weaponPath.getCurrentWeapon().getWeapon();
    }

    /**
     * All of the methods that happen on a per turn basis in one method.
     */
    public void hunterTurn() {
        exhaustion();
        regenStamina();
        checkHpBuff();
        poisonDamage();
        burnDamage();
        checkMudBlight();
        tickBleeding();
    }
}
