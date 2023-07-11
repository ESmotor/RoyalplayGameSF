package Characters;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Arena {

    Players player;
    NpsTraders shop;
    List<NpcMonsters> enemylist;


    private static int enterNum(int low, int up) {
        Scanner scanner = new Scanner(System.in);
        String strLine;
        do {
            strLine = scanner.nextLine();
            StringBuilder wrongSymbols = new StringBuilder();
            Pattern pattern = Pattern.compile("[\\D\\s]");
            Matcher matcher = pattern.matcher(strLine);
            while (matcher.find()) {
                wrongSymbols.append(strLine, matcher.start(), matcher.end());
            }
            if (!wrongSymbols.toString().equals("") || Integer.parseInt(strLine) > up || Integer.parseInt(strLine) < low) {
                System.out.println("Не веррный ввод:");
                continue;
            }
            return Integer.parseInt(strLine);
        } while (true);
    }

    public Players startMenu() {
        System.out.println("1) Начать игру");
        System.out.println("2) Выход из игры");
        int enterNumber = enterNum(1, 2);
        if (enterNumber == 1) {
            System.out.println("Добро пожаловать на Арену смерти");
            this.player = Players.createNewPlayer();
            this.shop = new NpsTraders(this.player);
            NpcMonsters.makeEnemies();
            this.enemylist = NpcMonsters.getMonstersList();
            return this.player;
        } else if (enterNumber == 2) {
            System.out.println("Выходим из игры");
            System.exit(0);
        }
        return null;
    }


    public void mainMenu() {

        int enterNumber;
        do {
            System.out.println("\n1) Меню персонажа");
            System.out.println("2) Магазин");
            System.out.println("3) Арена");
            System.out.println("4) Выход из игры");
            enterNumber = enterNum(1, 4);
            if (enterNumber == 1) {
                this.characterMenu();
            } else if (enterNumber == 2) {
                this.player.buySomething();
            } else if (enterNumber == 3) {
                this.chooseRival();
            } else if (enterNumber == 4) {
                System.out.println("\nВыход из игры...");
                System.exit(0);
            }
        } while (true);
    }

    public void characterMenu() {

        int enterNumber;
        boolean back = false;
        do {
            System.out.println("\n1) Информация о персонаже");
            System.out.println("2) Повысить уровень");
            System.out.println("3) Распределить способности");
            System.out.println("4) Назад");

            enterNumber = enterNum(1, 4);
            if (enterNumber == 1) {
                System.out.print("\nИмя: " + player.getName());
                System.out.print(" Сила: " + player.getStrength());
                System.out.print(" Ловкость: " + player.getAgility());
                System.out.print(" Здоровье: " + player.getHealthPoint() + "/" + player.getMaxHealth());
                System.out.print(" Золото: " + player.getGold());
                System.out.print(" Уровень: " + player.getCurrentLVL());
                System.out.print(" Опыт: " + player.getExperience() + "/" + Players.getLvls().get(player.getCurrentLVL() + 1));
                System.out.print(" Очки способностей: " + player.getNewPoints() + "\n");
            } else if (enterNumber == 2) {
                player.lvlUP();
            } else if (enterNumber == 3) {
                player.distributeAbilities();
            } else if (enterNumber == 4) {
                back = true;
            }
        } while (!back);
    }

    public void arenaMenu() {
        int enterNumber;
        do {
            System.out.println("\n1) Выбрать противника");
            System.out.println("2) Выйти");
            enterNumber = enterNum(1, 2);
            if (enterNumber == 1) {
                this.chooseRival();
            } else if (enterNumber == 2) {
                break;
            }
        } while (true);
    }

    public void chooseRival() {
        int enterNumber;
        do {
            System.out.println(enemylist);
            NpcMonsters enemy1 = enemylist.get(0);
            NpcMonsters enemy2 = enemylist.get(1);
            NpcMonsters enemy3 = enemylist.get(2);
            System.out.printf("\n1) %s %s, уровень %d\n", enemy1.getClass().getSimpleName(), enemy1.getName(), enemy1.getMonsterLVL());
            System.out.printf("2) %s %s, уровень %d\n", enemy2.getClass().getSimpleName(), enemy2.getName(), enemy2.getMonsterLVL());
            System.out.printf("3) %s %s, уровень %d\n", enemy3.getClass().getSimpleName(), enemy3.getName(), enemy3.getMonsterLVL());
            System.out.println("4) Выйти");
            enterNumber = enterNum(1, 4);
            if (enterNumber == 1) {
                enemylist.remove(0);
                new Battle(player, enemy1).run();
                break;
            } else if (enterNumber == 2) {
                enemylist.remove(1);
                new Battle(player, enemy2).run();
                break;
            } else if (enterNumber == 3) {
                enemylist.remove(2);
                new Battle(player, enemy3).run();
                break;
            } else if (enterNumber == 4) {
                break;
            }
        } while (true);
    }
}
