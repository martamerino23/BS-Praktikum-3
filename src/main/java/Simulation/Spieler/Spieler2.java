package Simulation.Spieler;

import Simulation.Spielzug.Spielzug;
import Simulation.Spielzug.SpielzugRandom;
import Simulation.Tisch.Tisch2;

public class Spieler2 extends Thread{

    private int spielerID;
    private Tisch2 tisch;
    private final SpielzugRandom random = new SpielzugRandom();

    public Spieler2(int spielerID, Tisch2 tisch){
        this.spielerID = spielerID;
        this.tisch = tisch;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Spielzug zug = random.getSpielzug();
                tisch.spielen(spielerID, zug);
            }
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
