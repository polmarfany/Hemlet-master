package com.company;

public class Martell extends Items {

    private static final int lifeMinus = 2;
    private static final String hammerPath = "icon/hammer.png";

    public Martell(Game game, int positionX){
        super(game, hammerPath, positionX);
    }

    @Override
    public void effect() {
        game.restarVides(2);
        getPersonatge().mrStart(); //also effect of Hammer
        //TODO mostrar vides i comprobar que funcioona
    }
}
