import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CardLabel extends JLabel {
    final public static int CARD_WIDTH = 150;

    final public static int CARD_HEIGHT = 350;

    final private static ImageIcon BACK = new ImageIcon("back.png");


    final private static Icon EMPTY = new ImageIcon("back.png");

    private Card card;

    private ImageIcon icon;

    public CardLabel(Card card, boolean revealed) {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        this.card = card;
        setVerticalTextPosition(JLabel.CENTER);
        setHorizontalTextPosition(JLabel.CENTER);
        setSize(CARD_WIDTH, CARD_HEIGHT);
        icon = card.getImage();
        setRevealed(revealed);
        setBorder(border);
    }

    public Card getCard() {
        return card;
    }

    final public void setRevealed(boolean flag) {
        if (flag) {
            // Reveal card
            if (icon != null) {
                // Card icon available - display it
                setIcon(icon);
            } else {
                // No icon available - display card's name
                setIcon(EMPTY);
                setText(card.getRank() + " " + card.getSuit());
            }
        } else {
            // Conceal card
            setIcon(BACK);
            setText(null);
        }

    }
}
