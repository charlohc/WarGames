package WarGames;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Army {
    private final String name;
    private List<Unit> units;

    public Army(String name, List<Unit> units) {
        this.name = name;
        this.units = units;
    }

    public Army(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addUnit(Unit newUnit) {
        units.add(newUnit);
        return true;
    }

    public boolean AddAllUnits(List<Unit> allUnits) {
        units.addAll(allUnits);
        return true;
    }

    public boolean removeUnit(Unit specificUnit) {
        for (Unit removeUnit : units) {
            if (units.contains(removeUnit)) {
                units.remove(specificUnit);
                return true;
            }
        }
        return false;
    }

    public boolean hasUnit() {
        return !units.isEmpty();
    }

    public List<Unit> getAllUnits() {
        return new ArrayList<>(units);
    }

    public Unit getRandom(){
        int range = units.size();
        int random = (int) (Math.random() * range);
        return units.get(random);
    }

    @Override
    public String toString() {
        return "Army{" +
                "name='" + name + '\'' +
                ", units=" + units +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return Objects.equals(name, army.name) && Objects.equals(units, army.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }
}













