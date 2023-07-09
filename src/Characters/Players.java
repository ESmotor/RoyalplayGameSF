package Characters;

import Characters.Actions.Fighting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Players implements Fighting {
    // Поля класса
    private static List<Integer> lvls = new ArrayList<>();

    {
        lvls.add(0);
        lvls.add(1);
        lvls.add(1000);
        for (int i = 3; i < 21; i++) {
            lvls.add((lvls.get(i - 1) / 2 + lvls.get(i - 1)) / 100 * 100);
        }
    }

    private static final int MAX_PlAYERS = 1;
    private static int totalPlayers = 0;
    private static List<Players> playersList = new ArrayList<>();
    private String name;
    private int healthPoint;
    private int strength;
    private int agility;
    private int gold;
    private int experience;
    private int currentLVL;
    private int newPoints;
    private boolean isAlive;
    // Геттеры и Сеттеры


    public static int getTotalPlayers() {
        return totalPlayers;
    }

    public static List<Players> getPlayersList() {
        return playersList;
    }

    public String getName() {
        return name;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getGold() {
        return gold;
    }

    public int getExperience() {
        return experience;
    }

    public int getCurrentLVL() {
        return currentLVL;
    }

    public int getNewPoints() {
        return newPoints;
    }

    private Players(String name) {
        this.name = name;
        this.healthPoint = 100;
        this.strength = 3;
        this.agility = 3;
        this.gold = 10;
        this.currentLVL = 1;
        this.experience = 1;
        this.newPoints = 4;
        this.isAlive = true;
        playersList.add(this);
        totalPlayers++;
    }

    public Players() {
        this.name = "Valera";
        this.healthPoint = 100;
        this.strength = 5;
        this.agility = 5;
        this.gold = 10;
        this.currentLVL = 1;
        this.experience = 1;
        this.newPoints = 0;
        this.isAlive = true;
        playersList.add(this);
        totalPlayers++;
    }


    public static Players createNewPlayer() {
        if (totalPlayers < MAX_PlAYERS) {
            Players newPlayer = new Players("PlayerName");
            System.out.println("Создание нового персонажа:");
            createName(newPlayer);
            newPlayer.distributeAbilities();
            return newPlayer;
        } else {
            System.out.println("Создано максимальное количество персонажей");
        }
        return null;
    }

    private static void createName(Players newPlayer) {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите имя персонажа:");
        String wrongSymbols;
        String correctName;
        do {
            wrongSymbols = "";
            correctName = console.nextLine();
            String text = correctName;

            Pattern pattern = Pattern.compile("[^\\s[a-zA-Z]]");
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                wrongSymbols += text.substring(matcher.start(), matcher.end());
            }
            if (!wrongSymbols.equals("")) {
                System.out.println("Не правильное имя персонажа, попробуйте снова:");
            }
        } while (!wrongSymbols.equals(""));
        System.out.println("Отлично. Имя вашего персонажа: " + correctName);
        newPlayer.name = correctName;
    }


    public static int enterNum(int low, int up) {
        Scanner scanner = new Scanner(System.in);
        String strLine;
        do {
            strLine = scanner.nextLine();
            String wrongSymbols = "";
            Pattern pattern = Pattern.compile("[[^\\d]\\s]");
            Matcher matcher = pattern.matcher(strLine);
            while (matcher.find()) {
                wrongSymbols += strLine.substring(matcher.start(), matcher.end());
            }
            if (!wrongSymbols.equals("") || Integer.parseInt(strLine) > up || Integer.parseInt(strLine) < low) {
                System.out.println("Не веррный ввод:");
                continue;
            }
            return Integer.parseInt(strLine);
        } while (true);


    }

    private static void checkStats(Players player) {
        player.healthPoint = player.strength * 20;
    }


    public void lvlUP() {
        int nextLvL = this.currentLVL + 1;
        if (this.getExperience() >= lvls.get(nextLvL)) {
            this.currentLVL = nextLvL;
            this.newPoints += 3;
            System.out.println("Уровень повышен");
            System.out.println("Уровень: " + currentLVL + " Очков способностей: " + this.newPoints);
        } else {
            System.out.printf("Не хватает опыта. Нужно еще %d опыта.\n", lvls.get(nextLvL) - this.experience);
        }
    }

    public void distributeAbilities() {
        Scanner scanner = new Scanner(System.in);
        int enterNumber;
        System.out.println("Распределите очки");
        do {
            System.out.printf("Сила = %d, Ловкость = %d, Доступно очков = %d:\n",
                    this.getStrength(), this.getAgility(), newPoints);
            System.out.println("1) Увеличить силу.");
            System.out.println("2) Увеличить ловкость.");

            enterNumber = enterNum(1, 2);
            if (enterNumber == 1) {
                System.out.println("Сила увеличина");
                this.strength++;
                newPoints--;
            } else if (enterNumber == 2) {
                System.out.println("Ловкость увеличина");
                this.agility++;
                newPoints--;
            }
        } while (newPoints > 0);
        System.out.printf("Сила = %d, Ловкость = %d, Доступно очков = %d:\n",
                this.getStrength(), this.getAgility(), newPoints);
        this.newPoints = 0;
    }


    private double criticalChance() {
        double base = 10;
        double bonus = this.agility * 0.5;
        return base + bonus;
    }

    private int attackPower() {
        double critChance = this.criticalChance();
        Random rnd = new Random();
        int rndNumber = rnd.nextInt(1000) + 1;
        int multi = (int) (critChance * 10) >= rndNumber ? 2 : 1;
        return this.strength * multi;
    }

    @Override
    public void attack(Fighting rival) {
        int attackPower = this.attackPower();
        if (this.isAlive) {
            System.out.printf("%s пытается атаковать противника силой: %d урона\n",this.name,attackPower);
            rival.defence(attackPower);
        }
    }

    @Override
    public void defence(int dmg) {
        this.healthPoint -= dmg;
    }

    @Override
    public boolean isAliveNow() {
        if (this.healthPoint <= 0) {
            this.isAlive = false;
            System.out.printf("Игрок %s героически погиб в битве...\n", this.name);
        }
        return this.healthPoint > 0;
    }

    @Override
    public String toString() {
        return "Players{" +
                "name='" + name + '\'' +
                ", healthPoint=" + healthPoint +
                ", strength=" + strength +
                ", agility=" + agility +
                ", gold=" + gold +
                ", experience=" + experience +
                ", lvl=" + currentLVL +
                '}';
    }
}
