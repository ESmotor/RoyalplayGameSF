package Characters;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NpsTraders {
    private List<String> stock = new ArrayList<>();
    private Players buyer;

    public NpsTraders(Players buyer) {
        this.buyer = buyer;
        this.stock.add("1) Зелье силы. Цена = 50");
        this.stock.add("2) Зелье ловкости. Цена = 50");
        this.stock.add("3) Зелье лечения. Цена = 60");
        this.stock.add("4) Выход.");

    }

    public List<String> getStock() {
        return stock;
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

    public void printStockList() {
        stock.forEach(System.out::println);
    }

    public String buyedItem() {
        String result = "";
        do {
            System.out.println("\nЧто хотите купить?");
            this.printStockList();
            int enterNumber = enterNum(1, 4);
            switch (enterNumber) {
                case 1 -> {
                    result = "strength,1,50";
                    break;
                }
                case 2 -> {
                    result = "agility,1,50";
                    break;
                }
                case 3 -> {
                    result = "health,60,60";
                    break;
                }
                case 4 -> {
                    result = "exit,0,0";
                    break;
                }
            }
            if (Integer.parseInt(result.split(",")[2]) > buyer.getGold()) {
                System.out.println("Недостаточно денег для покупки.\n");
                result = "error";
            }
            if (result.split(",")[0].equals("health") && buyer.getMaxHealth() <= buyer.getHealthPoint()) {
                System.out.println("Уже максимальное здоровье.\n");
                result = "error";
            }
        } while (result.equals("error"));
        return result;
    }


}
