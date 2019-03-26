import java.util.ArrayList;

public class Series {

    private String rank;

    private ArrayList<Card> cards;

    Series(String rank, ArrayList<Card> cards) {
        this.rank = rank;
        this.cards = cards;
    }

    public String getRank() {
        return rank;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return "Series{rank=" + rank + "}";
    }

}