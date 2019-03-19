import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GameGui implements KeyListener, MouseMotionListener, ActionListener, MouseListener {
    public static JFrame screen;
    public static FlowLayout experimentLayout;
    public static JPanel panel;
    public static JPanel panel2;
    public static boolean cardSelected = false;
    static int turnNumber = 0;
    static JLabel turnText = new JLabel("Player 1 turn");
    static HashMap<String, ArrayList<Card>> p1hand = new HashMap<>();
    static HashMap<String, ArrayList<Card>> p2hand = new HashMap<>();
    static GameGui game;
    private static Deck deck;
    private static Border highlight;

    public GameGui() {
        experimentLayout = new FlowLayout();
        screen = new JFrame();
        panel = new JPanel();
        panel2 = new JPanel();
        screen.setLayout(experimentLayout);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setTitle("Go Fish");
        screen.setResizable(false);
        screen.setSize(1300, 1000);
        //panel.setSize(500, 300);


        screen.addKeyListener(this);
        screen.addMouseMotionListener(this);


    }

    public static void main(String[] args) {
        highlight = BorderFactory.createLineBorder(Color.YELLOW, 3);
        System.out.println("hi");

        game = new GameGui();
        System.out.println("hi");
        deck = new Deck();
        deck.Initiate();
        deck.shuffle();
        deck.deal(p1hand, p2hand, "gofish");


        screen.add(turnText);
        panel.setPreferredSize(new Dimension(1200, 400));
        panel2.setPreferredSize(new Dimension(1200, 400));
        panel.setVisible(true);
        panel2.setVisible(true);
        screen.add(panel);
        screen.add(panel2);
        screen.setVisible(true);
        drawGame();


        System.out.println();
        for (Map.Entry<String, ArrayList<Card>> entry : p2hand.entrySet()) {
            for (Card card : entry.getValue()) {

                //System.out.println(entry.getKey() + "/" + entry.getValue());
            }
        }

        //Turn(p1hand, p2hand);
    }

    public static void Turn(HashMap<String, ArrayList<Card>> a, HashMap<String, ArrayList<Card>> b) {
        while (true) {
            System.out.println("P1 Please select a card");
            Card card = new Card(null, null); //Done after multiplayer interface
            if (b.containsKey(card.getRank())) {
                ArrayList<Card> temp = b.get(card.getRank());
                b.remove(card.getRank());
                a.put(card.getRank(), temp);
            } else {
                deck.dealOne(a);
                break;
            }
        }

        while (true) {
            System.out.print("P2 Please select a card");
            Card card = new Card(null, null); //Done after multiplayer interface
            if (a.containsKey(card.getRank())) {
                ArrayList<Card> temp = a.get(card.getRank());
                a.remove(card.getRank());
                b.put(card.getRank(), temp);
            } else {
                deck.dealOne(b);
                break;
            }
        }
    }

    public static void drawGame() {
        indicateTurn(turnNumber);
        for (Map.Entry<String, ArrayList<Card>> entry : p1hand.entrySet()) {
            //System.out.println(entry.getKey() + "/" + entry.getValue());
            for (Card card : entry.getValue()) {
                System.out.println(card.getRank() + card.getSuit());
                JLabel cardimg = new JLabel(card.getImage());
                cardimg.setToolTipText(card.toString());
                cardimg.addMouseListener(game);
                panel.add(cardimg);


            }

        }

        for (Map.Entry<String, ArrayList<Card>> entry : p2hand.entrySet()) {
            //System.out.println(entry.getKey() + "/" + entry.getValue());
            for (Card card : entry.getValue()) {
                System.out.println(card.getRank() + card.getSuit());
                JLabel cardimg = new JLabel(card.getImage());
                cardimg.addMouseListener(game);
                cardimg.setToolTipText(card.toString());
                panel2.add(cardimg);


            }

        }
        screen.repaint();
        screen.validate();
    }

    public static void indicateTurn(int turn) {
        if (turn == 0)
            turnText.setText("Your Turn!");
        else
            turnText.setText("Player " + turn + "'s Turn!");
    }

    public ArrayList<Card> isFourMade(HashMap<String, ArrayList<Card>> a) {
        for (Map.Entry<String, ArrayList<Card>> entry : a.entrySet()) {
            if (entry.getValue().size() == 4) {
                return entry.getValue();
            }
        }
        return null;
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {
        JLabel temp = (JLabel) e.getSource();

        if (!cardSelected && temp.getY() != 5) {
            temp.setBounds(temp.getX(), temp.getY() - 25, temp.getWidth(), temp.getHeight());
            cardSelected = true;
        } else {
            if (temp.getY() == 5) {
                temp.setBounds(temp.getX(), temp.getY() + 25, temp.getWidth(), temp.getHeight());
                cardSelected = false;
            }


        }
        //isFourMade(p1hand, p2hand);
    }

    public void mouseEntered(MouseEvent e) {
        JLabel temp = (JLabel) e.getSource();
        temp.setBorder(highlight);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel temp = (JLabel) e.getSource();
        temp.setBorder(null);
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
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public class Player {

    }
}