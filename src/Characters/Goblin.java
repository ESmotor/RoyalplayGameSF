package Characters;

import Characters.Actions.Fighting;

import java.util.Random;

public class Goblin extends NpcMonsters {
    //Рассовая особенность гоблинов: с вероятностью 10% восле получения урона восстановить
    //20% максимального запаса здороья
    public Goblin(String name, int monsterLVL) {
        super(name, monsterLVL);
    }

    private boolean isDodge() {
        double base = 10;
        double dodgeChance = base + this.agility;
        Random rnd = new Random();
        int rndNumber = rnd.nextInt(1000) + 1;
        return (dodgeChance * 10) >= rndNumber;
    }

    private boolean isRaceAbility() {
        double abilityChance = 20;
        Random rnd = new Random();
        int rndNumber = rnd.nextInt(100) + 1;
        return abilityChance >= rndNumber;
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
            if (isRaceAbility()) {
                System.out.printf("Рассовая способность. %s восстановил %d здоровья.\n",this.getName(),this.healthPoint / 5);
                this.healthPoint = Math.min(this.healthPoint + this.healthPoint / 5,this.maxHealth);
            }
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
    public int getMonsterLVL() {
        return this.monsterLVL;
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
