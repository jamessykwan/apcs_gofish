import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CardsPanel extends JLayeredPane implements MouseListener {

    final public static int SPACING = 150; // Spacing between overlapping cards
    final private static Color PLAYING_BACKGROUND = new Color(0xAFFF8A);
    Player player;
    CardLabel cardSelected;
    boolean isSelected;
    private JLabel remainingCardsLabel;
    private JLabel remainingCardsCount;

    void initComponents() {
        remainingCardsLabel = new JLabel("Remaining cards:");
        add(remainingCardsLabel);
        remainingCardsLabel.setVisible(true);

        remainingCardsCount = new JLabel("0");
        remainingCardsCount.setVisible(true);
        add(remainingCardsCount);
    }

    void setCards(HashMap<String, ArrayList<Card>> cards, boolean revealed) {
        removeAll();
        int index = 0;
        for (Map.Entry<String, ArrayList<Card>> entry : cards.entrySet()) {
            //System.out.println(entry.getKey() + "/" + entry.getValue());
            for (Card card : entry.getValue()) {
                System.out.println(card.getRank() + card.getSuit());
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
        revalidate();
    }

    public void isNotPlaying() {
        setBackground(null);
    }

    public void addCard(CardLabel cardLabel, int index) {
        // Add card with appropriate z-index
        this.add(cardLabel, new Integer(index));
        cardLabel.addMouseListener(this);
        cardLabel.setLocation(100 + SPACING * index, 80);
    }

    public final void updatePanels() {
        updateHand();
        //updateComplete();
    }

    public void updateHand() {
        HashMap<String, ArrayList<Card>> hand = player.getHand();
        setCards(hand, true);
        updateInfoBox(remainingCardsCount, hand.size());
    }

    private void updateInfoBox(JLabel label, int num) {
        label.setText(Integer.toString(num));
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


    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        cardSelected = (CardLabel) label;
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
        JLabel label = (JLabel) e.getSource();
        label.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

    }
}