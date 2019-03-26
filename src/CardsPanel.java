import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CardsPanel extends JLayeredPane implements MouseListener {

    private final static int SPACING = 150; // Spacing between overlapping cards
    final private static Color PLAYING_BACKGROUND = new Color(0xFF9189);
    Player player;
    private CardLabel cardSelected;
    boolean isSelected;
    private int zindex;
    private JLabel playerStatus;
    private JLabel remainingCardsCount;
    private boolean showCompletedSeries = false;
    private JLabel completedSeriesCount;

    public void showCompletedSeries(boolean flag) {
        showCompletedSeries = flag;
    }

    void initComponents() {
        completedSeriesCount = new JLabel("Series: 0");
        completedSeriesCount = new JLabel("Series: 0");
        JLabel remainingCardsLabel = new JLabel("Remaining cards:");
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

    void isPlaying() {
        setBackground(PLAYING_BACKGROUND);
        revalidate();
    }

    void isNotPlaying() {
        Color color = new Color(97, 95, 94);
        setBackground(color);
    }

    private void addCard(CardLabel cardLabel, int index) {
        // Add card with appropriate z-index
        this.add(cardLabel, new Integer(index));
        cardLabel.addMouseListener(this);
        cardLabel.setLocation(100 + SPACING * index, 80);
    }

    void updatePanels(Player player, boolean revealed) {
        updateHand(player, revealed);
        updateSeries(player);
    }

    JLabel getCompletedSeriesCount() {
        return completedSeriesCount;
    }

    private void updateSeries(Player player) {
        player.checkComplete();
        Collection<Series> completeSeries = player.getCompleteSeries();
        updateInfoBox(completedSeriesCount, completeSeries.size());
    }

    private void updateHand(Player player, boolean revealed) {
        setCards(player, revealed);
        updateInfoBox(remainingCardsCount, player.getHand().size());
    }

    private void updateInfoBox(JLabel label, int num) {
        label.setText("Series count : " + num);
    }

    CardLabel getCardSelected() {
        return cardSelected;
    }

    boolean isSelected() {
        return isSelected;
    }

    private void updatePreferredSize(int numCards) {
        int width = (50 + (numCards - 1) * SPACING) + CardLabel.CARD_WIDTH;
        setPreferredSize(new Dimension(width + 200, CardLabel.CARD_HEIGHT + 50));
        revalidate();
        repaint();
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