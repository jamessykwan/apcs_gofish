import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Deck {
    protected ArrayDeque<Card> deck = new ArrayDeque<Card>();

    public void Initiate() {
        String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
        String[] ranks = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                deck.push(new Card(suits[i], ranks[j]));
            }
        }
    }

    public void Shuffle() {
        ArrayList<Card> shuffledDeck = new ArrayList<Card>();

        int startdecksize = deck.size();
        for (int i = 0; i < startdecksize; i++) {
            shuffledDeck.add(deck.remove());
        }

        ArrayList<Card> temp = new ArrayList<Card>();
        for (int i = 0; i < shuffledDeck.size(); i++) {
            int rand = (int) (Math.random() * shuffledDeck.size());
            temp.add(shuffledDeck.remove(rand));
        }

        for (int i = 0; i < shuffledDeck.size(); i++) {
            deck.push(temp.get(i));
        }
    }


    public void deal(HashMap<String, String> a, HashMap<String, String> b) {
        while (a.size() < 5) {
            Card curr = deck.remove();
            //System.out.println("p1 " + curr.getRank() + curr.getSuit());
            a.put(curr.getRank(), curr.getSuit());
        }
        while (b.size() < 5) {
            Card curr = deck.remove();
            //System.out.println("p1 " + curr.getRank() + curr.getSuit());
            b.put(curr.getRank(), curr.getSuit());
        }
    }

    public void dealOne(HashMap<String, String> a, HashMap<String, String> b) {
        Card curr = deck.remove();
        a.put(curr.getRank(), curr.getSuit());
        Card curr1 = deck.remove();
        b.put(curr1.getRank(), curr1.getSuit());
    }

}
