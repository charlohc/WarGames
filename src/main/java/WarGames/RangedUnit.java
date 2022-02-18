package WarGames;

public class RangedUnit extends Unit {
    private int numberOfAttacks = 0;


    public RangedUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    public RangedUnit(String name, int health) {
        super(name, health, 15, 8);

    }

    @Override
    public int getAttackBonus() {
        return 3;
    }

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