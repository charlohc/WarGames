package edu.ntnu.IDATT2001.charlohc.WarGames;

import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.InfantryUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfantryUnitTest {
InfantryUnit infantryUnit1, infantryUnit2;

    @BeforeEach
    public void reset(){
    try{
        infantryUnit1 = new InfantryUnit("Infantry Unit one", 100, 15,10);
        infantryUnit2 = new InfantryUnit("Infantry Unit two",100,15,10);
    }catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
    }

    @Test
    public void HealthAfterFirstAttack() {
        infantryUnit1.attack(infantryUnit2);
        assertEquals(infantryUnit2.getHealth(), 94);
    }

    @Test
    public void HealthAfterSecondAttack() {
        infantryUnit1.attack(infantryUnit2);
        infantryUnit1.attack(infantryUnit2);
        assertEquals(infantryUnit2.getHealth(), 88);
    }
}