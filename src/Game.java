import javax.swing.*;
import java.awt.event.*;

public class Game implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

    private static State state = State.MENU;
    public int screenwidth = 1000;
    public int screenheight = 600;
    public Timer timer;
    private JFrame screen;
    private JFrame menu;

    public Game() {
        startGame();

    }

    public void startGame() {
        screen = new JFrame();
        timer = new Timer(1000 / 60, this);
        timer.start();
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setTitle("Go Fish");
        screen.setResizable(false);
        screen.setSize(screenwidth, screenheight);
        screen.setLayout(null);
        screen.setVisible(true);
        screen.addKeyListener(this);
        screen.addMouseMotionListener(this);
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

    private static enum State {
        MENU,
        GAME,
        SETTINGS
    }
}






