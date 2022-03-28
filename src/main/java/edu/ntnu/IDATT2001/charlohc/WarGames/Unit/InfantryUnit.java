package edu.ntnu.IDATT2001.charlohc.WarGames.Unit;

import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.Unit;

/**
 * InfantryUnit subclass of Unit
 */
public class InfantryUnit extends Unit {

    public InfantryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack,armor);
    }

    public InfantryUnit(String name, int health) {
        super(name,health,15,10);
    }

    /**
     * Methode that gets the attack bonus
     * @return int 2
     */
    @Override
    public int getAttackBonus() {
        return 2;
    }

    /**
     * Methode that gets the resistance bonus
     * @return int 1
     */
    @Override
    public int getResistBonus() {
        return 1;
    }

}
