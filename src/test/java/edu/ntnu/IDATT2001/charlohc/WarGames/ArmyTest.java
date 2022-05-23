package edu.ntnu.IDATT2001.charlohc.WarGames;


import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.CavalryUnit;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.InfantryUnit;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class ArmyTest {
    Army army;
    Unit unit1, unit2, unit3;

    @BeforeEach
    void reset() {
        army = new Army("Army");
        unit1 = new CavalryUnit("Cavalry Unit one", 100);
        unit2 = new InfantryUnit("Infantry Unit one", 100);
        unit3 = new InfantryUnit("Infantry unit two",100);

        army.addUnit(unit1);
        army.addUnit(unit2);

}

    @Test
    void AddUnitTest(){
        assertTrue(army.hasUnit());
    }

    @Test
    void AddAllUnitTest(){
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
    void hasUnitsTrueTest(){
        assertTrue(army.hasUnit());
    }

    @Test
    void hasUnitsFalseTest(){
        army.removeUnit(unit1);
        army.removeUnit(unit2);
        assertFalse(army.hasUnit());
    }

}