package edu.ntnu.IDATT2001.charlohc.WarGames.UnitFactory;

import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

class UnitFactoryTest {
CavalryUnit cavalryUnit;
CommanderUnit commanderUnit;
InfantryUnit infantryUnit;
RangedUnit rangedUnit;
List<Unit> listCavalryUnits;
UnitFactory unitFactory;

    @BeforeEach
    void setUp() {
        unitFactory = new UnitFactory();
        cavalryUnit = (CavalryUnit) unitFactory.createUnitByType(UnitTypeENUM.CAVALRY,"Cavalry",100);
        commanderUnit = (CommanderUnit) unitFactory.createUnitByType(UnitTypeENUM.COMMANDER,"Commander",90);
        infantryUnit = (InfantryUnit) unitFactory.createUnitByType(UnitTypeENUM.INFANTRY,"Infantry",80);
        rangedUnit = (RangedUnit) unitFactory.createUnitByType(UnitTypeENUM.RANGED,"Ranged",100);

        listCavalryUnits = unitFactory.createListOfUnits(4,UnitTypeENUM.CAVALRY,"Cavalry units",50);
    }

    @Nested
    @DisplayName("Test createUnitByType method in UnitFactory class")
    class testCreateUnitByTypeMethod {
        @Test
        void correctUnitType() {
            Assertions.assertEquals(UnitTypeENUM.RANGED, rangedUnit.getUnitType());
        }

        @Test
        void getName() {
            Assertions.assertEquals("Cavalry", cavalryUnit.getName());
        }

        @Test
        void getHealth() {
            Assertions.assertEquals(100, cavalryUnit.getHealth());
        }

        @Test
        void setHealth() {
            cavalryUnit.setHealth(50);
            Assertions.assertEquals(50, cavalryUnit.getHealth());
        }

        @Test
        void getArmour() {
            Assertions.assertEquals(12, cavalryUnit.getArmor());
        }

        @Test
        void getAttack() {
            Assertions.assertEquals(20, cavalryUnit.getAttackValue());
        }
    }

    @Nested
    @DisplayName("Test createListOfUnits method in UnitFactory class")
    class testCreateListOfUnitsMethod{

        @Test
        void getName(){
            for (Unit listCavalryUnit : listCavalryUnits) {
                Assertions.assertEquals("Cavalry units", listCavalryUnit.getName());
            }
        }

        @Test
        void getType(){
            for (Unit listCavalryUnit : listCavalryUnits) {
                Assertions.assertEquals(UnitTypeENUM.CAVALRY, listCavalryUnit.getUnitType());
            }
        }

        @Test
        void getHealth(){
            for (Unit listCavalryUnit : listCavalryUnits) {
                Assertions.assertEquals(50, listCavalryUnit.getHealth());
            }
        }

        @Test
        void getSize(){
            Assertions.assertEquals(4, listCavalryUnits.size());
        }

    }
    
    @Nested
    @DisplayName("Negative tests")
    class negative {
        @Test
        void setNegativeHealth() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> cavalryUnit.setHealth(-1));
        }

        @Test
        @DisplayName("Throw Exception when the name parameter is null")
        void nameNullException() {
            Assertions.assertThrows(NullPointerException.class, () -> unitFactory.createUnitByType(UnitTypeENUM.INFANTRY, null, 100));
        }

        @Test
        @DisplayName("Throw Exception when the unit type parameter is null")
        void typeNullException() {
            Assertions.assertThrows(NullPointerException.class, () -> unitFactory.createUnitByType(null, "Cavalry", 100));
        }

        @Test
        @DisplayName("Throw Exception when the health int in parameter is negative")
        void negativeHealthException() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> unitFactory.createUnitByType(UnitTypeENUM.RANGED, "ranged", -1));
        }

    }
}