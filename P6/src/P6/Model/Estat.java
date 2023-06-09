package P6.Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Estat {
    // Atributs d'un estat, la dimensió i la matriu.
    private int dimensioPuzzle;
    private int[][] Puzzle;

    public List<Estat> sol;

    public BufferedImage imatgepuzzle;


    // Dos constructors, donats una matriu especificada per paràmetre o donat una dimensió i el cream random.
    public Estat(int dim) {
        dimensioPuzzle = dim;
        Puzzle = generarTaulerRandomResoluble();
    }

    public Estat(int[][] taul) {
        Puzzle = taul;
        dimensioPuzzle = taul[0].length;
    }
    // ! DEBUG : BORRAR !
    public void setDefault() {
        Puzzle = new int[][]{{1,2,3,4},{5,6,7,8},{0,10,11,12},{9,13,14,15}};
    }

    // Generació d'un taulell random que sigui resoluble.
    private int[][] generarTaulerRandomResoluble() {
        // Número màxim a generar.
        int nMax = dimensioPuzzle * dimensioPuzzle;
        Random random = new Random();
        // Set de nombres que ja han estat colocats dins el puzzle.
        HashSet<Integer> numerosApareguts = new HashSet<>();

        // Cream la possible matriu.
        int[][] puzle = new int[dimensioPuzzle][dimensioPuzzle];

        // Recorrem la matriu element a element.
        for (int i = 0; i < dimensioPuzzle; i++) {
            for (int j = 0; j < dimensioPuzzle; j++) {
                // Agafam un possible número i miram si ja esta colocat.
                int numero = random.nextInt(nMax);
                // Si ha estat colocat demanam un altre numero.
                while (!numerosApareguts.add(numero)) {
                    numero = random.nextInt(nMax);
                }
                // L'assignam
                puzle[i][j] = numero;
            }
        }

        // Si no es resoluble cream un altre puzzle random
        if (!esResoluble(puzle)) {
           puzle = generarTaulerRandomResoluble();
        }

        // Si és resoluble el retornam.
        return puzle;
    }

    public boolean esResoluble(int[][] puzzle) {
        int[] arrayLineal = convertirArrayLineal(puzzle);
        int inversiones = contarInversions(arrayLineal);
        int filaVacia = obtenirFilaBuida(puzzle);
        int n = puzzle.length;

        if (n % 2 == 1) {
            // Para puzzles de tamaño impar
            return inversiones % 2 == 0;
        } else {
            // Para puzzles de tamaño par
            if ((n - filaVacia) % 2 == 0) {
                return inversiones % 2 == 1;
            } else {
                return inversiones % 2 == 0;
            }
        }
    }
    private int[] convertirArrayLineal(int[][] puzzle) {
        // Calulam la longitud de l'array.
        int n = puzzle.length * puzzle[0].length - 1;
        int[] arrayLineal = new int[n];

        // Colocam en ordre d'una matriu nxn a un array n.
        int index = 0;
        for (int i = 0; i < puzzle[0].length; i++) {
            for (int j = 0; j < puzzle[0].length; j++) {
                if(puzzle[i][j] != 0){
                    arrayLineal[index++] = puzzle[i][j];
                }
            }
        }
        return arrayLineal;
    }

    private int contarInversions(int[] arrayLineal) {
        // Inicialitzem el comptador d'inversions
        int inversions = 0;
        // Obtenim la longitud de l'array
        int n = arrayLineal.length;
        // Recorrem l'array per comptar les inversions
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                // Comprovem si l'element actual i l'element posterior formen una inversió (El 0 no compta)
                if (arrayLineal[i] != 0 && arrayLineal[j] != 0 && (arrayLineal[i] > arrayLineal[j])) {
                    inversions++;
                }
            }
        }
        // Retornem el nombre d'inversions
        return inversions;
    }

    // Retornam la fila en la qual es troba el 0.
    private int obtenirFilaBuida(int[][] puzzle) {
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle.length; j++) {
                if (puzzle[i][j] == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    // Recorrem la matriu i comparam els seus valors amb el que esparariem trobar, si algun esta mal colocat retornam false.o
    public boolean esSolucio() {
        for (int i = 0; i < dimensioPuzzle; i++) {
            for (int j = 0; j < dimensioPuzzle; j++) {
                if (i == dimensioPuzzle - 1 && j == dimensioPuzzle - 1) {
                    if (Puzzle[i][j] != 0) {
                        return false;
                    }
                } else if (Puzzle[i][j] != (i * dimensioPuzzle) + (j + 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Getters & Setters.
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
}
