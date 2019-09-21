import javafx.scene.image.Image;

/**
 * This class serves as a base for small monsters in the game.
 *
 * Date Last Modified: 09 / 20 / 2019
 *
 * @author Shirley Krogel
 */

public class SmallMonster extends Monster {

    private int maxPopulation;
    private int currentPopulation;
    private int regenerateTick = 0;
    private double unitHP;
    private boolean memberDied = false;

    public SmallMonster(String name, double hp, double chase, Image front, Attack frontAttack,
                        Image rightSide, Attack rightAttack, Image backSide, Attack backAttack, Image leftSide,
                        Attack leftAttack, int startingLocation, int population) {
        super(name, hp * population, chase, front, frontAttack, rightSide, rightAttack, backSide, backAttack,
                leftSide, leftAttack, startingLocation);
        this.maxPopulation = population;
        this.currentPopulation = population;
        unitHP = hp;
    }

    public void setMemberDied(boolean value){memberDied = value;}

    public boolean getMemberDied(){return memberDied;}

    /**
     * Allows the grouping of small monsters to repopulate themselves over time.
     */
    private void repopulate() {
        if (currentPopulation == maxPopulation) {
            regenerateTick = 0;
        } else if (regenerateTick >= 200) {
            currentPopulation++;
            super.healed(unitHP);
            regenerateTick = 0;
        } else {
            regenerateTick++;
        }
    }

    private void checkPopulation(){
        if(getHP() <= (currentPopulation - 1) * unitHP){
            memberDied = true;
            currentPopulation--;
        }
    }

    public void smallMonsterTurn() {
        repopulate();
        checkPopulation();
    }

}
