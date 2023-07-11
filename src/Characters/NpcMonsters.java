package Characters;

import Characters.Actions.Fighting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class NpcMonsters implements Fighting {
    protected static List<NpcMonsters> monstersList = new ArrayList<>();
    protected static int speed = 3;
    protected String name;
    protected int maxHealth;
    protected int healthPoint;
    protected int strength;
    protected int agility;
    protected int gold;
    protected int experience;
    protected int monsterLVL;
    protected boolean isAlive;


    public NpcMonsters(String name, int monsterLVL) {
        this.name = name;
        this.monsterLVL = monsterLVL;
        int freeSkillPoints = 4 + (monsterLVL - 1) * 3;
        int rndNumber = new Random().nextInt(freeSkillPoints) + 1;
        this.strength = 3 + rndNumber;
        this.agility = 3 + freeSkillPoints - rndNumber;
        this.maxHealth = this.strength * 20;
        this.healthPoint = maxHealth;
        int rndGold = new Random().nextInt(50) + 1;
        this.gold = 60 + rndGold * monsterLVL;
        this.experience = Players.getLvls().get(monsterLVL + 1) / 3;
        this.isAlive = true;
    }

    public void arenaGladiators() {

    }

    abstract public String toString();

    abstract public String getName();

    abstract public int getGold();

    abstract public int getExperience();

    abstract public int getMonsterLVL();

    public static List<NpcMonsters> getMonstersList() {
        return monstersList;
    }

    public static void makeEnemies() {
        System.out.println(monstersList);
        monstersList.add(new Skeleton("Hasan", 1));
        monstersList.add(new Goblin("Velen", 1));
        monstersList.add(new Skeleton("Rodrigez", 1));
        monstersList.add(new Goblin("Bayden", 1));
        monstersList.add(new Skeleton("Valera", 1));
        monstersList.add(new Goblin("Miron", 1));
        monstersList.add(new Skeleton("Harras", 2));
        monstersList.add(new Goblin("Nikolay", 2));
        monstersList.add(new Skeleton("Vaga", 2));
        monstersList.add(new Goblin("Ramil", 2));
        monstersList.add(new Skeleton("Ivan", 3));
        monstersList.add(new Goblin("John", 3));
        monstersList.add(new Skeleton("Rassamik", 3));
        monstersList.add(new Goblin("Kiloja", 3));
        System.out.println(monstersList);
    }
}
