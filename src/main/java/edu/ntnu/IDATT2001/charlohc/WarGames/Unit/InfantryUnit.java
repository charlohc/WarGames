package edu.ntnu.IDATT2001.charlohc.WarGames.Unit;

import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import edu.ntnu.IDATT2001.charlohc.WarGames.UnitFactory.UnitTypeENUM;

/**
 * InfantryUnit subclass of Unit
 */
public class InfantryUnit extends Unit {

    public InfantryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack,armor);
    }

    public InfantryUnit(String name, int health) {
        super(name,health,15,10);
    }

    /**
     * Methode that gets the attack bonus
     * @return int 2
     */
    @Override
    public int getAttackBonus() {
        if(getTerrainType().equals(TerrainTypesENUM.FOREST)){
            return 6;
        }
        return 2;
    }

    /**
     * Methode that gets the resistance bonus
     * @return int 1
     */
    @Override
    public int getResistBonus() {
        if(getTerrainType().equals(TerrainTypesENUM.FOREST)){
            return 3;
        }
        return 1;
    }

    @Override
    public UnitTypeENUM getUnitType() {
        return UnitTypeENUM.INFANTRY;
    }

}
