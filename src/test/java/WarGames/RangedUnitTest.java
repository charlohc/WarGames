package WarGames;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangedUnitTest {
    RangedUnit rangedUnit = new RangedUnit("",1,1,1);

    @Test
    public void AfterFirstAttackBonusIsSix(){
      assertEquals(6,rangedUnit.getResistBonus());
    }

    @Test
    public void AfterSecondAttackBonusIsFour(){
        RangedUnit rangedUnit1 = new RangedUnit("",1,1,1);
        rangedUnit1.getResistBonus();
        assertEquals(4,rangedUnit1.getResistBonus());
    }
    @Test
    public void AfterThirdAttackBonusIsTwo(){
        RangedUnit rangedUnit2 = new RangedUnit("",1,1,1);
        rangedUnit2.getResistBonus();
        rangedUnit2.getResistBonus();
        assertEquals(2,rangedUnit2.getResistBonus());
    }
}
