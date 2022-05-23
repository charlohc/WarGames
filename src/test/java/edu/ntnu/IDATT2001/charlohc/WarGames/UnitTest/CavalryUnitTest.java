package edu.ntnu.IDATT2001.charlohc.WarGames.UnitTest;
//ha test for attack get health
import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.CavalryUnit;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CavalryUnitTest {
CavalryUnit cavalryUnit1, cavalryUnit2, cavalryUnit3;

    @BeforeEach
    public void reset(){
    try {
        cavalryUnit1 = new CavalryUnit("Cavalry Unit One", 100, 20, 12);
        cavalryUnit2 = new CavalryUnit("Cavalry Unit Two", 100, 20, 12);
        cavalryUnit3 = new CavalryUnit("Cavalry Unit three",100,20,12);

        cavalryUnit1.setTerrainTypes(TerrainTypesENUM.FOREST);
        cavalryUnit2.setTerrainTypes(TerrainTypesENUM.HILL);
        cavalryUnit3.setTerrainTypes(TerrainTypesENUM.PLAINS);
    }catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

    @Nested
    @DisplayName("Tests of get resist bonus methode")
    class getResistBonus{
        @Test
        public void GetResistBonusTerrainForrest(){
            assertEquals(0,cavalryUnit1.getResistBonus());
        }

        @Test
        public void GetResistBonusTerrainHill() {
            assertEquals(1, cavalryUnit2.getResistBonus());
        }

        @Test
        public void getResistBonusTerrainPlains(){
            assertEquals(1, cavalryUnit3.getResistBonus());
        }
    }

    //Do I need to test for all terrains, or can i only test with two?
    @Nested
    @DisplayName("Tests of get attack bonus methode")
    class getAttackBonus{

            @Test
            public void getBonusFirstAttackTerrainForrest(){
                Assertions.assertEquals(6, cavalryUnit1.getAttackBonus());
            }
            @Test
            public void getBonusSecondAttackTerrainForrest(){
                cavalryUnit1.getAttackBonus();
                Assertions.assertEquals(2, cavalryUnit1.getAttackBonus());
            }

            @Test
            public void getBonusFirstAttackTerrainHill(){
                Assertions.assertEquals(6, cavalryUnit2.getAttackBonus());
            }
            @Test
            public void getBonusSecondAttackTerrainHill(){
                cavalryUnit2.getAttackBonus();
                Assertions.assertEquals(2, cavalryUnit2.getAttackBonus());
            }

            @Test
            public void getBonusFirstAttackTerrainPlains(){
                Assertions.assertEquals(8, cavalryUnit3.getAttackBonus());
            }

             @Test
            public void getBonusSecondAttackTerrainPlains(){
            cavalryUnit3.getAttackBonus();
            Assertions.assertEquals(4, cavalryUnit3.getAttackBonus());
        }
    }

    @Nested
    @DisplayName("Tests of attack methode when terrain type is forrest")
    class attackTerrainForrest {

        //Health - ((opponent)attack + (opponent)attack bonus) + (armour + resist bonus)
        //100 - (20 + 6) + (12 + 0) = 86
        @Test
        public void HealthAfterFirstAttackTerrainForrest() {
            cavalryUnit1.attack(cavalryUnit1);
            assertEquals(86,cavalryUnit1.getHealth());
        }

        //86 - (20 + 2) + (12 + 0) = 76
        @Test
        public void HealthAfterSecondAttackTerrainForrest() {
            cavalryUnit1.attack(cavalryUnit1);
            cavalryUnit1.attack(cavalryUnit1);
            assertEquals(76,cavalryUnit1.getHealth());
        }
    }

   @Nested
    @DisplayName("Tests of attack methode when terrain type is hill")
    class attackTerrainHill{

        //100 - (20 + 6) + (12 + 1) = 87
        @Test
       public void HealthAfterFirstAttackTerrainHill(){
            cavalryUnit2.attack(cavalryUnit2);
            Assertions.assertEquals(87,cavalryUnit2.getHealth());
        }

       //87 - (20 + 2) + (12 + 1) = 74
       @Test
        public void HealthAfterSecondAttackTerrainHill(){
            cavalryUnit2.attack(cavalryUnit2);
            cavalryUnit2.attack(cavalryUnit2);
            Assertions.assertEquals(78,cavalryUnit2.getHealth());
        }
   }

   @Nested
    @DisplayName("Tests of attack methode when terrain type is plains")
    class attackTerrainPlains{

        //100 - (20 + 8) + (12 + 1) = 85
       @Test
       public void HealthAfterFirstAttackTerrainPlains(){
           cavalryUnit3.attack(cavalryUnit3);
           Assertions.assertEquals(85, cavalryUnit3.getHealth());
       }

       //85 - (20 + 4) + (12 + 1) = 74
       @Test
       public void HealthAfterSecondAttackTerrainPlains(){
           cavalryUnit3.attack(cavalryUnit3);
           cavalryUnit3.attack(cavalryUnit3);
           Assertions.assertEquals(74, cavalryUnit3.getHealth());
       }
   }

   @Test
    public void differentTerrainsAttack(){
        assertThrows(IllegalArgumentException.class,() -> cavalryUnit1.attack(cavalryUnit2));
   }
}