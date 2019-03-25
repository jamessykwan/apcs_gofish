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
    private CardsPanel lastClicked;
    private MouseListener listener = new LastClickedListener();

    public CardsPanel addPlayer(Player player) {
        this.validate();
        CardsPanel panel = new CardsPanel();
        panel.setOpaque(true);
        panel.setCards(player.getHand(), true);
        panel.addMouseListener(listener);
        this.add(panel);
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

    public void setCurrentPlayer(Player player) {
        if (current != null) {
            current.isNotPlaying();
        }
        CardsPanel panel = playerPanels.get(player);
        panel.isPlaying();
        current = panel;
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
