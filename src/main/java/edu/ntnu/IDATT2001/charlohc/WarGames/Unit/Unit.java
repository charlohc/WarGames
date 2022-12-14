package edu.ntnu.IDATT2001.charlohc.WarGames.Unit;

import edu.ntnu.IDATT2001.charlohc.WarGames.Listener.ChangeInHealth;
import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import edu.ntnu.IDATT2001.charlohc.WarGames.UnitFactory.UnitTypeENUM;
//TODO: test

/**
 * Abstract super class Unit
 * Contains four attributes that describe the unit
 */
public abstract class Unit{
    private ChangeInHealth listener;
    private String name;
    private int health;
    private int attack;
    private int armor;
    private TerrainTypesENUM terrainTypesENUM;

//TODO: can attack and armor be zero?
    /**
     * Description of the attributes
     * @param name short descriptive name
     * @param health int that describes the health to the unit, cannot be negative
     * @param attack int that represents the units weapon
     * @param armor int that represents the defends under attack
     */
    public Unit(String name, int health, int attack, int armor) throws IllegalArgumentException {
        if(name.isBlank()){throw new IllegalArgumentException("Name can not be blank");}
        if(name.contains(",")){ throw new IllegalArgumentException("name can not contain ','");}
        if(health < 0){throw new IllegalArgumentException("Health can not be negative");}
        if(attack < 0){throw new IllegalArgumentException("Attack value can not be negative");}
        if(armor < 0) {throw new IllegalArgumentException("Armor value can not be negative");}
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.armor = armor;
        terrainTypesENUM = null;
    }


    /**
     * Gets the unit name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the health value of the unit
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gets the attack value
     * @return attack
     */
    public int getAttackValue() {
        return attack;
    }

    /**
     * gets the armour value
     * @return armor
     */
    public int getArmor() {
        return armor;
    }

    public TerrainTypesENUM getTerrainType(){
        return terrainTypesENUM;
    }

    public String setName(String name){
        this.name = name;
        return name;
    }

    public int setAttack(int attack){
        this.attack = attack;
        return attack;
    }

    public int setArmour(int armour){
        this.armor = armour;
        return armour;
    }
    public void setTerrainTypes(TerrainTypesENUM terrainTypesENUM){
       if(terrainTypesENUM ==  null){throw new IllegalArgumentException("Terrain type can not be null");}
       this.terrainTypesENUM = terrainTypesENUM;
    }

//TODO: correct?
    /**
     * Changes the health value
     * @param newHealth
     */
    public void setHealth(int newHealth) {
        if(newHealth < 0){newHealth = 0;}

        if (listener != null) {
            listener.changeInHealth(this,this.getHealth(),newHealth);
        }

        this.health = newHealth;
    }

    /**
     * Abstract method used in subclasses, a bonus the unit gets at each attack
     * @return
     */
    public abstract int getAttackBonus();

    /**
     * Abstract methode used in subclasses, a bonus the unit gets for resisting an attack
     * @return
     */
    public abstract int getResistBonus();


    public abstract UnitTypeENUM getUnitType();

    public void setChangeInHealthListener(ChangeInHealth listener){
        this.listener = listener;
    }

    /**
     * Methode that changes the health of the opponent after an attack, the health value cannot be negative, therefore
     * the lowest health value the opponent can archive is zero
     * @param opponent unit that gets attacked
     */

    public void attack(Unit opponent) {
        if(this.getTerrainType() != opponent.getTerrainType()){
            throw new IllegalArgumentException("The two opponents need to have the same terrain type");
        }
        if(opponent.getHealth() < 0){
            opponent.setHealth(0);
        }

        int healthOpponent = opponent.health - (this.attack + this.getAttackBonus()) + (opponent.getArmor() + getResistBonus());
        opponent.setHealth(healthOpponent);

    }



    /**
     * Methode that return information about the unit
     * @return name, health, attack, armor
     */
    @Override
    public String toString() {
        return  "\n" + getUnitType() + "," + name  +
                "," + health ;

    }
}
