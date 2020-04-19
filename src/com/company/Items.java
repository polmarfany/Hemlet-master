package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Items implements Runnable {

    //game boundaries handling
    private static final int MAXIMITEMS = 500;
    private static final int MOVIMENT = 100;
    private static int TEMPS_CAIGUDA = 1000; //ms

    //class atributes
    private BufferedImage icon;
    private int positionX;
    private int positionY = 0;
    private boolean escut = false;
    public Game game;

    public Items(Game game, String path, int positionX) {
        this.game = game;
        this.icon = Icon.resize(path);
        this.positionX = positionX;
    }

    public abstract void effect();

    public void run() {
        while (this.positionY < MAXIMITEMS) {
            this.positionY = this.positionY + MOVIMENT;
            try {
                Thread.sleep(TEMPS_CAIGUDA);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        game.items.remove(this);
        if (!escut && getPersonatge().getPositionX() == this.positionX ) {
            this.effect();
        }
    }

    public Personatge getPersonatge(){
        return this.game.getPersonatge();
    }

    public static void disminuirMoveTime() {
        TEMPS_CAIGUDA = TEMPS_CAIGUDA - 50;
    }

    public void paint(Graphics2D g) {
        g.drawImage(icon, positionX, positionY, null);
    }
}
