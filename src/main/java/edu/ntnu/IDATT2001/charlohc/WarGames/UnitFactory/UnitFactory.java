package edu.ntnu.IDATT2001.charlohc.WarGames.UnitFactory;

import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.*;

import java.util.ArrayList;
import java.util.List;

public class UnitFactory {

    public Unit createUnitByType(UnitTypeENUM unitType,String name, int health) {

        return switch (unitType) {
            case CAVALRY -> new CavalryUnit(name, health);
            case COMMANDER -> new CommanderUnit(name, health);
            case INFANTRY -> new InfantryUnit(name, health);
            case RANGED -> new RangedUnit(name, health);
        };
    }

    public List<Unit> createListOfUnits(int numberOfUnits, UnitTypeENUM unitType, String name, int health) throws IllegalArgumentException{
        ArrayList<Unit> newListOfUnits = new ArrayList<>();
        for (int i = 0; i < numberOfUnits; i++) {
            newListOfUnits.add(createUnitByType(unitType, name+" "+(i+1), health));
        }
        return newListOfUnits;
    }

}

