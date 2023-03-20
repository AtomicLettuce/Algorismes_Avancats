package P2.Model;

import java.util.ArrayList;

public class Tauler {

    private int dim = 6;
    private Moviment tauler[][];
    private int intents = 0;
    ArrayList<Peca> peces = new ArrayList<>();

    public Tauler(int n){
        dim = n;
        tauler = new Moviment[n][n];
        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[0].length; j++) {
                tauler[i][j] = new Moviment(0,-1);
            }
        }
    }

    public void afegirPeca(Peca peca){
        peces.add(peca);
    }

    public void activarCasella(int x, int y, int count, int torn){
        tauler[y][x].setnMoviment(count);
        tauler[y][x].setTorn(torn);
        intents++;
    }

    public void desactivarCasella(int x, int y){
        tauler[y][x].setnMoviment(0);
        tauler[y][x].setTorn(-1);
    }

    public boolean estaLliure(int x,int y){
        try {
            if (tauler[y][x].getnMoviment() <= 0){
                return true;
            }
        }catch (ArrayIndexOutOfBoundsException e){
            return false;
        }

        return false;
    }



    @Override
    public String toString(){
        String res = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n";

        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[0].length; j++) {
                if (tauler[j][i].getnMoviment() == 0){
                    res += " 0 ";
                }else if (tauler[j][i].getnMoviment() != 0){
                    res += " " + tauler[j][i].getnMoviment()+" ";
                }
            }
            res += "\n";
        }
        res += "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        return res;
    }



    public int getDim(){
        return dim;
    }

    public void setDim(int n){
        dim=n;
    }

    public int getIntents() {
        return intents;
    }

    public ArrayList<Peca> getPeces(){
        return peces;
    }


}
