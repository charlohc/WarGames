package edu.ntnu.IDATT2001.charlohc.WarGames.UnitFactory;

import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.CavalryUnit;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.CommanderUnit;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.InfantryUnit;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.RangedUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UnitFactoryTest {
CavalryUnit cavalryUnit;
CommanderUnit commanderUnit;
InfantryUnit infantryUnit;
RangedUnit rangedUnit;
UnitFactory unitFactory;

//TODO: do I have to test everything for everyone? test terrain
    @BeforeEach
    void setUp() {
        unitFactory = new UnitFactory();
        cavalryUnit = (CavalryUnit) unitFactory.createUnitByType(UnitTypeENUM.CAVALRY,"Cavalry",100);
        commanderUnit = (CommanderUnit) unitFactory.createUnitByType(UnitTypeENUM.COMMANDER,"Commander",90);
        infantryUnit = (InfantryUnit) unitFactory.createUnitByType(UnitTypeENUM.INFANTRY,"Infantry",80);
        rangedUnit = (RangedUnit) unitFactory.createUnitByType(UnitTypeENUM.RANGED,"Ranged",100);
    }

    @Test
    void testCorrectType(){
        Assertions.assertEquals(UnitTypeENUM.RANGED,rangedUnit.getUnitType());
    }

    @Test
    void getName(){
        Assertions.assertEquals("Cavalry",cavalryUnit.getName());
    }

    @Test
    void getHealth(){
        Assertions.assertEquals(100,cavalryUnit.getHealth());
    }

    @Test
    void setHealth(){
        cavalryUnit.setHealth(50);
        Assertions.assertEquals(50,cavalryUnit.getHealth());
    }

    @Test
    void getArmour(){
        Assertions.assertEquals(12, cavalryUnit.getArmor());
    }

    @Test
    void getAttack(){
        Assertions.assertEquals(20,cavalryUnit.getAttackValue());
    }

    @Test
    @DisplayName("Get resist bonus after first attack ranged unit")
    void getResistBonusAfterAttack(){
        Assertions.assertEquals(6,rangedUnit.getResistBonus());
    }

    @Test
    @DisplayName("Get resist bonus after second attack ranged unit")
    void getResistBonusAfterSecondAttack(){
        rangedUnit.getResistBonus();
        Assertions.assertEquals(4,rangedUnit.getResistBonus());
    }

    @Test
    @DisplayName("Get resist bonus after second attack ranged unit")
    void getResistBonusAfterThirdAttack(){
        rangedUnit.getResistBonus();
        rangedUnit.getResistBonus();
        Assertions.assertEquals(2,rangedUnit.getResistBonus());
    }

    @Test
    @DisplayName("Get attack bonus after first attack cavalry unit")
    void getAttackBonusAfterFirst(){
        Assertions.assertEquals(6,cavalryUnit.getAttackBonus());
    }

    @Test
    @DisplayName("Get attack bonus after second attack cavalry unit")
    void getAttackBonusAfterSecond(){
        cavalryUnit.getAttackBonus();
        Assertions.assertEquals(2, cavalryUnit.getAttackBonus());
    }

    @Test
    @DisplayName("Test attack methode where commander unit attacks infantry unit")
    void healthAfterFirstAttack(){
        commanderUnit.attack(infantryUnit);
        Assertions.assertEquals(63, infantryUnit.getHealth());
    }

    @Test
    @DisplayName("Test attack methode where commander unit attacks infantry unit twice")
    void healthAfterSecondAttack(){
        commanderUnit.attack(infantryUnit);
        commanderUnit.attack(infantryUnit);
        Assertions.assertEquals(50, infantryUnit.getHealth());
    }

    @Test
    @DisplayName("Throw Exception when the name parameter is null")
    void nameNullException(){
        Assertions.assertThrows(NullPointerException.class,() -> unitFactory.createUnitByType(UnitTypeENUM.INFANTRY,null,100));
    }

    @Test
    @DisplayName("Throw Exception when the unit type parameter is null")
    void typeNullException(){
        Assertions.assertThrows(NullPointerException.class,() -> unitFactory.createUnitByType(null,"Cavalry",100));
    }

    @Test
    @DisplayName("Throw Exception when the health int in parameter is negative")
    void negativeHealthException(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> unitFactory.createUnitByType(UnitTypeENUM.RANGED,"ranged",-1));
    }
}