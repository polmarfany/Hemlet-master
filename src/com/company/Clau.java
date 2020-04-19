package com.company;

public class Clau extends Items {

    private static final String wrenchPath = "icon/wrench.png";

    public Clau(Game game, int positionX){
        super(game, wrenchPath, positionX);
    }

    @Override
    public void effect() {
        game.restarVides(1);
        getPersonatge().clauColisio();
    }
}
