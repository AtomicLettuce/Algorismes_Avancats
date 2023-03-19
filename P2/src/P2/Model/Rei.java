package P2.Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Rei extends Peca{


    public Rei(int x, int y)  {
        super(x, y);
        try{
            img= ImageIO.read(new File("img/rei.png"));
        }catch (IOException ioe){
            System.out.println(ioe.toString());
        }
        // Posar moviments i mandangón
        movx = new int[8];
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
        movy[pos++] = 1;
    }
}
