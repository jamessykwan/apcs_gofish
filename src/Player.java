import java.util.*;

class Player {

    private HashMap<String, ArrayList<Card>> hand;
    private ArrayList<Card> hand_all = new ArrayList<>();
    private Collection<Series> completeSeries = new LinkedList<>();
    private Deck deck;


    Player(Deck deck) {
        hand = new HashMap<>();
        deck.deal(hand, "gofish");
        for (ArrayList<Card> cards : hand.values()) {
            hand_all.addAll(cards);
        }
        this.deck = deck;
    }

    boolean makeCardRequest(Player opponent, String rank) {
        //if the card is in the opponent's hand, then return true. otherwise, false.
        return opponent.hand.containsKey(rank);
    }

    void respondCardRequest(Player opponent, Card a) {

        if (opponent.hand.containsKey(a.getRank())) {
            ArrayList<Card> temp = hand.get(a.getRank());
            opponent.hand.remove(a.getRank());
            if (hand.containsKey(a.getRank())) {
                ArrayList<Card> temp1 = hand.get(a.getRank());
                temp1.addAll(temp);
                hand.put(a.getRank(), temp1);
            } else {
                hand.put(a.getRank(), temp);
            }
        }
    }

    void goFish() {
        deck.dealOne(this.hand);
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


    public boolean canPlay() {
        return !hand.isEmpty();
    }

    HashMap<String, ArrayList<Card>> getHand() {
        return hand;
    }

    public Collection<Series> getCompleteSeries() {
        return completeSeries;
    }

    /**
     * @return total number of cards player has (in hand and in completed series
     */
    public int numCards() {
        int size = 0;
        int cardsInCompletedSeries = completeSeries.size() * 4;
        for (ArrayList<Card> list : hand.values()) {
            size += list.size();
        }
        return (size + cardsInCompletedSeries);
    }

    private Set<String> ranks() {
        return hand.keySet();
    }


    private int seriesSize(String rank) {
        return hand.get(rank).size();
    }

    /**
     * Check if there's a complete series in hand
     *
     * @return the completed series if it exists, null otherwise
     */
    Series checkComplete() {
        for (String rank : ranks()) {
            if (seriesSize(rank) == 4) {
                ArrayList<Card> cards = hand.remove(rank);
                Series series = new Series(rank, cards);
                completeSeries.add(series);
                return series;
            }
        }
        return null;
    }
}