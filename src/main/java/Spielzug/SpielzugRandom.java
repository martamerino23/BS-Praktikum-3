package Spielzug;

import java.util.Random;

public class SpielzugRandom {

    private Random random = new Random();

    public Spielzug getSpielzug(){
        int r = random.nextInt(3);
        switch(r){

            case 0: return new Stein();
            case 1: return new Schere();
            case 2: return new Papier();
            default: return new Stein();
        }
    }
}
