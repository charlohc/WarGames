package edu.ntnu.IDATT2001.charlohc.WarGames.UnitTest;
//ha test for attack get health
import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.CavalryUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CavalryUnitTest {
CavalryUnit cavalryUnit1, cavalryUnit2, cavalryUnit3;

    @BeforeEach
    public void reset(){
    try {
        cavalryUnit1 = new CavalryUnit("Cavalry Unit One", 100, 20, 12);
        cavalryUnit2 = new CavalryUnit("Cavalry Unit Two", 100, 20, 12);
        cavalryUnit3 = new CavalryUnit("Cavalry Unit three",100,20,12);
        cavalryUnit1.setTerrainTypes(TerrainTypesENUM.HILL);
        cavalryUnit2.setTerrainTypes(TerrainTypesENUM.PLAINS);
        cavalryUnit3.setTerrainTypes(TerrainTypesENUM.FORREST);
    }catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

    @Test
    @DisplayName("After the first attack on cavalry unit in the terrain hill bonus is six")
    public void FirstAttackBonusIsSixTerrainTypeNotPlains(){
        assertEquals(cavalryUnit1.getAttackBonus(),6);
    }

    @Test
    @DisplayName("After the second attack on cavalry unit in the terrain hill bonus is two")
    public void SecondAttackBonusIsTwoTerrainTypeNotPlains(){
        cavalryUnit1.getAttackBonus();
        assertEquals(cavalryUnit1.getAttackBonus(),2);
    }

    @Test
    @DisplayName("After the first attack on cavalry unit in the terrain plains bonus is eight")
    public void FirstAttackBonusIsEightTerrainTypePlains(){
        assertEquals(cavalryUnit2.getAttackBonus(),8);
    }

    @Test
    @DisplayName("After the second attack on cavalry unit in the terrain plains bonus is four")
    public void SecondAttackBonusIsFourTerrainTypePlains(){
        cavalryUnit2.getAttackBonus();
        assertEquals(cavalryUnit2.getAttackBonus(),4);
    }

    @Test
    public void HealthAfterFirstAttack() {
        cavalryUnit1.attack(cavalryUnit2);
        assertEquals(cavalryUnit2.getHealth(), 90);
    }

    @Test
    public void HealthAfterSecondAttack(){
        cavalryUnit1.attack(cavalryUnit2);
        cavalryUnit1.attack(cavalryUnit2);
        assertEquals(cavalryUnit2.getHealth(),84);
    }

    @Test
    @DisplayName("Health after first attack on cavalry unit in the terrain forrest is zero")
    void HealthAfterFirstAttackTerrainTypeForrest(){
        Assertions.assertEquals(0,cavalryUnit3.getResistBonus());
    }

}