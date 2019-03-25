import java.util.ArrayList;
import java.util.Iterator;

class Game {

    ArrayList<Player> players = new ArrayList<>();
    // gui instance
    private GUI gui;

    Game(GUI gui) {
        this.gui = gui;


    }

    void start() {

        // Run the GUI
        gui.startGame();
        // Main game loop
        loop();
        //end();
    }


    private void loop() {
        System.out.println("Starting game loop");

        // Create a temp list to cycle
        players = new ArrayList<>(gui.gamePanel.playerPanels.keySet());
        Iterator<Player> iterator = players.iterator();
        while (!players.isEmpty()) {
            Player player = iterator.next();

            if (player.canPlay()) {
                playFullTurn(player, iterator.next());
            } else {
                // Player is out of the game
                players.remove(player);
            }

            if (players.size() == 1 || !canPlay()) {
                // One player is left or no more series can be assembled
                break;
            }
            iterator = players.listIterator();
            iterator.next();


        }
    }

    private void playFullTurn(Player player, Player opponent) {
        System.out.println("select card");
        gui.playerTurn(player);
        while (true) {
            System.out.println(gui.gamePanel.current.isSelected());

            if (gui.gamePanel.current.isSelected()) {
                CardLabel cardLabel = gui.gamePanel.current.getCardSelected();
                System.out.println(cardLabel.getCard().getRank() + cardLabel.getCard().getSuit());
                if (player.makeCardRequest(opponent, cardLabel.getCard().getRank())) {
                    System.out.println("Player contains");
                    player.respondCardRequest(player, opponent, cardLabel.getCard());
                    gui.gamePanel.current.isSelected = false;
                } else {
                    System.out.println("abc");
                    gui.gamePanel.current.isSelected = false;
                    break;
                }
            }
        }

    }

    // needs to be implemented
    private boolean canPlay() {
        return (gui.deckPanel.getDeck().getSize() != 0 || players.get(1).getHand().size() != 0) && players.get(0).getHand().size() != 0;
    }

    // needs to be implemented
    private boolean playSingleTurn(Player player) {
        boolean anotherTurn = false;

        //player.makeCardRequest(player.get)
        return anotherTurn;
    }


}
