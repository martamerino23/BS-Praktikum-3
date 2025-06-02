package Spielzug;

public class Papier implements Spielzug {

    private String name;

    public Papier() {
        name = "Papier";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int vergleiche(Spielzug anderer) {
      if(anderer instanceof Stein){
          return 1; //gewinnt
      }else if(anderer instanceof Schere){
          return -1; //verliert
      }else{
          return 0; //Papier
      }
    }

    @Override
    public String name() {
        return name;
    }
}