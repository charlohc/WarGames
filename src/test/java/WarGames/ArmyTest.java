package WarGames;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class ArmyTest {
    Army army = new Army("", new ArrayList<Unit>());

//TODO: Maybe make tests nestled so that you dont have to add them in every methode...

    @Test
    public void AddUnitTest(){
        CavalryUnit cavalryUnit1 = new CavalryUnit("myTestUnit",30);
        army.addUnit(cavalryUnit1);
    }

    @Test
    public void AddAllUnitTest(){
        CavalryUnit cavalryUnit1 = new CavalryUnit("myTestUnit",30);
        CavalryUnit cavalryUnit2 = new CavalryUnit("testUnitCavalry",30);
        ArrayList<Unit> allUnitsTest = new ArrayList<>();
        allUnitsTest.add(cavalryUnit1);
        allUnitsTest.add(cavalryUnit2);

        army.AddAllUnits(allUnitsTest);
    }

    @Test
    public void getAllUnitsTest(){
        System.out.println(army.getAllUnits());
    }
    @Test
    public void GetRandomUnitTest() {
        CavalryUnit cavalryUnit2 = new CavalryUnit("testUnitCavalry",30);
        InfantryUnit infantryUnit1 = new InfantryUnit("testUnitInfantry",30);
        army.addUnit(cavalryUnit2);
        army.addUnit(infantryUnit1);
        for (int i = 0; i < 5; i++) {
            System.out.println(army.getRandom());
        }
    }

    @Test
    public void hasUnitsTrueTest(){
        RangedUnit rangedUnit = new RangedUnit("testUnitRanged",30);
        army.addUnit(rangedUnit);

        Assertions.assertTrue(army.hasUnit());
    }

    @Test
    public void RemoveUnitTest(){
        RangedUnit rangedUnit = new RangedUnit("testUnitRanged",30);
        army.addUnit(rangedUnit);
       Assertions.assertTrue(army.removeUnit(rangedUnit));
    }


}