import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * This class serves as a base for all monsters in the game.
 *
 * Date Last Modified: 08 / 20 / 2019
 *
 * @author Shirley Krogel
 */

public class Monster {

    /**
     * Instance variables.
     */
    private String name;
    private double hp;
    private double chase;
    private int windUp = 0;
    private double totalDamage = 0;
    private String targetPosition;
    private boolean poisoned = false;
    private int poisonTick = 0;
    private boolean stunned = false;
    private boolean burning = false;
    private int burnTick = 0;
    private Image front;
    private Attack frontAttack;
    private Image leftSide;
    private Attack leftAttack;
    private Image rightSide;
    private Attack rightAttack;
    private Image backSide;
    private Attack backAttack;
    private int wanderCooldown = (int) (Math.random() * 12 + 9);
    private int currentLocation;
    private int destination = 0;
    private ArrayList<Zone> currentPath = new ArrayList<Zone>();
    BreadthFirstSearch search = new BreadthFirstSearch();
    private int i = 0;

    public Monster(String name, double hp, double chase, Image front, Attack frontAttack,
                   Image leftSide, Attack leftAttack, Image rightSide, Attack rightAttack, Image backSide,
                   Attack backAttack, int startingLocation) {
        this.name = name;
        this.hp = hp;
        this.chase = chase;
        this.front = front;
        this.frontAttack = frontAttack;
        this.leftSide = leftSide;
        this.leftAttack = leftAttack;
        this.rightSide = rightSide;
        this.rightAttack = rightAttack;
        this.backSide = backSide;
        this.backAttack = backAttack;
        this.currentLocation = startingLocation;
    }

    /**
     * Getters and setters.
     */
    public void setPoisoned(boolean poisoned, MonsterHunter mh) {
        if (poisoned && poisonTick == 0) {
            this.poisoned = poisoned;
            mh.updateLog(name + " was poisoned!");

        }
    }

    public void setStunned(boolean stun) {
        stunned = stun;
    }

    public void setBurning(boolean burn, MonsterHunter mh) {
        if (burn && burnTick == 0) {
            burning = burn;
            mh.updateLog(name + " began to burn!");
        }
    }

    public String getName() {
        return name;
    }

    public double getTotalDamage() {
        return totalDamage;
    }

    public int getCurrentLocation() {
        return currentLocation;
    }

    public double getHP() {
        return hp;
    }

    public void setHP(double hp) {
        this.hp = hp;
    }

    public Attack getFrontAttack() {
        return frontAttack;
    }

    public Attack getRightAttack() {
        return rightAttack;
    }

    public Attack getBackAttack() {
        return backAttack;
    }

    public Attack getLeftAttack() {
        return leftAttack;
    }

    /**
     * Difficulty changing methods specific to each monster.
     */
    public void easyMode() {
    }

    public void hardMode() {
    }

    public void challengeMode() {
    }

    /**
     * Returns the image of the current side based on the position of the monster.
     * @param hunter
     * @return
     */
    public Image getCurrentSide(String hunter) {
        if (hunter.equals("Front")) {
            return front;
        } else if (hunter.equals("Left")) {
            return leftSide;
        } else if (hunter.equals("Back")) {
            return backSide;
        } else {
            return rightSide;
        }
    }

    /**
     * Records damage dealt to the monster.
     *
     * @param hit
     */
    public void damaged(double hit) {
        hp = hp - hit;
    }

    /**
     * Allows the monster to be healed.
     * @param heal
     */
    public void healed(double heal) {
        hp += heal;
    }

    /**
     * Causes poison damage while the monster is poisoned.
     */
    public void poisonDamage() {
        if (poisoned && poisonTick <= 15) {
            hp = hp - 5;
            poisonTick++;
        } else {
            poisoned = false;
        }
        if (!poisoned) {
            poisonTick = 0;
        }
    }

    /**
     * Causes burn damage while the monster is burning.
     */
    public void burnDamage() {
        if (burning && burnTick <= 8) {
            hp = hp - 10;
            burnTick++;
        } else {
            burning = false;
        }
        if (!burning) {
            burnTick = 0;
        }
    }

