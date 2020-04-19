package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

public class Game extends JPanel {

    private final Personatge personatge = new Personatge(this);

    private Image backgroundImage = Icon.getIcon("icon/fondo.jpg");

    public ArrayList<Items> items = new ArrayList<>(); //TODO TR%RRRRRRRRRR
    private static int punts = 0;
    private int vides = 3;
    public static boolean joc = false;
    public boolean teclat = true;
    private static int SLEEP = 1000;

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        JFrame frame = new JFrame("Helmet_Game");

        frame.add(game);
        frame.setSize(850, 850);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
        creadorItems = new Thread(() -> {
            while (joc) {
                try {
                    crearNouItem(main);
                    Thread.sleep(SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        creadorItems.start();
         */


        new Thread(new CreadorItems(game) ).start();

        while (joc) {
            game.repaint();
            Thread.sleep(50);
        }


    }

    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if (teclat) {
                    personatge.keyPressed(e);
                    teclat = false;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                teclat = true;
            }
        });
        setFocusable(true);
    }

    public Personatge getPersonatge() { //per a poder handlejar els efectes dels items directament des de les seves classes
        return personatge;
    }

    public void sumarVides(int vides) {
        vides = vides - vides;
    }

    public void restarVides(int vides) {
        vides = vides + vides;
        if (vides == 0) {
            joc = false;
            gameOver();
        }
    }

    public void sumarPunts() {
        punts = punts + 5;
        if (punts % 25 == 0) { //cada 25 punts, s'augmenta la dificultat, tant per HOLD TIME (creacio items) com per MOVE TIME (caiguda items)
            Items.disminuirMoveTime();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(backgroundImage,0, 0, this.getWidth(), this.getHeight(), null);
        g.drawString(String.valueOf(punts), 200, 20);
        g.drawString(String.valueOf(vides), 250, 20);
        personatge.paint(g2d);

        for (Items item : items) { //pintem els ITEMS
            item.paint(g2d);
        }
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }
}