package WarGames;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArmyTest {
    Army army;
    Unit unit1, unit2;

    @BeforeEach
    public void reset() {
        army = new Army("Army");
        unit1 = new CavalryUnit("Cavalry Unit one",100);
        unit2 = new CavalryUnit("Cavalry Unit two",100);
    }

    @Test
    public void AddUnitTest(){
        army.addUnit(unit1);
        assertTrue(army.hasUnit());
    }

    @Test
    public void AddAllUnitTest(){
        ArrayList<Unit> allUnitsTest = new ArrayList<>();

        allUnitsTest.add(unit1);
        allUnitsTest.add(unit2);
        army.addAllUnits(allUnitsTest);
        assertEquals(allUnitsTest.size(),2);
    }

    @Test
    public void hasUnitsTrueTest(){
        army.addUnit(unit1);
        assertTrue(army.hasUnit());
    }

    @Test
    public void hasUnitsFalseTest(){
        assertFalse(army.hasUnit());
    }

    @Test
    public void RemoveUnitTest(){
        army.addUnit(unit1);
       assertTrue(army.removeUnit(unit1));
    }


}