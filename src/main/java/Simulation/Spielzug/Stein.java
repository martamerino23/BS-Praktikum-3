package Simulation.Spielzug;

public class Stein implements Spielzug {

    private String name;

    public Stein() {

        name = name;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int vergleiche(Spielzug anderer) {
        if(anderer instanceof Schere){
            return 1; //gewinnt
        }else if(anderer instanceof Papier){
            return -1; //verliert
        }else{
            return 0; //Stein
        }
    }

}