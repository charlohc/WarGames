package edu.ntnu.IDATT2001.charlohc.WarGames;

import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.Unit;

/**
 * Class Battle
 * simulates a battle between two armies
 */
public class Battle {
    private final Army armyOne, armyTwo;
    private final TerrainTypesENUM terrainTypes;
    private Unit unit1, unit2;

    /**
     *
     * @param armyOne an army, which consists of several units
     * @param armyTwo an army, which consists of several units
     */
    public Battle(Army armyOne, Army armyTwo, TerrainTypesENUM terrainTypes) {
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
        this.terrainTypes = terrainTypes;

        armyOne.getAllUnits().forEach(unit -> unit.setTerrainTypes(terrainTypes));
        armyTwo.getAllUnits().forEach(unit -> unit.setTerrainTypes(terrainTypes));
    }

    public Army getArmyOne() {
        return armyOne;
    }

    public Army getArmyTwo() {
        return armyTwo;
    }

    public TerrainTypesENUM getTerrainTypes() {
        return terrainTypes;
    }

    //TODO: make so that two units fight until one is done, then another can try
    /**
     * Methode that simulates a battle between two armies. The armies battle as long as they both have any units.
     * A random unit from the first army attacks a random unit from the other,
     * they battle until there is only one unit left with health value higher than zero. The unit with health value zero
     * is removed from the list of units.The units take turns starting the attack. When one army is out of units, the
     * other win.
     * @return army
     */
    public Army simulate() {

        while(armyOne.hasUnit() && armyTwo.hasUnit()) {
            unit1 = armyOne.getRandomUnit();
            unit2 = armyTwo.getRandomUnit();

            while (unit1.getHealth() != 0 && unit2.getHealth() != 0) {

                if (armyOne.getRandomNumber() == 1) {
                    unitOneAttack();

                } else {
                    unitTwoAttack();
                }
            }
        }

        if(!armyOne.hasUnit()){
            return armyTwo;
        }else {
            return armyOne;
        }
    }

    public void unitOneAttack(){
        unit1.attack(unit2);

        if (unit2.getHealth() == 0) {
            armyTwo.removeUnit(unit2);

        }
    }

    public void unitTwoAttack(){
        unit2.attack(unit1);

        if (unit1.getHealth() == 0) {
            armyOne.removeUnit(unit1);
        }
    }

    /**
     * Information about the army
     * @return armyOne, armyTwo
     */
    @Override
    public String toString() {
        return "Battle{" +
                "armyOne=" + armyOne +
                ", armyTwo=" + armyTwo + ", terrain= " +  getTerrainTypes() +
                '}';
    }
}
