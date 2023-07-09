package Characters;

import Characters.Actions.Fighting;

import java.util.Random;

public class Skeleton extends NpcMonsters {
    public Skeleton(String name, int healthPoint, int strength, int agility, int gold, int experience, int monsterLVL) {
        super(name, healthPoint, strength, agility, gold, experience, monsterLVL);
        monstersList.add(this);
    }

    public Skeleton() {
        super("BoneFletcher", 100, 3, 7, 100, 500, 1);
        monstersList.add(this);
    }

    private boolean isDodge() {
        double base = 10;
        double dodgeChance = base + this.agility;
        Random rnd = new Random();
        int rndNumber = rnd.nextInt(1000) + 1;
        return (dodgeChance * 10) >= rndNumber;
    }

    private boolean isCriticalHit() {
        double base = 10;
        double critChance = base + this.agility * 0.5;
        Random rnd = new Random();
        int rndNumber = rnd.nextInt(1000) + 1;
        return (critChance * 10) >= rndNumber;
    }

    private int attackPower() {
        boolean critHit = this.isCriticalHit();
        double criticalDmg = ((this.strength * 0.05) + 2) * this.strength;
        return critHit ? (int) Math.ceil(criticalDmg) : this.strength;
    }

    @Override
    public void attack(Fighting rival) {
        int attackPower = this.attackPower();
        String critHit = attackPower > this.strength ? " Критический удар" : "";
        if (this.isAlive) {
            System.out.printf("%s пытается атаковать противника силой: %d урона%s\n", this.name, attackPower, critHit);
            rival.defence(attackPower);
        }
    }

    @Override
    public void defence(int dmg) {
        if (this.isDodge()) {
            System.out.printf("%s увернулся от атаки\n", this.name);
        } else {
            this.healthPoint -= dmg;
        }
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
    public int getGold() {
        return this.gold;
    }

    @Override
    public int getExperience() {
        return this.experience;
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
