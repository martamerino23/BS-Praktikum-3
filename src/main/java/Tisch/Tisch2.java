package Tisch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import Spielzug.Spielzug;


public class Tisch2 implements Tisch{
    private final Lock lock = new ReentrantLock();
    private final Condition zugGelegt = lock.newCondition();
    private final Condition bewertet = lock.newCondition();

    private Spielzug[] spielzuege = new Spielzug[2];
    private boolean rundeBewertet = true;

    private int runden = 0;
    private int unentschieden = 0;
    private int siegSpieler1 = 0;
    private int siegSpieler2 = 0;

    @Override
    public void spielen(int spielerId, Spielzug zug) throws InterruptedException {
        lock.lock();
        try {
            while (!rundeBewertet) {
                bewertet.await();
            }

            while (spielzuege[spielerId] != null) {
                bewertet.await();
            }

            spielzuege[spielerId] = zug;
            System.out.println("Spieler " + (spielerId +1) + " legt: " + zug);


            if (spielzuege[0] != null && spielzuege[1] != null) {
                rundeBewertet = false;
                zugGelegt.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void bewertung() throws InterruptedException {
        lock.lock();
        try {
            while (spielzuege[0] == null || spielzuege[1] == null) {
                zugGelegt.await();
            }

            runden++;

            int ergebnis = spielzuege[0].vergleiche(spielzuege[1]);

            if (ergebnis == 0) {
                unentschieden++;
                System.out.println("Runde " + runden + ": Unentschieden.");
            } else if (ergebnis > 0) {
                siegSpieler1++;
                System.out.println("Runde " + runden + ": Spieler 1 gewinnt.");
            } else {
                siegSpieler2++;
                System.out.println("Runde " + runden + ": Spieler 2 gewinnt.");
            }

            spielzuege[0] = null;
            spielzuege[1] = null;

            rundeBewertet = true;

            bewertet.signalAll();

        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean beideSpielzuegeVorhanden() {
        lock.lock();
        try {
            return spielzuege[0] != null && spielzuege[1] != null;
        } finally {
            lock.unlock();
        }
    }

    public void ergebnisseAnsehen() {
        System.out.println("***** ERGEBNISSE *****");
        System.out.println("Gesamt-Runden: " + runden);
        System.out.println("Unentschieden: " + unentschieden);
        System.out.println("Sieg Spieler 1: " + siegSpieler1);
        System.out.println("Sieg Spieler 2: " + siegSpieler2);
    }
}
