package edu.ntnu.IDATT2001.charlohc.WarGames.Unit;

import edu.ntnu.IDATT2001.charlohc.WarGames.UnitFactory.UnitTypeENUM;


    @FunctionalInterface

    public interface UnitType{
        UnitTypeENUM getType(Unit u);
    }

