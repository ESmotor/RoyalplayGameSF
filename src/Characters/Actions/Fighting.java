package Characters.Actions;

public interface Fighting {
    void attack(Fighting rival);
    void defence(int dmg);
    boolean isAliveNow();
}
