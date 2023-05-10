package P3.Model;

import java.util.Random;

public class Nuvol {

    private Punt [] nuvol;
    private Parells[] parells;
    private final Random rand = new Random();
    private int distribucio;
    private int max;
    private int dimensio;

    public Nuvol(int n, int max) {
        nuvol = new Punt[n];
        this.max = max;
        this.dimensio = n;
        distribucio = 0;
        this.parells = new Parells[3];
    }

    public void generarNuvol() {
        switch (distribucio) {
            // Cas equiprobable
            case 0:
                for (int i = 0; i < dimensio; i++) {
                    double[] aux = new double[2];
                    for (int y = 0; y < 2; y++) {
                        aux[y] = rand.nextDouble() + rand.nextInt(max);
                    }
                    nuvol[i] = new Punt(aux);
                }
                break;
            // Cas gausiana
            case 1:
                for (int i = 0; i < dimensio; i++) {
                    double[] aux = new double[2];
                    for (int y = 0; y < 2; y++) {
                        aux[y] = rand.nextGaussian();
                    }
                    nuvol[i] = new Punt(aux);
                }
                break;
        }
    }

    public void setDimensio(int dimensio) {
        this.dimensio = dimensio;
        nuvol = new Punt[dimensio];
    }

    public void setNuvol(Punt[] nuvol) {
        this.nuvol = nuvol;
    }

    public Parells[] getParells() {
        return parells;
    }


    public int getDistribucio() {
        return distribucio;
    }
    public void setDistribucio(int d){
        distribucio=d;
    }

    public int getMax() {
        return max;
    }

    public int getDimensio() {
        return dimensio;
    }

    public void setParells(Parells[] parells) {
        this.parells = parells;
    }

    public Punt [] getNuvol(){return nuvol;}
}
