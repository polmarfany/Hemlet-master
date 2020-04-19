package com.company;

public class Botiquin extends Items {

    private static final String firstAidPath = "icon/firstaid.png";

    public Botiquin(Game game, int positionX) {
        super(game, firstAidPath, positionX);
    }

    @Override
    public void effect() {
        game.sumarVides(1);
    }
}
