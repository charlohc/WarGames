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
        assertEquals(4,rangedUnit.getResistBonus());
    }
    @Test
    public void AfterThirdAttackBonusIsTwo(){
        assertEquals(2,rangedUnit.getResistBonus());
    }
}
