package P2.Model;

import java.util.ArrayList;

public class Tauler {

    private int dim;
    private Moviment tauler[][]; //Matriu de moviments
    private int intents = 0; //Usat per contar cada quant pintar
    ArrayList<Peca> peces = new ArrayList<>();

    public Tauler(int n){//Constructor
        dim = n;
        tauler = new Moviment[n][n];

        //Cream la matriu amb moviments invalids
        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[0].length; j++) {
                tauler[i][j] = new Moviment(0,-1);
            }
        }
    }

    //Funció per afegir una peća
    public void afegirPeca(Peca peca){
        peces.add(peca);
    }

    //Marca una casella com a ja trepitjada
    public void activarCasella(int x, int y, int count, int torn){
        tauler[y][x].setnMoviment(count);
        tauler[y][x].setTorn(torn);
        intents++;
    }

    //Desmarca la casella
    public void desactivarCasella(int x, int y){
        tauler[y][x].setnMoviment(0);
        tauler[y][x].setTorn(-1);
    }

    //Per poder consultar la matriu
    public Moviment getMoviment(int x, int y){
        return tauler[y][x];
    }

    //Comprova si un moviment és vàlid
    public boolean estaLliure(int x,int y){

            if ((y >= 0 && y < tauler.length) && (x >= 0 && x < tauler.length) &&(tauler[y][x].getnMoviment() <= 0)){
                return true;
            }
        return false;
    }


    public int getDim(){
        return dim;
    }

    public void setDim(int n){
        dim=n;
        tauler = new Moviment[n][n];
        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[0].length; j++) {
                tauler[i][j] = new Moviment(0,-1);
            }
        }
        peces = new ArrayList<>();
    }


    public int getIntents() {
        return intents;
    }

    public ArrayList<Peca> getPeces(){
        return peces;
    }


}
