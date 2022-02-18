package WarGames;

public class CavalryUnit extends Unit{
     private int numberOfAttacks = 0;

    public CavalryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    public CavalryUnit(String name, int health) {
        super(name,health,20,12);
    }

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
