import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;


class GUI extends JFrame implements KeyListener, MouseMotionListener, ActionListener, MouseListener {

    static GUI gui;
    private static Border highlight;
    GamePanel gamePanel;
    DeckPanel deckPanel;


    private JLabel statusBar;

    private void drawGame() {
        drawHand();
        repaint();
        revalidate();
    }

    private void drawHand() {

    }

    void startGame() {
        init();
        initComponents();
        drawGame();
        revalidate();
    }

    void run() {
        Game game = new Game(this);
        game.start();
    }

    private void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Go Fish");
        this.setResizable(false);
        this.setSize(1300, 1000);
        this.addKeyListener(this);
        this.addMouseListener(this);
    }

    public void setMessage(String text) {
        statusBar.setForeground(null);
        statusBar.setText(text);
    }

    public void setErrorMessage(String text) {
        statusBar.setForeground(Color.RED);
        statusBar.setText(text);
    }

    private void initComponents() {

        highlight = BorderFactory.createLineBorder(Color.YELLOW, 3);

        deckPanel = new DeckPanel();
        deckPanel.initComponents();
        Player player1 = new Player(deckPanel.getDeck());
        Player player2 = new Player(deckPanel.getDeck());
        gamePanel = new GamePanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
        gamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        CardsPanel panel1 = gamePanel.addPlayer(player1);
        CardsPanel panel2 = gamePanel.addPlayer(player2);
        //gamePanel.current.addCard(new CardLabel(new Card("Diamonds", "King"), true), 5);
        gamePanel.playerPanels.get(player2).revalidate();
        gamePanel.playerPanels.get(player2).repaint();
        gamePanel.playerPanels.get(player1).revalidate();
        gamePanel.revalidate();
        gamePanel.setVisible(true);
        add(gamePanel);
        setVisible(true);
        revalidate();
    }

    public void playerTurn(Player player) {
        gamePanel.setCurrentPlayer(player);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
