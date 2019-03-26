import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JPanel {

    //private static PlayerPanel p1_playerPanel;
    //private static PlayerPanel p2_playerPanel;
    private static DeckPanel deckPanel;
    public Map<Player, CardsPanel> playerPanels = new HashMap<>();
    public CardsPanel current;
    private Player player;
    private CardsPanel lastClicked;
    private MouseListener listener = new LastClickedListener();

    public CardsPanel addPlayer(Player player) {
        this.player = player;
        this.validate();
        CardsPanel panel = new CardsPanel();
        panel.initComponents();
        panel.setOpaque(true);
        panel.setCards(player, true);
        panel.addMouseListener(listener);
        this.add(panel);
        this.add(panel.add(panel.getCompletedSeriesCount()));
        panel.updatePanels(player, true);
        playerPanels.put(player, panel);
        System.out.println("panel init");
        this.setVisible(true);
        this.revalidate();
        return panel;
    }

    public void clear() {
        playerPanels.clear();
        removeAll();
        repaint();
    }


    synchronized public CardsPanel getLastClicked() {
        if (lastClicked == null) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        CardsPanel temp = lastClicked;
        lastClicked = null;
        return temp;
    }

    private void setLastClicked(CardsPanel player) {
        lastClicked = player;
    }

    void setCurrentPlayer(Player player) {
        current = playerPanels.get(player);
    }

    public Player getPlayer() {
        return player;
    }

    public CardsPanel getCardsPanel(Player player) {
        return playerPanels.get(player);
    }

    private class LastClickedListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            Object source = e.getSource();
            if (source instanceof CardsPanel) {
                setLastClicked((CardsPanel) source);
            }
        }
    }
}