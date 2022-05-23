package edu.ntnu.IDATT2001.charlohc.WarGames.UnitTest;

import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.InfantryUnit;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class InfantryUnitTest {
InfantryUnit infantryUnit1, infantryUnit2,infantryUnit3;

    @BeforeEach
    public void reset(){
    try{
        infantryUnit1 = new InfantryUnit("Infantry Unit one", 100, 15,10);
        infantryUnit2 = new InfantryUnit("Infantry Unit two",100,15,10);
        infantryUnit3 = new InfantryUnit("Infantry Unit three",100,15,10);

        infantryUnit1.setTerrainTypes(TerrainTypesENUM.FOREST);
        infantryUnit2.setTerrainTypes(TerrainTypesENUM.PLAINS);
        infantryUnit3.setTerrainTypes(TerrainTypesENUM.HILL);

    }catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

    @Nested
    @DisplayName("Tests of get resist bonus methode")
    class getResistBonus{
        @Test
        void GetResistBonusTerrainForrest(){
            assertEquals(3,infantryUnit1.getResistBonus());
        }

        @Test
        void GetResistBonusTerrainHill(){
            infantryUnit2.getResistBonus();
            assertEquals(1,infantryUnit2.getResistBonus());
        }
    }
//Do I need to test for all terrains, or can i only test with two?
    @Nested
    @DisplayName("Tests of get attack bonus methode")
    class getAttackBonus {

        @Test
        void getAttackBonusTerrainForrest() {
            Assertions.assertEquals(2, infantryUnit2.getAttackBonus());
        }

        @Test
        void getAttackBonusTerrainHill() {
            Assertions.assertEquals(6, infantryUnit1.getAttackBonus());
        }

    }
    @Nested
    @DisplayName("Tests of attack methode when the terrain type is forrest")
    class attackTerrainForrest {
        @Test
        @DisplayName("Health after first attack infantry unit with terrain type forrest")
        void HealthAfterFirstAttackTerrainTypeForrest() {
            infantryUnit1.attack(infantryUnit1);
            assertEquals(infantryUnit1.getHealth(), 92);
        }

        //
        @Test
        @DisplayName("Health after second attack infantry unit with terrain type forrest")
        void HealthAfterSecondAttackTerrainTypeForrest() {
            infantryUnit1.attack(infantryUnit1);
            infantryUnit1.attack(infantryUnit1);
            assertEquals(infantryUnit1.getHealth(), 84);
        }
    }

    @Nested
    @DisplayName("Tests of attack methode when the terrain type is hill")
    class attackTerrainHill {
        @Test
        @DisplayName("Health after first attack infantry unit with terrain type hill")
        void HealthAfterFirstAttackTerrainTypeHill() {
            infantryUnit3.attack(infantryUnit3);
            assertEquals(infantryUnit3.getHealth(), 94);
        }

        @Test
        @DisplayName("Health after second attack infantry unit with terrain type hill")
        void HealthAfterSecondAttackTerrainTypeHille() {
            infantryUnit3.attack(infantryUnit3);
            infantryUnit3.attack(infantryUnit3);
            assertEquals(infantryUnit3.getHealth(), 88);
        }
    }

    @Nested
    @DisplayName("Tests of attack methode when the terrain type is plains")
    class attackTerrainPlains {
        @Test
        @DisplayName("Health after first attack infantry unit with terrain type plains")
        void HealthAfterFirstAttackTerrainTypePlains() {
            infantryUnit2.attack(infantryUnit2);
            assertEquals(infantryUnit2.getHealth(), 94);
        }

        @Test
        @DisplayName("Health after second attack infantry unit with terrain type plains")
        void HealthAfterSecondAttackTerrainTypePlains() {
            infantryUnit2.attack(infantryUnit2);
            infantryUnit2.attack(infantryUnit2);
            assertEquals(infantryUnit2.getHealth(), 88);
        }
    }

    @Test
    void differentTerrainsAttack(){
        assertThrows(IllegalArgumentException.class,() -> infantryUnit1.attack(infantryUnit3));
    }

}