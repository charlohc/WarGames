package edu.ntnu.IDATT2001.charlohc.WarGames.UnitTest;

import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.InfantryUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfantryUnitTest {
InfantryUnit infantryUnit1, infantryUnit2,infantryUnit3,infantryUnit4;

    @BeforeEach
    public void reset(){
    try{
        infantryUnit1 = new InfantryUnit("Infantry Unit one", 100, 15,10);
        infantryUnit2 = new InfantryUnit("Infantry Unit two",100,15,10);
        infantryUnit3 = new InfantryUnit("Infantry Unit three",100,15,10);
        infantryUnit4 = new InfantryUnit("Infantry unit four",100,15,10);
        infantryUnit1.setTerrainTypes(TerrainTypesENUM.FORREST);
        infantryUnit2.setTerrainTypes(TerrainTypesENUM.FORREST);
        infantryUnit3.setTerrainTypes(TerrainTypesENUM.HILL);
        infantryUnit4.setTerrainTypes(TerrainTypesENUM.HILL);

    }catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

//100 -
    @Test
    @DisplayName("Health after first attack infantry unit with terrain type forrest")
    public void HealthAfterFirstAttackTerrainTypeForrest() {
        infantryUnit2.attack(infantryUnit1);
        assertEquals(infantryUnit1.getHealth(), 92);
    }

    //
    @Test
    @DisplayName("Health after second attack infantry unit with terrain type forrest")
    public void HealthAfterSecondAttackTerrainTypeForrest() {
        infantryUnit2.attack(infantryUnit1);
        infantryUnit2.attack(infantryUnit1);
        assertEquals(infantryUnit1.getHealth(), 84);
    }

    @Test
    @DisplayName("Health after first attack infantry unit with terrain type hill")
    public void HealthAfterFirstAttackTerrainTypeHill() {
        infantryUnit3.attack(infantryUnit4);
        assertEquals(infantryUnit4.getHealth(), 94);
    }

    @Test
    @DisplayName("Health after second attack infantry unit with terrain type hill")
    public void HealthAfterSecondAttackTerrainTypeHille() {
        infantryUnit3.attack(infantryUnit4);
        infantryUnit3.attack(infantryUnit4);
        assertEquals(infantryUnit4.getHealth(), 88);
    }
}