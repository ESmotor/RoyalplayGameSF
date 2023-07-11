package Characters;

import Characters.Actions.Fighting;

import java.util.Random;

public class Skeleton extends NpcMonsters {
    //Рассовая особенность скелетов: базовая вероятность увернуться от атаки увеличена на 10%
    public Skeleton(String name, int monsterLVL) {
        super(name, monsterLVL);
    }

    private boolean isDodge() {
        double base = 20;
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
        String critHit = attackPower > this.strength ? " КРИТИЧЕСКИЙ" : "";
        if (this.isAlive) {
            System.out.printf("\n%s наносит%s удар\n", this.name, critHit);
            rival.defence(attackPower, this);
        }
    }

    @Override
    public void takeCounterAttack(int dmg) {
        dmg = dmg * speed;
        this.healthPoint -= dmg;
        System.out.printf("%s получает %d урона от контратаки.(Здоровье:%d/%d)\n"
                , this.name, dmg, Math.max(0, this.healthPoint), this.maxHealth);
    }

    @Override
    public void defence(int dmg, Fighting rival) {
        dmg = dmg * speed;
        if (this.isDodge()) {
            System.out.printf("%s увернулся от атаки и проводит контратаку\n", this.name);
            rival.takeCounterAttack(this.attackPower() / 2);
        } else {
            this.healthPoint -= dmg;
            System.out.printf("%s получает %d урона от атаки.(Здоровье:%d/%d)\n"
                    , this.name, dmg, Math.max(0, this.healthPoint), this.maxHealth);
        }
    }

    @Override
    public boolean isAliveNow() {
        if (this.healthPoint <= 0) {
            this.isAlive = false;
        }
        return this.healthPoint > 0;
    }

    @Override
    public int getGold() {
        return this.gold;
    }

    @Override
    public int getMonsterLVL() {
        return this.monsterLVL;
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
                ", maxHealth=" + maxHealth +
                ", healthPoint=" + healthPoint +
                ", strength=" + strength +
                ", agility=" + agility +
                ", gold=" + gold +
                ", experience=" + experience +
                ", monsterLVL=" + monsterLVL +
                '}';
    }
}
