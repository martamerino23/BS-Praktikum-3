package Simulation.Schiedsrichter;

import Simulation.Tisch.Tisch2;

public class Schiedsrichter2 extends Thread {

    private final Tisch2 tisch;

    public Schiedsrichter2(Tisch2 tisch) {
        this.tisch = tisch;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                tisch.bewertung();
            }
        } catch (InterruptedException e) {

        }
    }
}


