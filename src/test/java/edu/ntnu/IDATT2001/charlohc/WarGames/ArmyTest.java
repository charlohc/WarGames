package edu.ntnu.IDATT2001.charlohc.WarGames;


import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.CavalryUnit;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.InfantryUnit;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//TODO: go trough and make sure not missing any tests
class ArmyTest {
    Army army;
    Unit unit1, unit2, unit3;

    @BeforeEach
    public void reset() {
    try {
        army = new Army("Army");
        unit1 = new CavalryUnit("Cavalry Unit one", 100);
        unit2 = new InfantryUnit("Infantry Unit one", 100);
        unit3 = new InfantryUnit("Infantry unit two",100);

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
        ArrayList<Unit> allUnitsList = new ArrayList<>();

        allUnitsList.add(unit1);
        allUnitsList.add(unit2);
        army.addAllUnits(allUnitsList);

        assertEquals(allUnitsList.size(),2);
    }

    @Test
    void containsUnitTrue(){
        Assertions.assertTrue(army.containsUnit(unit1));
    }

    @Test
    void containsUnitFalse(){
        Assertions.assertFalse(army.containsUnit(unit3));
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