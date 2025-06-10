package Simulation;

import Simulation.Schiedsrichter.Schiedsrichter2;
import Simulation.Spieler.Spieler2;
import Simulation.Tisch.Tisch2;
import java.util.ArrayList;
import java.util.List;

public class Simulation2 {

    private static final int ANZAHLSPIELER = 2;
    private static List<Spieler2> spielerListe = new ArrayList<>();

    public static void main(String[] args) {

        Tisch2 tisch = new Tisch2();
        Schiedsrichter2 richter = new Schiedsrichter2(tisch);

        for (int i = 0; i < ANZAHLSPIELER; i++) {
            spielerListe.add(new Spieler2(i, tisch));
        }


        for (Spieler2 spieler : spielerListe) {
            spieler.start();
        }

        richter.start();

        try {
            Thread.sleep(120_000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }


        for (Spieler2 spieler : spielerListe) {
            spieler.interrupt();
        }
        richter.interrupt();


        try {
            for (Spieler2 spieler : spielerListe) {
                spieler.join();
            }
            richter.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }


        tisch.ergebnisseAnsehen();
    }
}


