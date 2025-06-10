package Simulation;

import Simulation.Schiedsrichter.Schiedsrichter;
import Simulation.Spieler.Spieler;
import Simulation.Tisch.TischMitMonitor;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private static final int ANZAHLRUNDE = 0;
    private static final int ANZAHLSPIELER = 2;
    private static List<Spieler> spielern = new ArrayList<>();

    public static void main(String[] args) {

        TischMitMonitor tisch = new TischMitMonitor(ANZAHLRUNDE);
        Schiedsrichter richter = new Schiedsrichter("Rick",tisch);

        for(int i = 0; i < ANZAHLSPIELER; i++ ){
            spielern.add(new Spieler(i,tisch));
        }

        for(Spieler spieler : spielern){
            spieler.start();
        }

        richter.start();

        try{
            Thread.sleep(120_000);
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }



        for(Spieler spieler : spielern){
            spieler.interrupt();
        }

        richter.interrupt();


        try {
            for (Spieler spieler : spielern) {
                spieler.join();
            }
            richter.join();
        }catch(InterruptedException ex){

        }

        tisch.ergebnisseAnsehen();
    }
}
