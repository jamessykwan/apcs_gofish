import javax.swing.*;
import java.io.File;

public class Card {
    private String suit;
    private String Rank;
    private ImageIcon image;

    public Card(String a, String b) {
        suit = a;
        Rank = b;
        String path = "images/" + Rank + suit + ".png";
        File f = new File(path);
        System.out.println(f.exists());
        image = new ImageIcon(path);
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
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