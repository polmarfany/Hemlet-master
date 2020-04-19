package com.company;

public class Tornavis extends Items{

    private static final String screwdriverPath = "icon/screwdriver.png";
    //TODO fer el path cutre

    public Tornavis(Game game, int positionX){
        super(game, screwdriverPath, positionX);
    }

    @Override
    public void effect() {
        game.restarVides(1);
        getPersonatge().tornavisColisio();
    }
}
