package WarGames;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CavalryUnitTest {
CavalryUnit cavalryUnit = new CavalryUnit("",1,1,1);

    @Test
    public void FirstAttackBonusIsFour(){
        assertEquals(6,cavalryUnit.getAttackBonus());
    }

    @Test
    public void AfterFirstAttackBonusIsTwo(){
        assertEquals(2,cavalryUnit.getAttackBonus());
    }

}