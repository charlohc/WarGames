package WarGames;
//ha test for attack get health
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CavalryUnitTest {
CavalryUnit cavalryUnit1, cavalryUnit2;

    @BeforeEach
    public void reset(){
        cavalryUnit1 = new CavalryUnit("Cavalry Unit One",100,20,12);
        cavalryUnit2 = new CavalryUnit("Cavalry Unit Two",100,20,12);
    }

    @Test
    public void FirstAttackBonusIsFour(){
        assertEquals(cavalryUnit1.getAttackBonus(),6);
    }

    @Test
    public void AfterFirstAttackBonusIsTwo(){
        cavalryUnit1.getAttackBonus();
        assertEquals(cavalryUnit1.getAttackBonus(),2);
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

}