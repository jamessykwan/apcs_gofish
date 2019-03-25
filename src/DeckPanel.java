import javax.swing.*;

class DeckPanel extends JPanel {
    private Deck deck;
    private JLabel deckLabel;

    DeckPanel() {
    }

    void initComponents() {
        this.deck = new Deck();
        deck.Initiate();
        deck.shuffle();
        deckLabel = new JLabel("deck");
        this.revalidate();
    }

    Deck getDeck() {
        return deck;
    }
}
