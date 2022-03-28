package edu.ntnu.IDATT2001.charlohc.WarGames.Unit;

import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.Unit;

/**
 * CavalryUnit Subclass of Unit
 */
public class CavalryUnit extends Unit {
     private int numberOfAttacks = 0;

    public CavalryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    public CavalryUnit(String name, int health) {
        super(name,health,20,12);
    }

    /**
     * Methode that gets the attack bonus, at first attack the bonus is six, after that the bonus will be two
     * @return int 6 or int 2, dependent on number of attack
     */
    @Override
    public int getAttackBonus() {
        numberOfAttacks++;
         if(numberOfAttacks == 1){
             return 6;
         }else {
             return 2;
         }
    }

    /**
     * Methode that gets the resistance bonus
     * @return int 4
     */
    @Override
    public int getResistBonus() {
        return 4;
    }
}
