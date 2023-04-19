package P3.Model;

public class Parells {

    private Punt punt1;
    private Punt punt2;
    private double distancia;

    public Parells(Punt punt1, Punt punt2, double distancia){
        this.punt1 = punt1;
        this.punt2 = punt2;
        this.distancia = distancia;
    }

    public Punt getPunt1() {
        return punt1;
    }

    public Punt getPunt2() {
        return punt2;
    }

    public double getDistancia() {
        return distancia;
    }
}
