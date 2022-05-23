package edu.ntnu.IDATT2001.charlohc.WarGames.UnitTest;

import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.RangedUnit;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class RangedUnitTest {
    RangedUnit rangedUnit1, rangedUnit2,rangedUnit3;

    @BeforeEach
    void reset(){
        try {
            rangedUnit1 = new RangedUnit("Ranged Unit one", 100, 15, 8);
            rangedUnit2 = new RangedUnit("Ranged Unit two", 100, 15, 8);
            rangedUnit3 = new RangedUnit("Ranged Unit three", 100, 15, 8);

            rangedUnit1.setTerrainTypes(TerrainTypesENUM.HILL);
            rangedUnit2.setTerrainTypes(TerrainTypesENUM.FOREST);
            rangedUnit3.setTerrainTypes(TerrainTypesENUM.PLAINS);

        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Nested
    @DisplayName("Tests of get resist bonus methode")
    class getResistBonus{
        @Test
        void GetResistBonusFirstAttack(){
          assertEquals(6,rangedUnit1.getResistBonus());
        }

        @Test
        void GetResistBonusSecondAttack(){
            rangedUnit1.getResistBonus();
            assertEquals(4,rangedUnit1.getResistBonus());
        }
        @Test
        void GetResistBonusThirdAttack(){
            rangedUnit1.getResistBonus();
            rangedUnit1.getResistBonus();
            assertEquals(2,rangedUnit1.getResistBonus());
        }
    }

    @Nested
    @DisplayName("Tests of get attack bonus methode")
    class getAttackBonus{

        @Test
        void getAttackBonusTerrainHill(){
            Assertions.assertEquals(6, rangedUnit1.getAttackBonus());
        }

        @Test
        void getAttackBonusTerrainForrest(){
            Assertions.assertEquals(1, rangedUnit2.getAttackBonus());
        }

        @Test
        void getAttackBonusTerrainPlains(){
            Assertions.assertEquals(3, rangedUnit3.getAttackBonus());
        }
    }

    @Nested
    @DisplayName("Tests of attack methode when the terrain type is hill")
    class attackTerrainHill {
        //Health - ((opponent)attack + (opponent)attack bonus) + (armour + resist bonus)
        // 100 - (15 + 6) + (8 + 6) = 93
        @Test
        void HealthAfterFirstAttackTerrainTypeHill() {
            rangedUnit1.attack(rangedUnit1);
            assertEquals(93, rangedUnit1.getHealth());
        }

        //93 - (15 + 6) + (8 + 4) = 84
        @Test
        void HealthAfterSecondAttackTerrainTypeHill() {
            rangedUnit1.attack(rangedUnit1);
            rangedUnit1.attack(rangedUnit1);
            assertEquals(84, rangedUnit1.getHealth());
        }

        //84 - (15 + 6) + (8 + 2) = 73
        @Test
        void HealthAfterThirdAttackTerrainTypeHill() {
            rangedUnit1.attack(rangedUnit1);
            rangedUnit1.attack(rangedUnit1);
            rangedUnit1.attack(rangedUnit1);
            assertEquals(73, rangedUnit1.getHealth());
        }
    }


    @Nested
    @DisplayName("Tests of attack methode when terrain type is forrest")
    class attackTerrainForrest {
        //100 - (15 + 1) + (8 + 6)
        @Test
        void HealthAfterFirstAttackTerrainTypeForrest() {
            rangedUnit2.attack(rangedUnit2);
            assertEquals(98, rangedUnit2.getHealth());
        }

        //98 - (15 + 1) + (8 + 4) = 94
        @Test
        void HealthAfterSecondAttackTerrainTypeForrest() {
            rangedUnit2.attack(rangedUnit2);
            rangedUnit2.attack(rangedUnit2);
            assertEquals(94, rangedUnit2.getHealth());
        }

        //94 - (15 + 1) + (8 + 2) = 88
        @Test
        void HealthAfterThirdAttackTerrainTypeForrest() {
            rangedUnit2.attack(rangedUnit2);
            rangedUnit2.attack(rangedUnit2);
            rangedUnit2.attack(rangedUnit2);
            assertEquals(88, rangedUnit2.getHealth());
        }
    }


    @Nested
    @DisplayName("Test attack methode when terrain type is plains")
    class attackTerrainPlains{
        //100 - (15 + 3 ) + (8 + 6) = 96
        @Test
        void HealthAfterFirstAttackTerrainTypePlains() {
            rangedUnit3.attack(rangedUnit3);
            assertEquals(96, rangedUnit3.getHealth());
        }

        //96 - (15 + 3 ) + (8 + 4) = 90
        @Test
        void HealthAfterSecondAttackTerrainTypePlains() {
            rangedUnit3.attack(rangedUnit3);
            rangedUnit3.attack(rangedUnit3);
            assertEquals(90, rangedUnit3.getHealth());
        }

        //90 - (15 + 3 ) + (8 + 2) = 82
        @Test
        void HealthAfterThirdAttackTerrainTypePlains() {
            rangedUnit3.attack(rangedUnit3);
            rangedUnit3.attack(rangedUnit3);
            rangedUnit3.attack(rangedUnit3);
            assertEquals(82, rangedUnit3.getHealth());
        }
    }

    @Test
    void differentTerrainsAttack(){
        assertThrows(IllegalArgumentException.class,() -> rangedUnit1.attack(rangedUnit3));
    }
}
