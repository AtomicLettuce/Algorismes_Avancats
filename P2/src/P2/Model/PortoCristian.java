package P2.Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PortoCristian extends Peca{



    public PortoCristian(int x, int y) {
        super(x, y); movx = new int[8];
        try{
            img= ImageIO.read(new File("img/portoCristian.png"));
        }catch (IOException ioe){
            System.out.println(ioe.toString());
        }


        movy = new int[8];
        int pos = 0;
        movx[pos] = -1;
        movy[pos++] = 1;
        movx[pos] = 1;
        movy[pos++] = 1;
        movx[pos] = -1;
        movy[pos++] = -1;
        movx[pos] = 1;
        movy[pos++] = 1;
        movx[pos] = 0;
        movy[pos++] = 2;
        movx[pos] = 0;
        movy[pos++] = -2;
        movx[pos] = 2;
        movy[pos++] = 0;
        movx[pos] = -2;
        movy[pos++] = 0;

    }

}
