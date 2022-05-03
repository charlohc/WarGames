package edu.ntnu.IDATT2001.charlohc.WarGames.UnitTest;

import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.RangedUnit;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class RangedUnitTest {
    RangedUnit rangedUnit1, rangedUnit2,rangedUnit3;

    @BeforeEach
    public void reset(){
        try {
            rangedUnit1 = new RangedUnit("Ranged Unit one", 100, 15, 8);
            rangedUnit2 = new RangedUnit("Ranged Unit two", 100, 15, 8);
            rangedUnit3 = new RangedUnit("Ranged Unit three", 100, 15, 8);

            rangedUnit1.setTerrainTypes(TerrainTypesENUM.HILL);
            rangedUnit2.setTerrainTypes(TerrainTypesENUM.FORREST);
            rangedUnit3.setTerrainTypes(TerrainTypesENUM.PLAINS);

        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Nested
    @DisplayName("Tests of get resist bonus methode")
    class getResistBonus{
        @Test
        public void GetResistBonusFirstAttack(){
          assertEquals(6,rangedUnit1.getResistBonus());
        }

        @Test
        public void GetResistBonusSecondAttack(){
            rangedUnit1.getResistBonus();
            assertEquals(4,rangedUnit1.getResistBonus());
        }
        @Test
        public void GetResistBonusThirdAttack(){
            rangedUnit1.getResistBonus();
            rangedUnit1.getResistBonus();
            assertEquals(2,rangedUnit1.getResistBonus());
        }
    }

    @Nested
    @DisplayName("Tests of get attack bonus methode")
    class getAttackBonus{

        @Test
        public void getAttackBonusTerrainHill(){
            Assertions.assertEquals(6, rangedUnit1.getAttackBonus());
        }

        @Test
        public void getAttackBonusTerrainForrest(){
            Assertions.assertEquals(1, rangedUnit2.getAttackBonus());
        }

        @Test
        public void getAttackBonusTerrainPlains(){
            Assertions.assertEquals(3, rangedUnit3.getAttackBonus());
        }
    }

    @Nested
    @DisplayName("Tests of attack methode when the terrain type is hill")
    class attackTerrainHill {
        //Health - ((opponent)attack + (opponent)attack bonus) + (armour + resist bonus)
        // 100 - (15 + 6) + (8 + 6) = 93
        @Test
        public void HealthAfterFirstAttackTerrainTypeHill() {
            rangedUnit1.attack(rangedUnit1);
            assertEquals(93, rangedUnit1.getHealth());
        }

        //93 - (15 + 6) + (8 + 4) = 84
        @Test
        public void HealthAfterSecondAttackTerrainTypeHill() {
            rangedUnit1.attack(rangedUnit1);
            rangedUnit1.attack(rangedUnit1);
            assertEquals(84, rangedUnit1.getHealth());
        }

        //84 - (15 + 6) + (8 + 2) = 73
        @Test
        public void HealthAfterThirdAttackTerrainTypeHill() {
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
        public void HealthAfterFirstAttackTerrainTypeForrest() {
            rangedUnit2.attack(rangedUnit2);
            assertEquals(98, rangedUnit2.getHealth());
        }

        //98 - (15 + 1) + (8 + 4) = 94
        @Test
        public void HealthAfterSecondAttackTerrainTypeForrest() {
            rangedUnit2.attack(rangedUnit2);
            rangedUnit2.attack(rangedUnit2);
            assertEquals(94, rangedUnit2.getHealth());
        }

        //94 - (15 + 1) + (8 + 2) = 88
        @Test
        public void HealthAfterThirdAttackTerrainTypeForrest() {
            rangedUnit2.attack(rangedUnit2);
            rangedUnit2.attack(rangedUnit2);
            rangedUnit2.attack(rangedUnit2);
            assertEquals(88, rangedUnit2.getHealth());
        }
    }

}
