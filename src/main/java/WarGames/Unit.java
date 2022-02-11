package WarGames;

//TODO: Java Doc
public abstract class Unit{
    private final String name;
    private int health;
    private final int attack;
    private final int armor;

//TODO: Implement checks
    public Unit(String name, int health, int attack, int armor){
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.armor = armor;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getArmor() {
        return armor;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public abstract int getAttackBonus();

    public abstract int getResistBonus();

    public void attack() {
        int healthOpponent = getAttack() - (getAttack() + getAttackBonus()) + (getArmor() + getResistBonus());
    }


}
