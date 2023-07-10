package Characters;

import java.util.List;
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
            System.out.println("3) Выбрать противника");
            System.out.println("4) Выход из игры");
            enterNumber = enterNum(1, 4);
            if (enterNumber == 1) {
                this.characterMenu();
            } else if (enterNumber == 2) {
                this.shop.buyedItem();
            } else if (enterNumber == 3) {

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


}
