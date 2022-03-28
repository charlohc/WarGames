package edu.ntnu.IDATT2001.charlohc.WarGames;

import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.Unit;

/**
 * Class Battle
 * simulates a battle between two armies
 */
public class Battle {
    private final Army armyOne;
    private final Army armyTwo;

    /**
     *
     * @param armyOne an army, which consists of several units
     * @param armyTwo an army, which consists of several units
     */
    public Battle(Army armyOne, Army armyTwo) {
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

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
            Unit unit1 = armyOne.getRandomUnit();
            Unit unit2 = armyTwo.getRandomUnit();

            if (armyOne.getRandomNumber() == 1) {
                unit1.attack(unit2);

                if (unit2.getHealth() == 0) {
                    armyTwo.removeUnit(unit2);
                }
                if (!armyTwo.hasUnit()) {
                    break;
                }

                unit2.attack(unit1);

                if (unit1.getHealth() == 0) {
                    armyOne.removeUnit(unit1);
                }

            } else {
                unit2.attack(unit1);
                if (unit1.getHealth() == 0) {
                    armyOne.removeUnit(unit1);
                }
                if (!armyOne.hasUnit()) {
                    break;
                }
                unit1.attack(unit2);

                if (unit2.getHealth() == 0) {
                    armyTwo.removeUnit(unit2);
                }
            }
        }
        if(!armyOne.hasUnit()){
            return armyTwo;
        }else {
            return armyOne;
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
                ", armyTwo=" + armyTwo +
                '}';
    }
}
