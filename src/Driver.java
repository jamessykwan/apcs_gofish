import java.util.HashMap;
import java.util.Map;

public class Driver {
    public static void main(String[] args) {
        Game game = new Game();
        HashMap<String, String> p1hand = new HashMap<String, String>();
        HashMap<String, String> p2hand = new HashMap<String, String>();
        //game.Initiate();
        //game.Shuffle();
        //System.out.println(game.deck);
        //game.deal(p1hand, p2hand);
        for (Map.Entry<String, String> entry : p1hand.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }

        System.out.println();
        for (Map.Entry<String, String> entry : p2hand.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }
    }
}




