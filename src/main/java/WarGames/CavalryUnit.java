package WarGames;

public class CavalryUnit extends Unit{
     int bonus= 2;

    public CavalryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    public CavalryUnit(String name, int health) {
        super(name,health,20,12);
    }

    @Override
    public int getAttackBonus() {
        for(int nrOfAttacks = 1; nrOfAttacks == 1; nrOfAttacks++){
            bonus += 4;
        }
          return bonus;
    }


    @Override
    public int getResistBonus() {
        return 4;
    }
}
