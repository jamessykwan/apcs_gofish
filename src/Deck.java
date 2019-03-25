import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Deck {

    private ArrayDeque<Card> cards = new ArrayDeque<>();
    private int size;

    public void Deck() {
        String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
        String[] ranks = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                cards.push(new Card(suits[i], ranks[j]));
            }
        }
        size = cards.size();
    }


    public void Initiate() {
        String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
        String[] ranks = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                cards.push(new Card(suits[i], ranks[j]));
            }
        }
    }

    public void shuffle() {
        ArrayList<Card> shuffledDeck = new ArrayList<>();

        int startdecksize = cards.size();
        for (int i = 0; i < startdecksize; i++) {
            shuffledDeck.add(cards.remove());
        }

        ArrayList<Card> temp = new ArrayList<>();
        for (int i = 0; i < shuffledDeck.size(); i++) {
            int rand = (int) (Math.random() * shuffledDeck.size());
            temp.add(shuffledDeck.remove(rand));
        }

        for (int i = 0; i < shuffledDeck.size(); i++) {
            cards.push(temp.get(i));
        }
    }


    public void deal(HashMap<String, ArrayList<Card>> a, String nameOfGame) {
        if (nameOfGame.toLowerCase().equals("gofish")) {
            for (int i = 0; i < 5; i++) {
                Card curr = cards.remove();
                if (a.containsKey(curr.getRank())) {
                    ArrayList<Card> card = a.get(curr.getRank());
                    card.add(curr);
                    a.put(curr.getRank(), card);
                } else {
                    ArrayList<Card> card = new ArrayList<>();
                    card.add(curr);
                    a.put(curr.getRank(), card);
                }
            }

        }
    }

    public void dealOne(HashMap<String, ArrayList<Card>> a) {
        Card curr = cards.remove();
        if (a.containsKey(curr.getRank())) {
            ArrayList<Card> card = a.get(curr.getRank());
            card.add(curr);
            a.put(curr.getRank(), card);
        } else {
            ArrayList<Card> card = new ArrayList<>();
            card.add(curr);
            a.put(curr.getRank(), card);
        }
    }


    public int getSize() {
        return size;
    }
}