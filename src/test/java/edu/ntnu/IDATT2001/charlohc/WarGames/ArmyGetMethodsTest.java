package edu.ntnu.IDATT2001.charlohc.WarGames;

import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArmyGetMethodsTest {
    Army army,army2;
    Unit cavalryUnit, infantryUnit, commanderUnit, rangedUnit, cavalryUnit2;

    @BeforeEach
    void reset() {
            army = new Army("Army");
            cavalryUnit = new CavalryUnit("Cavalry Unit", 100);
            infantryUnit = new InfantryUnit("Infantry Unit", 100);
            commanderUnit = new CommanderUnit("Commander Unit",100);
            rangedUnit = new RangedUnit("Ranged Unit",100);
            cavalryUnit2 = new CavalryUnit("Cavalry Unit two",100);


            army.addUnit(cavalryUnit);
            army.addUnit(infantryUnit);
            army.addUnit(commanderUnit);
            army.addUnit(rangedUnit);
            army.addUnit(cavalryUnit2);

            army2 = new Army("Army 2");
            cavalryUnit2 = new CavalryUnit("CavalryUnit 2",100);
            army2.addUnit(cavalryUnit2);
            cavalryUnit.setTerrainTypes(TerrainTypesENUM.PLAINS);
            cavalryUnit2.setTerrainTypes(TerrainTypesENUM.PLAINS);

    }

    @Test
    void getInfantryUnitTest(){
        Assertions.assertEquals(1,army.getInfantryUnits().size());
    }

    @Test
    void getCavalryUnitTest(){
        Assertions.assertEquals(2,army.getCavalryUnits().size());
    }

    @Test
    void getRangedUnitTest(){
        Assertions.assertEquals(1,army.getRangedUnits().size());
    }

    @Test
    void getCommanderUnitTest(){
        Assertions.assertEquals(1, army.getCommanderUnits().size());
    }
}
