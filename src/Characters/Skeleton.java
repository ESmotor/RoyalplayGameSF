package Characters;

import Characters.Actions.Fighting;

import java.util.Random;

public class Skeleton extends NpcMonsters {
    public Skeleton(String name, int healthPoint, int strength, int agility, int gold, int experience, int monsterLVL) {
        super(name, healthPoint, strength, agility, gold, experience, monsterLVL);
        monstersList.add(this);
    }

    public Skeleton() {
        super("BoneFletcher", 100, 5, 5, 100, 500, 1);
        monstersList.add(this);
    }

    private double criticalChance() {
        double base = 10;
        double bonus = this.agility * 0.5;
        return base + bonus;
    }

    private int attackPower() {
        double critChance = this.criticalChance();
        Random rnd = new Random();
        int rndNumber = rnd.nextInt(1000) + 1;
        int multi = (int) (critChance * 10) >= rndNumber ? 2 : 1;
        return this.strength * multi;
    }

    @Override
    public void attack(Fighting rival) {
        int attackPower = this.attackPower();
        if (this.isAlive) {
            System.out.printf("%s пытается атаковать противника силой: %d урона\n", this.name, attackPower);
            rival.defence(attackPower);
        }
    }

    @Override
    public void defence(int dmg) {
        super.healthPoint -= dmg;
    }

    @Override
    public boolean isAliveNow() {
        if (this.healthPoint <= 0) {
            this.isAlive = false;
            System.out.printf("Монстр %s повержен!\n", this.name);
        }
        return this.healthPoint > 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Skeleton{" +
                "name='" + name + '\'' +
                ", healthPoint=" + healthPoint +
                ", strength=" + strength +
                ", agility=" + agility +
                ", gold=" + gold +
                ", experience=" + experience +
                ", monsterLVL=" + monsterLVL +
                '}';
    }
}
