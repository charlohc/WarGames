package edu.ntnu.IDATT2001.charlohc.WarGames.Unit;


import edu.ntnu.IDATT2001.charlohc.WarGames.UnitFactory.UnitTypeENUM;

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

    @Override
    public UnitTypeENUM getUnitType() {
        return UnitTypeENUM.COMMANDER;
    }
}
