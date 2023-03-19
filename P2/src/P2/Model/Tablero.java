package P2.Model;

public class Tablero {

    private int dim;
    private int tablero[][];

    public Tablero(int n){
        dim = n;
        tablero = new int[n][n];

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = 0;
            }
        }
    }

    public void activarCasella(int x, int y, int count){
        tablero[y][x] = count;
    }

    public void desactivarCasella(int x, int y){
        tablero[y][x] = 0;
    }

    public boolean estaLliure(int x,int y){
        try {
            if (tablero[y][x] <= 0){
                return true;
            }
        }catch (ArrayIndexOutOfBoundsException e){
            return false;
        }

        return false;
    }

    public int getCasella(int x, int y){

        return tablero[y][x];
    }

    @Override
    public String toString(){
        String res = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n";

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[j][i] == 0){
                    res += " 0 ";
                }else if (tablero[j][i] != 0){
                    res += " " +tablero[j][i]+" ";
                }
            }
            res += "\n";
        }
        res += "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        return res;
    }


    public int[][] getTablero() {
        return tablero;
    }
    public int getDim(){
        return dim;
    }
}
