import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

class CardLabel extends JLabel {

    final static int CARD_WIDTH = 150;

    final static int CARD_HEIGHT = 350;

    final private static ImageIcon BACK = new ImageIcon("src/images/back.png");


    final private static Icon EMPTY = new ImageIcon("src/images/back.png");

    private Card card;

    private ImageIcon icon;

    CardLabel(Card card, boolean revealed) {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        this.card = card;
        setVerticalTextPosition(JLabel.CENTER);
        setHorizontalTextPosition(JLabel.CENTER);
        setSize(CARD_WIDTH, CARD_HEIGHT);
        icon = card.getImage();
        setRevealed(revealed);
        setBorder(border);
        setOpaque(true);
        Color bgColor = new Color(241, 241, 234);
        setBackground(bgColor);
    }

    Card getCard() {
        return card;
    }

    final void setRevealed(boolean flag) {
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
