import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

class Player {

    private HashMap<String, ArrayList<Card>> hand;

    private ArrayList<Card> hand_all = new ArrayList<>();
    private Player opponent;

    private Deck deck;


    Player(Deck deck) {
        hand = new HashMap<>();
        deck.deal(hand, "gofish");
        for (ArrayList<Card> cards : hand.values()) {
            hand_all.addAll(cards);
        }
        this.deck = deck;
    }

    public Deck doTurn(Deck deck, Player opponent) {
        this.deck = deck;
        this.opponent = opponent;
        ArrayList<Card> foundCards = new ArrayList<Card>();
        return deck;
    }

    boolean makeCardRequest(Player opponent, String rank) {
        this.opponent = opponent;
        boolean opponentHasCard = false;

        //if the card is in the opponent's hand, then return true. otherwise, false.

        if (opponent.getHand().containsKey(rank)) {
            opponentHasCard = true;
            return opponentHasCard;
        }


        return opponentHasCard;
    }

    void respondCardRequest(Player player, Player opponent, Card a) {

        if (opponent.hand.containsKey(a.getRank())) {
            ArrayList<Card> temp = opponent.hand.get(a.getRank());
            opponent.hand.remove(a.getRank());
            player.hand.put(a.getRank(), temp);
        } else {
            deck.dealOne(player.hand);
        }
    }

    public void removeCard(Card card) {
        if (!hand_all.contains(card)) {
            throw new NoSuchElementException();
        }
        hand_all.remove(card);
        hand.get(card.getRank()).remove(card);

    }

    public boolean contains(Card card) {
        return hand_all.contains(card);
    }

    public void addCard(Card card) {
        hand.get(card.getRank()).add(card);
        hand_all.add(card);
    }

    public boolean canPlay() {
        return !hand.isEmpty();
    }

    HashMap<String, ArrayList<Card>> getHand() {
        return hand;
    }
}
