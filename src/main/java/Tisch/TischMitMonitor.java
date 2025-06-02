package Tisch;

import Spielzug.Spielzug;


public class TischMitMonitor implements Tisch {

    private int runden;
    private int unentschieden = 0;
    private int siegSpieler1 = 0;
    private int siegSpieler2 = 0;
    Spielzug[] spielzugs = new Spielzug[2];

    public TischMitMonitor(int runden){
        this.runden = runden;
    }


    @Override
    public synchronized void spielen(int spielerId, Spielzug zug) throws InterruptedException {

        while(spielzugs[spielerId] != null) {
            wait();
        }
        spielzugs[spielerId] = zug;
        System.err.println(spielerId + " setzt: " + zug.name());
        notifyAll();
    }

    @Override
    public synchronized void bewertung() throws InterruptedException {
       if(spielzugs[0] != null || spielzugs[1] != null){
           wait();
       }
       runden++;


           int ergebnis = spielzugs[0].vergleiche(spielzugs[1]);
           if(ergebnis == 0){
               unentschieden++;
               System.err.println("Runde: " + runden + " unentschieden.");
           }

           if(ergebnis < 0){
               siegSpieler2++;
               System.err.println("Runde: " + runden + " Spieler 2 gewinnt.");
           }

           if(ergebnis > 0){
               siegSpieler1++;
               System.err.println("Runde: " + runden + " Spieler 1 gewinnt.");
           }

       for(int i = 0; i < 2;i++){
           spielzugs[i] = null;
       }
       notifyAll();

    }

    public void ergebnisseAnsehen(){
        System.err.println("***** ERGEBNISSE *****");
        System.err.println("Unentschieden: " + unentschieden);
        System.err.println("Sieg von Spieler 1: " + siegSpieler1);
        System.err.println("Sieg von Spieler 2: " +siegSpieler2);
    }

    public Spielzug[] getSpielzugs() {
        return spielzugs;
    }

    @Override
    public synchronized boolean beideSpielzuegeVorhanden() {
        return spielzugs[0] != null && spielzugs[1] != null;
    }
}
