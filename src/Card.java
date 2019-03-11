public class Card {
    private String suit;
    private String Rank;
    private String imagePng;

    public Card(String a, String b) {
        suit = a;
        Rank = b;
    }

    public String toString() {
        return (Rank + " of " + suit);
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }
}