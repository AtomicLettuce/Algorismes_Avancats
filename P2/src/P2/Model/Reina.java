package P2.Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Reina extends Peca{

    public Reina(int d, int x, int y)  {
        super(x, y);
        try{
            img= ImageIO.read(new File("img/reina.png"));
        }catch (IOException ioe){
            System.out.println(ioe.toString());
        }
        //Moviemnts
        movx = new int[(d-1)*4*2];
        movy = new int[(d-1)*4*2];
        int pos = 0;
        for (int i = -(d-1); i < d; i++) {
            if (i != 0) {
                movx[pos] = 0; // vertical
                movy[pos++] = i; //vertical
                movx[pos] = i; // horizontal
                movy[pos++] = 0; //horizontal
                movx[pos] = i; //   oblicuo 1
                movy[pos++] = i; //    oblicuo1
                movx[pos] = -i; //   oblicuo 2
                movy[pos++] = i; //    oblicuo2
            }
        }
    }
}
