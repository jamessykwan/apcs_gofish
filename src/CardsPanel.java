import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Map;

public class CardsPanel extends JLayeredPane implements MouseListener {

    final public static int SPACING = 150; // Spacing between overlapping cards
    final private static Color PLAYING_BACKGROUND = new Color(0xAFFF8A);
    Player player;
    CardLabel cardSelected;
    boolean isSelected;
    private int zindex;
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

    void setCards(Player player, boolean revealed) {
        removeAll();
        int index = 0;
        for (Map.Entry<String, ArrayList<Card>> entry : player.getHand().entrySet()) {
            //System.out.println(entry.getKey() + "/" + entry.getValue());
            for (Card card : entry.getValue()) {
                //System.out.println(card.getRank() + card.getSuit());
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

    public final void updatePanels(Player player) {
        updateHand(player);
        //updateComplete();
    }

    public void updateHand(Player player) {
        setCards(player, true);
        updateInfoBox(remainingCardsCount, player.getHand().size());
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

    public void updatePreferredSize(int numCards) {
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
        zindex = getComponentZOrder(label);

        label.setSize(225, 350);
        label.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        setLayer(label, highestLayer());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        label.setSize(150, 350);
        label.setBorder(null);
        setLayer(label, zindex);

    }
}