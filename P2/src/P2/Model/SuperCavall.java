package P2.Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SuperCavall extends Peca{
    public SuperCavall(int x, int y)  {
        super(x, y);
        try{
            img= ImageIO.read(new File("img/SuperCavall.png"));
        }catch (IOException ioe){
            System.out.println(ioe.toString());
        }
        // Posar moviments i mandangón
        movx = new int[8];
        movy = new int[8];
        int pos = 0;
        movx[pos] = 2;
        movy[pos++] = 3;
        movx[pos] = 3;
        movy[pos++] = 2;
        movx[pos] = 2;
        movy[pos++] = -3;
        movx[pos] = 3;
        movy[pos++] = -2;
        movx[pos] = -2;
        movy[pos++] = 3;
        movx[pos] = -3;
        movy[pos++] = 2;
        movx[pos] = -2;
        movy[pos++] = -3;
        movx[pos] = -3;
        movy[pos++] = -2;

    }

}
