package Spielzug;

public class Schere implements Spielzug{

    private String name;

    public Schere(){
        name = "Schere";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int vergleiche(Spielzug anderer) {
        if(anderer instanceof Papier){
            return 1; //gewinnt
        }else if(anderer instanceof Stein){
            return -1; //verliert
        }else{
            return 0; //Schere
        }
    }

    @Override
    public String name() {
        return name;
    }
}