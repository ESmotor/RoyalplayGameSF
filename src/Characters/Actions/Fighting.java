package Characters.Actions;

public interface Fighting {
    void attack(Fighting rival);
    void defence(int dmg,Fighting rival);
    boolean isAliveNow();
    void takeCounterAttack(int dmg);
}
