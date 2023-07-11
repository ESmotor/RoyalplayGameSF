package Characters;

public class Battle extends Thread {
    private final Players playerRival;
    private final NpcMonsters monsterRival;

    public Battle(Players playerRival, NpcMonsters monsterRival) {
        this.playerRival = playerRival;
        this.monsterRival = monsterRival;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Бой начинается между %s и %s!.\n", playerRival.getName(), monsterRival.getName());
            Thread.sleep(500);
            while (true) {
                if (playerRival.isAliveNow()) {
                    playerRival.attack(monsterRival);
                    Thread.sleep(500);
                } else {
                    System.out.printf("\nИгрок %s героически погиб в битве...\n", playerRival.getName());
                    Thread.sleep(500);
                    System.out.printf("\nМонстр %s победил в битве!\n", monsterRival.getName());
                    Thread.sleep(500);
                    System.out.println("\nВыход из игры...");
                    System.exit(0);
                    break;
                }
                if (monsterRival.isAliveNow()) {
                    monsterRival.attack(playerRival);
                    Thread.sleep(500);
                } else {
                    System.out.printf("\nМонстр %s повержен!\n", monsterRival.getName());
                    Thread.sleep(500);
                    System.out.printf("\nИгрок %s победил в битве и получает %d опыта и %d золота.\n"
                            , playerRival.getName(), monsterRival.getExperience(), monsterRival.getGold());
                    playerRival.getAward(monsterRival.getExperience(), monsterRival.getGold());
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
