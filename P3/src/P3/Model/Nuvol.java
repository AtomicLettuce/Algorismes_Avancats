package P3.Model;

import java.util.Random;

public class Nuvol {

    private Punt [] nuvol;
    private Parells[] parells;
    private final Random rand = new Random();

    private int max;

    private int dimensio;
    public Nuvol(int n, int max){
        nuvol = new Punt[n];
        this.max = max;
        this.dimensio = n;
        this.parells = new Parells[3];
    }
    public void generarNuvol(){
        double[] aux = new double[2];
        for(int i = 0; i<dimensio; i++){
            for(int y = 0; y<2; y++){
                 aux[y]= rand.nextDouble() * 1000;

            }
            nuvol[i] = new Punt(aux.clone());
        }
    }

    public void setNuvol(Punt[] nuvol) {
        this.nuvol = nuvol;
    }

    public Parells[] getParells() {
        return parells;
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
