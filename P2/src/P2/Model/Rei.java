package P2.Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Rei extends Peca{


    public Rei(int x, int y)  {
        super(x, y);
        try{
            img= ImageIO.read(new File("img/rei.png"));
        }catch (IOException ioe){
            System.out.println(ioe.toString());
        }
        // Posar moviments i mandangón
        /*movx = new int[8];
        movy = new int[8];
        int pos = 0;
        movx[pos] = 1;
        movy[pos++] = 0;
        movx[pos] = 1;
        movy[pos++] = -1;
        movx[pos] = 0;
        movy[pos++] = -1;
        movx[pos] = -1;
        movy[pos++] = -1;
        movx[pos] = -1;
        movy[pos++] = 0;
        movx[pos] = -1;
        movy[pos++] = 1;
        movx[pos] = 0;
        movy[pos++] = 1;
        movx[pos] = 1;
        movy[pos++] = 1;*/

        int[] directions = {-1, 0, 1};

// La posición actual del rey
        int currentRow = 3;
        int currentCol = 4;

// Almacenamos los posibles movimientos del rey en un array
        ArrayList<int[]> possibleMoves = new ArrayList<int[]>();

        for (int rowDirection : directions) {
            for (int colDirection : directions) {
                // Excluimos la dirección (0, 0), que corresponde a la posición actual del rey
                if (rowDirection != 0 || colDirection != 0) {
                    int nextRow = currentRow + rowDirection;
                    int nextCol = currentCol + colDirection;
                    // Verificamos si la siguiente posición está dentro del tablero

                }
            }
        }
    }
}
