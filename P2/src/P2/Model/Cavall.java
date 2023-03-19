package P2.Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Cavall extends Peca{



    public Cavall(int x, int y)  {
        super(x,y);
        try{
        img= ImageIO.read(new File("img/cavall.png"));
        }catch (IOException ioe){
            System.out.println(ioe.toString());
        }
        // Posar moviments i mandangón
        movx = new int[8];
        movy = new int[8];
        int pos = 0;
        movx[pos] = 1;
        movy[pos++] = 2;
        movx[pos] = 2;
        movy[pos++] = 1;
        movx[pos] = 1;
        movy[pos++] = -2;
        movx[pos] = 2;
        movy[pos++] = -1;
        movx[pos] = -1;
        movy[pos++] = 2;
        movx[pos] = -2;
        movy[pos++] = 1;
        movx[pos] = -1;
        movy[pos++] = -2;
        movx[pos] = -2;
        movy[pos++] = -1;

    }


}
