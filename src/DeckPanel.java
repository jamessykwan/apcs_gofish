import javax.swing.*;

public class DeckPanel extends JPanel {
    private Deck deck;
    private JLabel deckLabel;

    DeckPanel() {
    }

    public void initComponents() {
        this.deck = new Deck();
        deck.Initiate();
        deck.shuffle();
        deckLabel = new JLabel("deck");
        this.revalidate();
    }

    public Deck getDeck() {
        return deck;
    }
}
