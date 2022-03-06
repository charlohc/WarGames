package WarGames;

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
    public Army(String name, List<Unit> units) {
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
    public Unit getRandom(){
        int range = units.size();
        int random = (int) (Math.random() * range);
        return units.get(random);
    }

    /**
     * Methode that return information about the unit
     * @return name, units
     */
    @Override
    public String toString() {
        return '\'' +  name + '\''
                + ", units=" + units ;
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













