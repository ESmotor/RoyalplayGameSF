import Characters.Battle;
import Characters.Players;
import Characters.Skeleton;

public class Main {

    public static void main(String[] args) {
        Players player = new Players();
        Skeleton skeleton = new Skeleton();
        new Battle(player,skeleton).fight();

        System.out.println(player);
        System.out.println(skeleton);
    }
}