import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CardsPanel extends JLayeredPane {

    final public static int SPACING = 150; // Spacing between overlapping cards
    final private static Color PLAYING_BACKGROUND = new Color(0xFFFEDF);
    Player player;
    CardLabel cardSelected;
    boolean isSelected;

    public void setCards(HashMap<String, ArrayList<Card>> cards, boolean revealed) {
        removeAll();
        int index = 0;
        for (Map.Entry<String, ArrayList<Card>> entry : cards.entrySet()) {
            //System.out.println(entry.getKey() + "/" + entry.getValue());
            for (Card card : entry.getValue()) {
                CardLabel cardLabel = new CardLabel(card, revealed);
                addCard(cardLabel, index);
                cardLabel.setRevealed(true);
                index++;

            }
        }
        updatePreferredSize(index);
        this.setVisible(true);
        this.revalidate();
    }

    public void isPlaying() {
        setBackground(PLAYING_BACKGROUND);
    }

    public void isNotPlaying() {
        setBackground(null);
    }

    public void addCard(CardLabel cardLabel, int index) {
        // Add card with appropriate z-index
        this.add(cardLabel, new Integer(index));
        cardLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardSelected = cardLabel;
                isSelected = true;
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                cardLabel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cardLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            }
        });
        System.out.println("card added");
        cardLabel.setLocation(100 + SPACING * index, 80);
    }

    public CardLabel getCardSelected() {
        return cardSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    private void updatePreferredSize(int numCards) {
        int width = (50 + (numCards - 1) * SPACING) + CardLabel.CARD_WIDTH;
        setPreferredSize(new Dimension(width, CardLabel.CARD_HEIGHT));
    }


}
