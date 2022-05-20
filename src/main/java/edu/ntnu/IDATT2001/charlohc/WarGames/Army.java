package edu.ntnu.IDATT2001.charlohc.WarGames;

import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class Army
 * an army is a collection of units
 */
public class Army {
    private final String name;
    private List<Unit> units = new ArrayList<Unit>();

    /**
     * Description of the army
     * @param name name of army
     * @param units list of units
     */
    public Army(String name, List<Unit> units)throws IllegalArgumentException {
        if(name.isBlank()){throw new IllegalArgumentException("Name of army can not be blank");}
        this.name = name;
        this.units = units;
    }

    public Army(String name) {
        this.name = name;
    }

    /**
     * Methode that gets the name of the army
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method filters through the list and adds all objects that are instances of InfantryUnit to a new list
     * @return list with only Infantry units
     */
    public List<Unit> getInfantryUnits(){
    return units.stream()
            .filter(unit -> unit instanceof InfantryUnit)
            .toList();
    }

//TODO:Test this methode
    /**
     * Method filters through the list and adds all objects that are instances of CavalryUnit to a new list
     * @return list with only cavalry units
     */
    public List<Unit> getCavalryUnits(){
        return this.units.stream()
                .filter(unit -> unit instanceof CavalryUnit)
                .filter(unit -> !(unit instanceof CommanderUnit))
                .toList();
    }

    /**
     * Method filters through the list and adds all objects that are instances of RangedUnit to a new list
     * @return list with only ranged units
     */
    public List<Unit> getRangedUnits(){
        return this.units.stream()
                .filter(unit -> unit instanceof RangedUnit)
                .toList();
    }

    /**
     * Method filters through the list and adds all objects that are instances of CommanderUnits to a new list
     * @return list with only commander units
     */
    public List<Unit> getCommanderUnits(){
        return this.units.stream()
                .filter(unit -> unit instanceof CommanderUnit)
                .toList();
    }

    /**
     * Methode that adds a unit to the list of units
     * @param newUnit unit that gets put in list units
     */
    public void addUnit(Unit newUnit) {
        units.add(newUnit);
    }

    /**
     * Methode that adds all units in a list to the list units
     * @param allUnits list of units that gets added to list units
     */
    public void addAllUnits(List<Unit> allUnits) {
        units.addAll(allUnits);
    }

    /**
     * Methode that removes a specific unit from the list
     * @param specificUnit the specific unit that we want to remove from the list
     * @return boolean true or false, dependent on if the unit exist in the list or not
     */
    public boolean removeUnit(Unit specificUnit) {
        for (Unit removeUnit : units) {
            if (units.contains(removeUnit)) {
                units.remove(specificUnit);
                return true;
            }
        }
        return false;
    }

    /**
     * Methode that checks if the list has units or is empty
     * @return boolean true or false, dependent on if there is one or more units in the list or not
     */
    public boolean hasUnit() {
        return !units.isEmpty();
    }

    /**
     * Methode that gets all units in the list units
     * @return units
     */
    public List<Unit> getAllUnits() {
        return units;
    }

    /**
     * Methode that gets a random unit in the list units
     * @return unit
     */
    public Unit getRandomUnit(){
        int range = units.size();
        int random = (int) (Math.random() * range);
        return units.get(random);
    }

    /**
     * Methode that gets a random number in range 1 - 2
     * @return random int
     */
    public int getRandomNumber(){
        return (int) (Math.random() * 2);
    }


    public String removeBracketsUnitsList(){
        StringBuilder sb = new StringBuilder();
        for(Unit unit: units){
            sb.append(unit);
        }
        return sb.toString();
    }

    public Boolean containsUnit(Unit unit){
        return getAllUnits().contains(unit);
    }

    /**
     * Methode that return information about the unit
     * @return name, units
     */
    @Override
    public String toString() {
        return name + removeBracketsUnitsList();
    }

    /**
     * Methode that compares two objects
     * @param o object that is being compared to
     * @return true or false, dependent on if the objects are alike or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return Objects.equals(name, army.name) && Objects.equals(units, army.units);
    }

    /**
     *  Methode that returns a hash code value for the object
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }
}













