import Tisch.*;

public class Schiedsrichter extends Thread{

    private String name;
    private Tisch tisch;

    public Schiedsrichter(String name, Tisch tisch){
        this.name = name;
        this.tisch = tisch;
    }

    @Override
    public void run() {
        try{
            while(!Thread.currentThread().isInterrupted()){
                synchronized (tisch) {
                    while (!tisch.beideSpielzuegeVorhanden()) {
                        tisch.wait();
                    }
                    tisch.bewertung();
                    tisch.notifyAll();
                }
            }
        }catch(InterruptedException ex){

        }
    }
}
