package P3.Model;

public class Parells {

    private double[] punt1;
    private double[] punt2;
    private double distancia;

    public Parells(double[] punt1, double[] punt2, double distancia){
        this.punt1 = punt1;
        this.punt2 = punt2;
        this.distancia = distancia;
    }

    public double[] getPunt1() {
        return punt1;
    }

    public double[] getPunt2() {
        return punt2;
    }

    public double getDistancia() {
        return distancia;
    }
}
