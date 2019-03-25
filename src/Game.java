import java.util.ArrayList;

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
        int turncount = 0;

        // Create a temp list to cycle
        players = new ArrayList<>(gui.gamePanel.playerPanels.keySet());
        while (!players.isEmpty()) {
            int curr = turncount % 2;
            Player player = players.get(curr);

            if (canPlay()) {
                if (curr == 1) {
                    gui.gamePanel.playerPanels.get(players.get(1)).isPlaying();
                    gui.gamePanel.playerPanels.get(players.get(0)).isNotPlaying();
                    playFullTurn(player, players.get(0));
                    gui.gamePanel.current.setCards(players.get(1).getHand(), true);
                    gui.gamePanel.playerPanels.get(gui.gamePanel.player1).setCards(players.get(0).getHand(), true);
                } else {
                    gui.gamePanel.playerPanels.get(players.get(0)).isPlaying();
                    gui.gamePanel.playerPanels.get(players.get(1)).isNotPlaying();
                    playFullTurn(player, players.get(1));
                    gui.gamePanel.current.setCards(players.get(0).getHand(), true);
                    gui.gamePanel.playerPanels.get(gui.gamePanel.player1).setCards(players.get(0).getHand(), true);

                }
            } else {
                // Player is out of the game
                players.remove(player);
            }

            if (players.size() == 1 || !canPlay()) {
                // One player is left or no more series can be assembled
                break;
            }

            turncount++;
        }
    }

    private void playFullTurn(Player player, Player opponent) {
        while (true) {
            gui.playerTurn(player);
            if (gui.gamePanel.current.isSelected()) {
                CardLabel cardLabel = gui.gamePanel.current.getCardSelected();
                System.out.println(cardLabel.getCard().getRank() + cardLabel.getCard().getSuit());
                if (player.makeCardRequest(opponent, cardLabel.getCard().getRank())) {
                    System.out.println("Player contains");
                    player.respondCardRequest(opponent, cardLabel.getCard());
                    gui.gamePanel.current.isSelected = false;
                } else {
                    System.out.println("abc");
                    player.goFish();
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