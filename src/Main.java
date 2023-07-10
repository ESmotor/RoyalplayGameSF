import Characters.Arena;
import Characters.Players;

public class Main {

    public static void main(String[] args) {
        Arena arena = new Arena();
        Players player = arena.startMenu();
        arena.mainMenu();




    }
}