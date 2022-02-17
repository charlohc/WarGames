package WarGames;

public class CavalryUnit extends Unit{

    public CavalryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    public CavalryUnit(String name, int health) {
        super(name,health,20,12);
    }

    @Override
    public int getAttackBonus() {
        int sum= 2;
      for(int i = 0; i <=1; i++){
          sum += 4;
      }
      return sum;
    }

    @Override
    public int getResistBonus() {
        return 4;
    }
}
