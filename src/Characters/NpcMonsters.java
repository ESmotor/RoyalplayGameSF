package Characters;

import Characters.Actions.Fighting;

import java.util.ArrayList;
import java.util.List;

public abstract class NpcMonsters implements Fighting {
    protected static List<NpcMonsters> monstersList = new ArrayList<>();
    protected String name;
    protected int healthPoint;
    protected int strength;
    protected int agility;
    protected int gold;
    protected int experience;
    protected int monsterLVL;
    protected boolean isAlive;


    public NpcMonsters(String name, int healthPoint, int strength, int agility, int gold, int experience, int monsterLVL) {
        this.name = name;
        this.healthPoint = healthPoint;
        this.strength = strength;
        this.agility = agility;
        this.gold = gold;
        this.experience = experience;
        this.monsterLVL = monsterLVL;
        this.isAlive = true;
    }


    abstract public String toString();

    abstract public String getName();
    abstract public int getGold();
    abstract public int getExperience();

}
