package P2.Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Alfil extends Peca{

    public Alfil(int d, int x, int y)  {
        super(x,y);
        try{
            img= ImageIO.read(new File("img/alfil.png"));
        }catch (IOException ioe){
            System.out.println(ioe.toString());
        }
        movx = new int[(d - 1) * 4];
        movy = new int[(d - 1) * 4];
        int pos = 0;
        // Posar moviments i mandangón
        for (int i = -(d - 1); i < d; i++) {
            if (i != 0) {
                movx[pos] = i; // vertical
                movy[pos++] = i; //vertical
                movx[pos] = i; // horizontal
                movy[pos++] = i; //horizontal
            }
        }
    }
}
