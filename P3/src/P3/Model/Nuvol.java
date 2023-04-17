package P3.Model;

import java.util.Random;

public class Nuvol {

    private double [][] nuvol;
    private final Random rand = new Random(77);

    private int max;

    private int dimensio;
    public Nuvol(int n, int max){
        nuvol = new double[n][n];
        this.max = max;
        this.dimensio = n;
    }
    public void generarNuvol(){

        for(int i = 0; i<dimensio; i++){
            for(int y = 0; y<2; y++){
                nuvol[i][y] = rand.nextInt(max);
            }
        }
    }

    public double[][] getNuvol(){return nuvol;}
}
