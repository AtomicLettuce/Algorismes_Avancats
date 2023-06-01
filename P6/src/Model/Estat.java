package Model;

import java.util.HashSet;
import java.util.Random;

public class Estat {
    private int dimensioPuzzle = 2;
    private int[][] Puzzle;
    public int getDimensioPuzzle() {
        return dimensioPuzzle;
    }

    public int[][] getPuzzle() {
        return Puzzle;
    }

    public int getPosicio(int i, int j)  {
        return this.Puzzle[i][j];
    }

    public void setPuzzle(int[][] puzzle) {
        Puzzle = puzzle;
    }

    public Estat(int dim) {
        dimensioPuzzle = dim;
        Puzzle = generarTaulerRandomResoluble();
    }

    public void setDefault() {
        Puzzle = new int[][]{{7,10,3,5},{12,15,0,8},{6,14,2,1},{4,11,9,13}};
    }

    public Estat(int[][] taul) {
        Puzzle = taul;
        dimensioPuzzle = taul[0].length;
    }

    private int[][] generarTaulerRandomResoluble() {
        int nMax = dimensioPuzzle * dimensioPuzzle;
        HashSet<Integer> numerosApareguts = new HashSet<>();
        Random random = new Random();

        int[][] puzle = new int[dimensioPuzzle][dimensioPuzzle];

        for (int i = 0; i < dimensioPuzzle; i++) {
            for (int j = 0; j < dimensioPuzzle; j++) {
                int numero = random.nextInt(nMax);
                while (!numerosApareguts.add(numero)) {
                    numero = random.nextInt(nMax);
                }
                puzle[i][j] = numero;
            }
        }

        // Verificar si el tablero generado es resoluble
        if (!esResoluble(puzle)) {
           puzle = generarTaulerRandomResoluble();
        }

        return puzle;
    }

    private int[] convertirArrayLineal(int[][] puzzle) {
        int n = puzzle.length * puzzle[0].length;
        int[] arrayLineal = new int[n];
        int index = 0;
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[0].length; j++) {
                arrayLineal[index++] = puzzle[i][j];
            }
        }
        return arrayLineal;
    }

    private int contarInversiones(int[] arrayLineal) {
        int inversiones = 0;
        int n = arrayLineal.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arrayLineal[i] != 0 && arrayLineal[j] != 0 && arrayLineal[i] > arrayLineal[j]) {
                    inversiones++;
                }
            }
        }
        return inversiones;
    }

    public boolean esResoluble(int[][] puzzle) {
        int[] arrayLineal = convertirArrayLineal(puzzle);
        int inversiones = contarInversiones(arrayLineal);
        int blankRow = getBlankRow(puzzle);

        if (puzzle.length % 2 != 0) {
            // For odd-sized puzzles
            return inversiones % 2 == 0;
        } else {
            // For even-sized puzzles
            if (blankRow % 2 == 0) {
                return inversiones % 2 != 0;
            } else {
                return inversiones % 2 == 0;
            }
        }
    }

    private int getBlankRow(int[][] puzzle) {
        int lastRow = puzzle.length - 1;
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle.length; j++) {
                if (puzzle[i][j] == 0) {
                    return lastRow - i;
                }
            }
        }
        return -1; // Blank not found
    }


    private void intercambiarFichasValidas(int[][] puzle) {
        // Realizar intercambio de fichas válidas, por ejemplo, intercambiar la última ficha con la penúltima
        int temp = puzle[dimensioPuzzle - 1][dimensioPuzzle - 1];
        puzle[dimensioPuzzle - 1][dimensioPuzzle - 1] = puzle[dimensioPuzzle - 1][dimensioPuzzle - 2];
        puzle[dimensioPuzzle - 1][dimensioPuzzle - 2] = temp;
        // Puedes aplicar otras series de movimientos válidos para hacer el tablero resoluble
    }


    public boolean esSolucio() {
        boolean sol = true;

        for (int i = 0; i < dimensioPuzzle; i++) {
            for (int j = 0; j < dimensioPuzzle; j++) {
                if (i == dimensioPuzzle - 1 && j == dimensioPuzzle - 1) {
                    if (Puzzle[i][j] != 0) {
                        sol = false;
                        break;
                    }
                } else if (Puzzle[i][j] != (i * dimensioPuzzle) + (j + 1)) {
                    sol = false;
                    break;
                }
            }
        }

        return sol;
    }




}
