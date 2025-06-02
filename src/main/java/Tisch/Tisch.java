package Tisch;

import Spielzug.*;

public interface Tisch {

    public void spielen(int spielerId, Spielzug zug) throws InterruptedException;
    public void bewertung() throws InterruptedException;
    public boolean beideSpielzuegeVorhanden();
}