package WarGames;

public class CommanderUnit extends CavalryUnit{
    private int numberOfAttacks = 0;
    public CommanderUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    public CommanderUnit(String name, int health) {
        super(name, health,25,15);
    }
//TODO: make sure that CommanderUnit uses the same bonus methods as CavalryUnit

    @Override
    public int getAttackBonus() {
        numberOfAttacks++;
        if(numberOfAttacks == 1){
            return 6;
        }else {
            return 2;
        }
    }


    @Override
    public int getResistBonus() {
        return 4;
    }
}
