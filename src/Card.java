import javax.swing.*;
import java.io.File;

public class Card {
    private String suit;
    private String rank;
    private ImageIcon image;

    public Card(String a, String b) {
        suit = a;
        rank = b;
        String path = "images/" + rank + suit + ".png";
        File f = new File(path);
        image = new ImageIcon(path);
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public String toString() {
        return (rank + " of " + suit);
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}