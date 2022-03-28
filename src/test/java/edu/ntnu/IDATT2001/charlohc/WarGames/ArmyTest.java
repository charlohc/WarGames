package edu.ntnu.IDATT2001.charlohc.WarGames;


import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.CavalryUnit;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.InfantryUnit;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArmyTest {
    Army army;
    Unit unit1, unit2, unit3,unit4;

    @BeforeEach
    public void reset() {
    try {
        army = new Army("Army");
        unit1 = new CavalryUnit("Cavalry Unit one", 100);
        unit2 = new InfantryUnit("Infantry Unit one", 100);

        army.addUnit(unit1);
        army.addUnit(unit2);


    }catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

    @Test
    public void AddUnitTest(){
        assertTrue(army.hasUnit());
    }

    @Test
    public void AddAllUnitTest(){
        army.removeUnit(unit1);
        army.removeUnit(unit2);

        ArrayList<Unit> allUnitsTest = new ArrayList<>();

        allUnitsTest.add(unit1);
        allUnitsTest.add(unit2);
        army.addAllUnits(allUnitsTest);
        assertEquals(allUnitsTest.size(),2);
    }

    @Test
    public void hasUnitsTrueTest(){
        assertTrue(army.hasUnit());
    }

    @Test
    public void hasUnitsFalseTest(){
        army.removeUnit(unit1);
        army.removeUnit(unit2);
        assertFalse(army.hasUnit());
    }


}