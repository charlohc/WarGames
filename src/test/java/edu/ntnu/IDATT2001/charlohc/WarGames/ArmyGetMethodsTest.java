package edu.ntnu.IDATT2001.charlohc.WarGames;

import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArmyGetMethodsTest {
    Army army;
    Unit unit1, unit2, unit3,unit4,unit5;

    @BeforeEach
    public void reset() {
        try {
            army = new Army("Army");
            unit1 = new CavalryUnit("Cavalry Unit", 100);
            unit2 = new InfantryUnit("Infantry Unit", 100);
            unit3 = new CommanderUnit("Commander Unit",100);
            unit4 = new RangedUnit("Ranged Unit",100);
            unit5 = new CavalryUnit("Cavalry Unit two",100);

            army.addUnit(unit1);
            army.addUnit(unit2);
            army.addUnit(unit3);
            army.addUnit(unit4);

        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Test
    public void getInfantryUnitTest(){
        Assertions.assertEquals(1,army.getInfantryUnits().size());
    }

    @Test
    public void getCavalryUnitTest(){
        Assertions.assertEquals(2,army.getCavalryUnits().size());
    }

    @Test
    public void getRangedUnitTest(){
        Assertions.assertEquals(1,army.getRangedUnits().size());
    }

    @Test
    public void getCommanderUnitTest(){
        Assertions.assertEquals(1, army.getCommanderUnits().size());
    }
}
