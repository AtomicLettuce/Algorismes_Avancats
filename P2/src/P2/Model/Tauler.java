package P2.Model;

public class Tauler {

    private int dim;
    private int tauler[][];

    public Tauler(int n){
        dim = n;
        tauler = new int[n][n];

        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[0].length; j++) {
                tauler[i][j] = 0;
            }
        }
    }

    public void activarCasella(int x, int y, int count){
        tauler[y][x] = count;
    }

    public void desactivarCasella(int x, int y){
        tauler[y][x] = 0;
    }

    public boolean estaLliure(int x,int y){
        try {
            if (tauler[y][x] <= 0){
                return true;
            }
        }catch (ArrayIndexOutOfBoundsException e){
            return false;
        }

        return false;
    }

    public int getCasella(int x, int y){

        return tauler[y][x];
    }

    @Override
    public String toString(){
        String res = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n";

        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[0].length; j++) {
                if (tauler[j][i] == 0){
                    res += " 0 ";
                }else if (tauler[j][i] != 0){
                    res += " " + tauler[j][i]+" ";
                }
            }
            res += "\n";
        }
        res += "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        return res;
    }


    public int[][] getTauler() {
        return tauler;
    }
    public int getDim(){
        return dim;
    }

    public void setDim(int n){
        dim=n;
    }
}
