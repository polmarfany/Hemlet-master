package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class CreadorItems implements Runnable {

    //milliseconds, TIME BETWEEN ITEM CREATED
    public boolean gameRunning = true;
    public static Game game;
    private static ArrayList<Integer> columnArrayList = new ArrayList<>(Arrays.asList(100, 200, 300, 400, 500)); //valors de diferents columnes, de moment hardcodejats

    public CreadorItems(Game game) {
        this.game = game;
    }

    public void run(){
        while (gameRunning) {
            creator();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void creator() {
        Items nouItem = null;
        int posicioXnouItem = 100 + 100 * (int) (Math.random() * 5) + 1;
        int itemCrear = (int) (Math.random() * 5) + 1;

        switch (itemCrear) {
            case 1:
                nouItem = new Clau(game, posicioXnouItem);
                break;
            case 2:
                nouItem = new Martell(game, posicioXnouItem);
                break;
            case 3:
                nouItem = new Tornavis(game, posicioXnouItem);
                break;
            case 4:
                if ((int) (Math.random() * 2) + 1 == 1) {
                    nouItem = new Escut(game, posicioXnouItem);
                } else {
                    nouItem = new Botiquin(game, posicioXnouItem);
                }

        }
        game.items.add(nouItem);
        new Thread(nouItem).start();
    }
}