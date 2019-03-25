import java.util.ArrayList;
import java.util.Iterator;

class Game {

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
        // Create a temp list to cycle
        ArrayList<Player> players = new ArrayList<>();
        Iterator<Player> iterator = players.iterator();
        while (!players.isEmpty()) {
            Player player = iterator.next();

            if (player.canPlay()) {
                playFullTurn(player);
            } else {
                // Player is out of the game
                players.remove(player);
            }

            if (players.size() == 1 || !canPlay()) {
                // One player is left or no more series can be assembled
                break;
            }

        }
    }

    private void playFullTurn(Player player) {
        while (playSingleTurn(player)) {
            continue;
        }

    }

    // needs to be implemented
    private boolean canPlay() {
        return true;
    }

    // needs to be implemented
    private boolean playSingleTurn(Player player) {
        return true;
    }


}
