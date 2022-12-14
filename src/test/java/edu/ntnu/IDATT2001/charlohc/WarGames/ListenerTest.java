package edu.ntnu.IDATT2001.charlohc.WarGames;

import edu.ntnu.IDATT2001.charlohc.WarGames.Listener.ChangeInHealth;
import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.CavalryUnit;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.InfantryUnit;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.RangedUnit;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListenerTest implements ChangeInHealth {
    Army humans, orcs;
    InfantryUnit infantryUnit;
    RangedUnit rangedUnit;
    int changeInHealth;

    @BeforeEach
    void reset() {
        humans = new Army("humans");
        orcs = new Army("Orcs");

        infantryUnit = new InfantryUnit("infantry", 100, 20, 10);
        rangedUnit = new RangedUnit("ranged", 100, 20, 10);
        rangedUnit.setTerrainTypes(TerrainTypesENUM.PLAINS);
        infantryUnit.setTerrainTypes(TerrainTypesENUM.PLAINS);

        humans.addUnit(infantryUnit);
        orcs.addUnit(rangedUnit);

    }


    @Test
    void testHealthChange(){
        infantryUnit.setChangeInHealthListener(this);
        rangedUnit.attack(infantryUnit);
        Assertions.assertEquals(7,changeInHealth);


    }

    @Override
    public void changeInHealth(Unit unit, int startHealth, int currentHealth) {
        changeInHealth = (startHealth - currentHealth);
    }
}

