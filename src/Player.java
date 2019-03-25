import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

class Player {

    private HashMap<String, ArrayList<Card>> hand;

    private ArrayList<Card> hand_all = new ArrayList<>();
    private Player opponent;
    private boolean opponentHasCard;

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

    public boolean makeCardRequest(Player opponent, String rank) {
        this.opponent = opponent;
        opponentHasCard = false;

        //if the card is in the opponent's hand, then return true. otherwise, false.

        if (opponent.getHand().containsKey(rank)) {
            opponentHasCard = true;
            return opponentHasCard;
        }


        return opponentHasCard;
    }

    public ArrayList<Card> respondCardRequest(String rank) {

        ArrayList<Card> foundCards = new ArrayList<>(hand.get(rank));
        for (Card c : foundCards) {
            removeCard(c);
        }
        return foundCards;
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
