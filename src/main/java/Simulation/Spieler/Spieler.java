package Simulation.Spieler;
import Simulation.Spielzug.Spielzug;
import Simulation.Spielzug.SpielzugRandom;
import Simulation.Tisch.Tisch;

public class Spieler extends Thread{

    private int spielerID;
    private Tisch tisch;
    private final SpielzugRandom random = new SpielzugRandom();

    public Spieler(int spielerID, Tisch tisch){
        this.spielerID = spielerID;
        this.tisch = tisch;
    }

    @Override
    public void run() {

        try {
            while (!Thread.currentThread().isInterrupted()) {
                Spielzug zug = random.getSpielzug();
                tisch.spielen(spielerID, zug);

                synchronized(tisch) {
                    tisch.wait();
                }
            }
        }catch(InterruptedException ex){
          ex.printStackTrace();
        }
    }
}
