package Characters;

public class Battle {
    private final Players playerRival;
    private final NpcMonsters monsterRival;

    public Battle(Players playerRival, NpcMonsters monsterRival) {
        this.playerRival = playerRival;
        this.monsterRival = monsterRival;
    }

    public void fight() {
        System.out.printf("Бой начинается между %s и %s!.\n", playerRival.getName(), monsterRival.getName());
        while (true) {
            if (playerRival.isAliveNow()) {
                playerRival.attack(monsterRival);
            } else {
                System.out.printf("Монстр %s победил в битве!\n", monsterRival.getName());
                break;
            }
            if (monsterRival.isAliveNow()) {
                monsterRival.attack(playerRival);
            } else {
                System.out.printf("Игрок %s победил в битве и получает %d опыта и %d золота.\n"
                        , playerRival.getName(), monsterRival.getExperience(), monsterRival.getGold());
                playerRival.getAward(monsterRival.getExperience(), monsterRival.getGold());
                break;
            }
        }
    }
}
