package edu.ntnu.IDATT2001.charlohc.WarGames;

import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.CavalryUnit;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.CommanderUnit;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.InfantryUnit;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.RangedUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class BattleTest {
    Army humans,orcs;
    Battle battle;

    @BeforeEach
    public void reset() {
        try{

         humans = new Army("Human army", new ArrayList<>());
         orcs = new Army("Orcish horde", new ArrayList<>());
         battle = new Battle(humans, orcs);

        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        for (int i = 0; i < 500; i++) {
            humans.addUnit(new InfantryUnit("Footman", 100));
            orcs.addUnit(new InfantryUnit("Grunt", 100));
        }

        for (int i = 0; i < 200; i++) {
            humans.addUnit(new RangedUnit("Archer", 100));
            orcs.addUnit(new RangedUnit("Spearman", 100));
        }

        for (int i = 0; i < 100; i++) {
            humans.addUnit(new CavalryUnit("Knight", 100));
            orcs.addUnit(new CavalryUnit("Raider", 100));
        }

        humans.addUnit(new CommanderUnit("Mountain King", 180));
        orcs.addUnit(new CommanderUnit("gulDan", 180));

        System.out.println("The winner of the battle is : " + battle.simulate().toString());
    }
    @Test
    public void testSimulate(){
        assertTrue(battle.simulate().equals(humans) || battle.simulate().equals(orcs));
    }
}

