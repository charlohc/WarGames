package edu.ntnu.IDATT2001.charlohc.WarGames.UnitFactory;

import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.*;

public class UnitFactory {

    public Unit createUnitByType(UnitTypeENUM unitType,String name, int health) {
        Unit unit;

        return switch (unitType) {
            case CAVALRY -> new CavalryUnit(name, health);
            case COMMANDER -> new CommanderUnit(name, health);
            case INFANTRY -> new InfantryUnit(name, health);
            case RANGED -> new RangedUnit(name, health);
            default -> throw new IllegalArgumentException("Unexpected value: ");
        };
    }
}

