package P2.Model;

public class Moviment {

    //Objecte usat per guardar la informació del tauler.
    //Cada moviment enregistra qui el fa i quin nombre de moviement és
    private int nMoviment;
    private  int torn;

    public Moviment(int nMoviment, int torn){
        this.torn = torn;
        this.nMoviment = nMoviment;
    }

    public void setnMoviment(int nMoviment) {
        this.nMoviment = nMoviment;
    }

    public int getnMoviment() {
        return nMoviment;
    }

    public void setTorn(int torn) {
        this.torn = torn;
    }

    public int getTorn() {
        return torn;
    }
}
