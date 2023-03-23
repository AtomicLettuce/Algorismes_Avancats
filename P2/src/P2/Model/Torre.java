package P2.Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Torre extends Peca{

    public Torre(int d, int x, int y)  {
        super(x,y);
        try{
            img= ImageIO.read(new File("img/Torre.png"));
        }catch (IOException ioe){
            System.out.println(ioe.toString());
        }
        afecta_dimensio = true;
        //Moviemnts
        movx = new int[(d - 1) * 4];
        movy = new int[(d - 1) * 4];
        int pos = 0;
        for (int i = -(d - 1); i < d; i++) {
            if (i != 0) {
                movx[pos] = 0; // vertical
                movy[pos++] = i; //vertical
                movx[pos] = i; // horizontal
                movy[pos++] = 0; //horizontal
            }
        }
    }
}
