package P1.model;

import P1.Main;
import P1.interfaces.InterficieComunicacio;

import java.util.Random;

public class Model implements InterficieComunicacio {

    // Punter al main.
    Main main;

    // Matriu que guardarà els temps que tarda cada algorisme
    // Cada fila comptindrà els temps de cada algorisme
    private Float matriu_temps[][];
    public static final int[] NITERACIONS = {10000,20000,30000,40000,50000,60000,70000,80000,90000,100000};

    // Random per a crear l'array per a calcular la moda, inicialitzat amb una llavor
    // per a que cada vegada que s'executi posi els mateixos números.
    private final Random rand = new Random(42);
    // Nombre màxim que generà el random.
    private static final int MAXIM_VALUE = 100000;

    // Matriu que guarda les dades. Cada fila de la matriu correspon a un vector
    private int[][] vectors;

    public Model(Main main) {
        this.main = main;
    }

    // Generam amb una longitud fixada pel paràmetre d'entrada.
    public void generarVectors(int dim, int qt) {
        vectors = new int[qt][dim];

        // Omplim matriu de dades
        for (int i = 0; i < dim; i++) {
            int number= rand.nextInt(MAXIM_VALUE);
            for(int j=0;j<qt;j++){
                vectors[j][i]=number;
            }
        }
    }

    // Getter necesari per la classe 'Controlador.Controlador'.
    public int[][] getVectors() {
        return vectors;
    }

    public void inicialitzar_temps(int rows, int columns){
        matriu_temps = new Float[rows][columns];
        for (int i=0;i<rows;i++){
            for (int j =0;j<columns;j++){
                matriu_temps[i][j]=null;
            }
        }
    }

    public void setTemps(int row, int colum, Float nValor) {
        matriu_temps[row][colum] = nValor;
    }

    public Float[][] getMatriu_temps(){
        return matriu_temps;
    }

    @Override
    public void comunicacio(String instruccio) {

    }
}