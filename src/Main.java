import Characters.Battle;
import Characters.Players;
import Characters.Skeleton;

public class Main {

    public static void main(String[] args) {
        Players player = new Players();
        Skeleton skeleton = new Skeleton();
        System.out.printf("Бой начинается между %s и %s!.\n",player.getName(),skeleton.getName());
        while (true) {
            if (player.isAliveNow()) {
                player.attack(skeleton);
            } else {
                System.out.printf("Монстр %s победил в битве!\n",skeleton.getName());
                break;
            }
            if (skeleton.isAliveNow()) {
                skeleton.attack(player);
            } else {
                System.out.printf("Игрок %s победил в битве\n!",player.getName());
                break;
            }
        }

        System.out.println(player);
        System.out.println(skeleton);
    }
}