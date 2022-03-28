package edu.ntnu.IDATT2001.charlohc.WarGames.Unit;


/**
 * CommanderUnit subclass of CavalryUnit
 */
public class CommanderUnit extends CavalryUnit {

    public CommanderUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    public CommanderUnit(String name, int health) {
        super(name, health,25,15);
    }

}
