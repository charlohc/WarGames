package WarGames;

/**
 * RangedUnit subclass of Unit
 */
public class RangedUnit extends Unit {
    private int numberOfAttacks = 0;


    public RangedUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    public RangedUnit(String name, int health) {
        super(name, health, 15, 8);

    }

    /**
     * Methode that gets the attack bonus
     * @return 3
     */
    @Override
    public int getAttackBonus() {
        return 3;
    }

    /**
     * Methode that gets the resistance bonus, after the first attack the bonus is six, after second attack the bonus is four, after that the bonus will be two
     * @return 6, 4 or 2, dependent on the number of attack
     */
    @Override
    public int getResistBonus() {
        numberOfAttacks++;

        switch(numberOfAttacks) {
            case 1: {
                return 6;
            }
            case 2:{
                return 4;
            }
            default:
                return 2;
        }
    }
}