    /**
     * Checks if the hunter can escape the monster.
     *
     * @return
     */
    public boolean checkEscape() {
        boolean escaped = true;
        double chaseChance = Math.random();
        if (chaseChance < chase) {
            escaped = false;
        }

        return escaped;
    }

    /**
     * Overall method to react to being stunned, decide direction of attacks, and implement a one turn delay on attacks.
     *
     * @param mh
     * @param h
     */
    public void attack(MonsterHunter mh, Hunter h) {
        if (stunned) {
            windUp = 0;
            mh.updateLog("The " + name + " was stunned and is unable to attack!");
            stunned = false;
            return;
        }
        if (currentLocation != h.getCurrentLocation()) {
            windUp = 0;
        }
        if (currentLocation == h.getCurrentLocation()) {
            if (windUp == 0) {
                targetPosition = h.getPosition();
                if (targetPosition.equals("Front")) {
                    mh.updateLog(name + " prepares to attack the hunter in front of it!");
                } else if (targetPosition.equals("Left")) {
                    mh.updateLog(name + " prepares to attack the hunter to its right!");
                } else if (targetPosition.equals("Back")) {
                    mh.updateLog(name + " prepares the attack the hunter behind it!");
                } else {
                    mh.updateLog(name + " prepares to attack the hunter to its left!");
                }
                windUp++;
                return;
            }
            if (targetPosition.equals("Front")) {
                totalDamage += frontAttack.processAttack();
                windUp = 0;
            } else if (targetPosition.equals("Right")) {
                totalDamage += leftAttack.processAttack();
                windUp = 0;
            } else if (targetPosition.equals("Back")) {
                totalDamage += backAttack.processAttack();
                windUp = 0;
            } else {
                totalDamage += rightAttack.processAttack();
                windUp = 0;
            }
        }
    }

    /**
     * Stops the monster from continuing on. Selects a new destination for it to attempt to reach.
     * @param mh
     * @param path
     */
    private void halt(MonsterHunter mh, Pathing path) {
        destination = (int) (Math.random() * 9 + 1);
        currentPath = search.breadthFirstSearch(path, path.getZone(currentLocation - 1), path.getZone(destination - 1));
        i = 0;
        windUp = 0;
        currentLocation = currentPath.get(i).getName();
        wanderCooldown = (int) (Math.random() * 15 + 12);
    }


    /**
     * Causes the monster to sometimes change their location and causes the monster to roar when it reaches
     * its destination. Monster will not roar in the hunters ears because earplugs are not implemented.
     *
     * @param path
     */
    public boolean move(MonsterHunter mh, Pathing path) {
        if (wanderCooldown > 0) {
            wanderCooldown--;
        } else if (currentLocation == destination || currentPath.size() - 1 == i) {
            halt(mh, path);
            return true;
        } else {
            if (currentPath.size() > 0 && mh.getAreaMap().getZone(currentPath.get(i + 1).getName() - 1).getMonster() == null) { // Array gets to 2/2 instead of 1/2
                currentLocation = currentPath.get(i + 1).getName();
                currentPath.get(i + 1).setMonster(this);
                currentPath.get(i).setMonster(null);
                mh.getOpenLocations().add(currentPath.get(i).getName());
                mh.getOpenLocations().remove(Integer.valueOf(currentPath.get(i + 1).getName()));
                i++;
                windUp = 0;
            } else {
                halt(mh, path);
                return true;
            }
        }
        return false;
    }

    /**
     * Uses pre-made directions from the map to determine its relative direction compared to the hunter.
     *
     * @param mh
     * @param direction
     * @return
     */
    public void roar(MonsterHunter mh, ArrayList<Directions> direction) {
        if (currentLocation == mh.getHunter().getCurrentLocation()) {
            mh.updateLog("The " + name + " growls menacingly.");
        } else {
            int j = 0;
            while (!(direction.get(j).getLocation1() == mh.getHunter().getCurrentLocation()
                    && direction.get(j).getLocation2() == currentLocation)) {
                j++;
            }
            mh.updateLog("A mighty roar sounded to the " + direction.get(j).getDirection() + ".");
        }
    }

    /**
     * Keeps the general per turn methods in one method. Move is excluded to allow the main program to handle
     * making only the main monster roar.
     */
    public void monsterStatus() {
        poisonDamage();
        burnDamage();
    }

}
