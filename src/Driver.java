import java.util.HashMap;

public class Driver {
    public static void main(String[] args) {
        InitiateGame game = new InitiateGame();
        HashMap<String, String> p1hand = new HashMap<String, String>();
        HashMap<String, String> p2hand = new HashMap<String, String>();
        game.Initiate();
        game.Shuffle();
        System.out.println(game.deck);
        game.deal(p1hand, p2hand);
        System.out.println(p1hand.toString());
        System.out.println(p2hand.toString());
    }
}