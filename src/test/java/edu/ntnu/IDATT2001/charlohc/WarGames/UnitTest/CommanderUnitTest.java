package edu.ntnu.IDATT2001.charlohc.WarGames.UnitTest;

//ha test for attack get health
import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.CavalryUnit;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.CommanderUnit;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CommanderUnitTest {
    CommanderUnit commanderUnit1, commanderUnit2, commanderUnit3;

    @BeforeEach
    public void reset() {
        try {
            commanderUnit1 = new CommanderUnit("Commander Unit One", 100, 20, 12);
            commanderUnit2 = new CommanderUnit("Commander Unit Two", 100, 20, 12);
            commanderUnit3 = new CommanderUnit("Commander Unit three", 100, 20, 12);

            commanderUnit1.setTerrainTypes(TerrainTypesENUM.FORREST);
            commanderUnit2.setTerrainTypes(TerrainTypesENUM.HILL);
            commanderUnit3.setTerrainTypes(TerrainTypesENUM.PLAINS);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Nested
    @DisplayName("Tests of get resist bonus methode")
    class getResistBonus {
        @Test
        public void GetResistBonusTerrainForrest() {
            assertEquals(0, commanderUnit1.getResistBonus());
        }

        @Test
        public void GetResistBonusTerrainHill() {
            assertEquals(1, commanderUnit2.getResistBonus());
        }

        @Test
        public void getResistBonusTerrainPlains() {
            assertEquals(1, commanderUnit3.getResistBonus());
        }
    }

    //Do I need to test for all terrains, or can i only test with two?
    @Nested
    @DisplayName("Tests of get attack bonus methode")
    class getAttackBonus {

        @Test
        public void getBonusFirstAttackTerrainForrest() {
            Assertions.assertEquals(6, commanderUnit1.getAttackBonus());
        }

        @Test
        public void getBonusSecondAttackTerrainForrest() {
            commanderUnit1.getAttackBonus();
            Assertions.assertEquals(2, commanderUnit1.getAttackBonus());
        }

        @Test
        public void getBonusFirstAttackTerrainHill() {
            Assertions.assertEquals(6, commanderUnit2.getAttackBonus());
        }

        @Test
        public void getBonusSecondAttackTerrainHill() {
            commanderUnit2.getAttackBonus();
            Assertions.assertEquals(2, commanderUnit2.getAttackBonus());
        }

        @Test
        public void getBonusFirstAttackTerrainPlains() {
            Assertions.assertEquals(8, commanderUnit3.getAttackBonus());
        }

        @Test
        public void getBonusSecondAttackTerrainPlains() {
            commanderUnit3.getAttackBonus();
            Assertions.assertEquals(4, commanderUnit3.getAttackBonus());
        }
    }

    @Nested
    @DisplayName("Tests of attack methode when terrain type is forrest")
    class attackTerrainForrest {

        //Health - ((opponent)attack + (opponent)attack bonus) + (armour + resist bonus)
        //100 - (20 + 6) + (12 + 0) = 86
        @Test
        public void HealthAfterFirstAttackTerrainForrest() {
            commanderUnit1.attack(commanderUnit1);
            assertEquals(86, commanderUnit1.getHealth());
        }

        //86 - (20 + 2) + (12 + 0) = 76
        @Test
        public void HealthAfterSecondAttackTerrainForrest() {
            commanderUnit1.attack(commanderUnit1);
            commanderUnit1.attack(commanderUnit1);
            assertEquals(76, commanderUnit1.getHealth());
        }
    }

    @Nested
    @DisplayName("Tests of attack methode when terrain type is hill")
    class attackTerrainHill {

        //100 - (20 + 6) + (12 + 1) = 87
        @Test
        public void HealthAfterFirstAttackTerrainHill() {
            commanderUnit2.attack(commanderUnit2);
            Assertions.assertEquals(87, commanderUnit2.getHealth());
        }

        //87 - (20 + 2) + (12 + 1) = 74
        @Test
        public void HealthAfterSecondAttackTerrainHill() {
            commanderUnit2.attack(commanderUnit2);
            commanderUnit2.attack(commanderUnit2);
            Assertions.assertEquals(78, commanderUnit2.getHealth());
        }
    }

    @Nested
    @DisplayName("Tests of attack methode when terrain type is plains")
    class attackTerrainPlains {

        //100 - (20 + 8) + (12 + 1) = 85
        @Test
        public void HealthAfterFirstAttackTerrainPlains() {
            commanderUnit3.attack(commanderUnit3);
            Assertions.assertEquals(85, commanderUnit3.getHealth());
        }

        //85 - (20 + 4) + (12 + 1) = 74
        @Test
        public void HealthAfterSecondAttackTerrainPlains() {
            commanderUnit3.attack(commanderUnit3);
            commanderUnit3.attack(commanderUnit3);
            Assertions.assertEquals(74, commanderUnit3.getHealth());
        }
    }

    @Test
    public void differentTerrainsAttack() {
        assertThrows(IllegalArgumentException.class, () -> commanderUnit1.attack(commanderUnit2));
    }
}
