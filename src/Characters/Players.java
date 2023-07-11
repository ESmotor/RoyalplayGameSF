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
    private static final double hard = 0.8;
    private static final int speed = 3;
    private String name;
    private int strength;
    private int agility;
    private int maxHealth;
    private int healthPoint;
    private int gold;
    private int experience;
    private int currentLVL;
    private int newPoints;
    private boolean isAlive;

    public static List<Integer> getLvls() {
        return lvls;
    }
    // Геттеры и Сеттеры


    public static int getTotalPlayers() {
        return totalPlayers;
    }

    public static List<Players> getPlayersList() {
        return playersList;
    }

    public int getMaxHealth() {
        return maxHealth;
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
        this.strength = 3;
        this.agility = 3;
        this.maxHealth = this.strength * 20;
        this.healthPoint = this.maxHealth;
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
        this.strength = 5;
        this.agility = 5;
        this.maxHealth = this.strength * 20;
        this.healthPoint = maxHealth;
        this.gold = 1000;
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
        StringBuilder wrongSymbols;
        String correctName;
        do {
            wrongSymbols = new StringBuilder();
            correctName = console.nextLine();
            String text = correctName;

            Pattern pattern = Pattern.compile("[^\\s[a-zA-Z]]");
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                wrongSymbols.append(text, matcher.start(), matcher.end());
            }
            if (!"".equals(wrongSymbols.toString())) {
                System.out.println("Не правильное имя персонажа, попробуйте снова:");
            }
        } while (!wrongSymbols.toString().equals(""));
        System.out.println("Отлично. Имя вашего персонажа: " + correctName + "\n");
        newPlayer.name = correctName;
    }


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

    private void checkStats() {
        int nowMaxHealth = this.maxHealth;
        int correctMaxHealth = this.strength * 20;
        if (nowMaxHealth < correctMaxHealth) {
            this.healthPoint += (correctMaxHealth - nowMaxHealth);
            this.maxHealth = this.strength * 20;
        }
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

    public void getAward(int exp, int gold) {
        if (exp < 0) {
            System.out.println("Получаемый опыт не может быть отрицательным. Вернули 0");
            exp = 0;
        }
        if (gold < 0) {
            System.out.println("Получаемое золото не может быть отрицательным. Вернули 0");
            gold = 0;
        }
        this.experience += exp;
        this.gold += gold;
    }

    public void distributeAbilities() {
        int enterNumber;

        if (this.newPoints > 0) {
            do {
                System.out.println("\nРаспределите очки");
                System.out.printf("Сила = %d, Ловкость = %d, Доступно очков = %d:\n",
                        this.getStrength(), this.getAgility(), this.newPoints);
                System.out.println("1) Увеличить силу.");
                System.out.println("2) Увеличить ловкость.");

                enterNumber = enterNum(1, 2);
                if (enterNumber == 1) {
                    System.out.println("Сила увеличина\n");
                    this.strength++;
                    this.newPoints--;
                } else if (enterNumber == 2) {
                    System.out.println("Ловкость увеличина\n");
                    this.agility++;
                    this.newPoints--;
                }
            } while (this.newPoints > 0);
            this.checkStats();
        } else {
            System.out.println("\nУ вас нет очков для распределения");
        }

        System.out.printf("Сила = %d, Ловкость = %d, Доступно очков = %d:\n",
                this.getStrength(), this.getAgility(), newPoints);

    }

    private boolean isDodge() {
        double base = 10;
        double dodgeChance = base + this.agility;
        Random rnd = new Random();
        int rndNumber = rnd.nextInt(1000) + 1;
        return (dodgeChance * 10) >= rndNumber;
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
    public void takeCounterAttack(int dmg) {
        dmg = (int) (dmg * hard * speed);
        this.healthPoint -= dmg;
        System.out.printf("%s получает %d урона от контратаки.(Здоровье:%d/%d)\n"
                , this.name, dmg, Math.max(0, this.healthPoint), this.maxHealth);
    }

    @Override
    public void attack(Fighting rival) {
        int attackPower = this.attackPower();
        String critHit = attackPower > this.strength ? " КРИТИЧЕСКИЙ" : "";
        if (this.isAlive) {
            System.out.printf("\n%s наносит%s удар\n", this.name, critHit);
            rival.defence(attackPower, this);
        }
    }

    @Override
    public void defence(int dmg, Fighting rival) {
        dmg = (int) (dmg * hard * speed);
        if (this.isDodge()) {
            System.out.printf("%s увернулся от атаки и проводит контратаку\n", this.name);
            rival.takeCounterAttack(this.attackPower() / 2);
        } else {
            this.healthPoint -= dmg;
            System.out.printf("%s получает %d урона от атаки.(Здоровье:%d/%d)\n"
                    , this.name, dmg, Math.max(0, this.healthPoint), this.maxHealth);
        }
    }

    @Override
    public boolean isAliveNow() {
        if (this.healthPoint <= 0) {
            this.isAlive = false;
        }
        return this.healthPoint > 0;
    }

    public void buySomething() {
        NpsTraders shop = new NpsTraders(this);
        String item;
        String itemName;
        int itemQnt;
        int itemPrice;

        do {
            System.out.printf("Сила = %d, Ловкость = %d, Здоровье = %d/%d, Золото = %d:\n",
                    this.getStrength(), this.getAgility(), this.getHealthPoint(),this.getMaxHealth(), this.getGold());
            item = shop.buyedItem();
            itemName = item.split(",")[0];
            itemQnt = Integer.parseInt(item.split(",")[1]);
            itemPrice = Integer.parseInt(item.split(",")[2]);

            if (itemName.equals("strength")) {
                this.strength += itemQnt;
                this.gold -= itemPrice;
                this.checkStats();
                System.out.println("Зелье силы выпито.\n");
            }
            if (itemName.equals("agility")) {
                this.agility += itemQnt;
                this.gold -= itemPrice;
                System.out.println("Зелье ловкости выпито.\n");
            }
            if (itemName.equals("health")) {
                this.healthPoint = Math.min((this.healthPoint + itemQnt), this.maxHealth);
                this.gold -= itemPrice;
                System.out.println("Зелье лечения выпито.\n");
            }
            if (itemName.equals("exit")) {
                System.out.println("Выходим из магазина.\n");
            }
        } while (!itemName.equals("exit"));

        System.out.printf("Сила = %d, Ловкость = %d, Здоровье = %d, Золото = %d:\n",
                this.getStrength(), this.getAgility(), this.getHealthPoint(), this.getGold());
    }


    @Override
    public String toString() {
        return "Players{" +
                "name='" + name + '\'' +
                ", strength=" + strength +
                ", agility=" + agility +
                ", maxHealth=" + maxHealth +
                ", healthPoint=" + healthPoint +
                ", gold=" + gold +
                ", experience=" + experience +
                ", currentLVL=" + currentLVL +
                ", newPoints=" + newPoints +
                ", isAlive=" + isAlive +
                '}';
    }
}
