package edu.ntnu.IDATT2001.charlohc.WarGames;

import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.CavalryUnit;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.InfantryUnit;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.RangedUnit;
import org.junit.jupiter.api.*;

import java.util.Calendar;

//TODO: add terrain and more tests
class BattleTest {
    Army humans, orcs;
    Battle battle;
    InfantryUnit infantryUnit;
    RangedUnit rangedUnit;
    CavalryUnit cavalryUnit, cavalryUnit2;

    @BeforeEach
    public void reset() {
        humans = new Army("humans");
        orcs = new Army("Orcs");

        infantryUnit = new InfantryUnit("infantry",40,10,10);
        cavalryUnit = new CavalryUnit("cavalry orcs", 40,10,10);
        cavalryUnit2 = new CavalryUnit("cavalry humans", 40,10,10);
        rangedUnit = new RangedUnit("ranged",40,10,10);

        humans.addUnit(infantryUnit);
        humans.addUnit(cavalryUnit2);
        orcs.addUnit(rangedUnit);
        orcs.addUnit(cavalryUnit);

        battle = new Battle(humans,orcs, TerrainTypesENUM.PLAINS);
    }

    @Nested
    @DisplayName("Test of constructor")
    class constructorTest {
        @Test
        void getArmyOne() {
            Assertions.assertEquals(humans, battle.getArmyOne());
        }

        @Test
        void getArmyTwo(){
            Assertions.assertEquals(orcs,battle.getArmyTwo());
        }

        @Test
        void getTerrain(){
            Assertions.assertEquals(TerrainTypesENUM.PLAINS, battle.getTerrainTypes());
        }
    }


    @Test
    void getTerrainFromUnit(){
        Assertions.assertEquals(TerrainTypesENUM.PLAINS, humans.getInfantryUnits().get(0).getTerrainType());
    }

    @Test
    void testSimulate() throws InterruptedException {
        Assertions.assertTrue(battle.simulate().equals(humans) || battle.simulate().equals(orcs));
        System.out.println(battle.simulate());
    }

}

