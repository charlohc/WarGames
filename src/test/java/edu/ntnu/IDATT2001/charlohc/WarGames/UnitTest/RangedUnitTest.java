package edu.ntnu.IDATT2001.charlohc.WarGames.UnitTest;

import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.RangedUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangedUnitTest {
    RangedUnit rangedUnit1, rangedUnit2;

    @BeforeEach
    public void reset(){
    try {
        rangedUnit1 = new RangedUnit("Ranged Unit one", 100, 15, 8);
        rangedUnit2 = new RangedUnit("Ranged Unit two", 100, 15, 8);
    }catch (Exception e){
        System.out.println("Error: " + e.getMessage());
    }
    }
    @Test
    public void AfterFirstAttackBonusIsSix(){
      assertEquals(6,rangedUnit1.getResistBonus());
    }

    @Test
    public void AfterSecondAttackBonusIsFour(){
        rangedUnit1.getResistBonus();
        assertEquals(rangedUnit1.getResistBonus(),4);
    }
    @Test
    public void AfterThirdAttackBonusIsTwo(){
        rangedUnit1.getResistBonus();
        rangedUnit1.getResistBonus();
        assertEquals(rangedUnit1.getResistBonus(),2);
    }

    @Test
    public void HealthAfterFirstAttack() {
        rangedUnit1.attack(rangedUnit1);
        assertEquals(rangedUnit1.getHealth(), 96);
    }

    @Test
    public void HealthAfterSecondAttack(){
        rangedUnit2.attack(rangedUnit1);
        rangedUnit2.attack(rangedUnit1);
        assertEquals(rangedUnit1.getHealth(),90);
    }


    @Test
    public void HealthAfterThirdAttack(){
        rangedUnit2.attack(rangedUnit1);
        rangedUnit2.attack(rangedUnit1);
        rangedUnit2.attack(rangedUnit1);
        assertEquals(rangedUnit1.getHealth(),82);
    }
}
