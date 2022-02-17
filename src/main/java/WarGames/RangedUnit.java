package WarGames;

public class RangedUnit extends Unit{

    public RangedUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    public RangedUnit(String name, int health) {
        super(name,health,15,8);

    }

    @Override
    public int getAttackBonus() {
        return 3;
    }

    @Override
    public int getResistBonus() {
        int sum = 8;
        for(int i = 0; i <=2; i++){
            if(i == 0){
                sum-=2;
            }if(i == 1){
                sum-=4;
            }else{
                sum-=6;
            }
        }
        return sum;
    }
}
