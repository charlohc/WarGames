package edu.ntnu.IDATT2001.charlohc.WarGames;

import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//TODO: go trough and make sure that does not miss any tests + test with terrains
public class ArmyGetMethodsTest {
    Army army;
    Unit cavalryUnit, infantryUnit, commanderUnit, rangedUnit, cavalryUnit2;

    @BeforeEach
    public void reset() {
        try {
            army = new Army("Army");
            cavalryUnit = new CavalryUnit("Cavalry Unit", 100);
            infantryUnit = new InfantryUnit("Infantry Unit", 100);
            commanderUnit = new CommanderUnit("Commander Unit",100);
            rangedUnit = new RangedUnit("Ranged Unit",100);
            cavalryUnit2 = new CavalryUnit("Cavalry Unit two",100);

            //rangedUnit.setTerrainTypes(TerrainTypesENUM.PLAINS);

            army.addUnit(cavalryUnit);
            army.addUnit(infantryUnit);
            army.addUnit(commanderUnit);
            army.addUnit(rangedUnit);
            army.addUnit(cavalryUnit2);

        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Test
    public void getInfantryUnitTest(){
        Assertions.assertEquals(1,army.getInfantryUnits().size());
    }

    @Test
    public void getCavalryUnitTest(){
        Assertions.assertEquals(2,army.getCavalryUnits().size());
    }

    @Test
    public void getRangedUnitTest(){
        Assertions.assertEquals(1,army.getRangedUnits().size());
    }

    @Test
    public void getCommanderUnitTest(){
        Assertions.assertEquals(1, army.getCommanderUnits().size());
    }
}

/*
 @Test
    public void getTerrainRangedUnit(){
        Assertions.assertEquals(TerrainTypesENUM.PLAINS,army.getRangedUnits().get(0).getTerrainType());
        army.getAllUnits().get(0).getTerrainType();
    }
 */