package P2.Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Rei extends Peca{
    public Rei()  {
        try{
            img= ImageIO.read(new File("img/rei.png"));
        }catch (IOException ioe){
            System.out.println(ioe.toString());
        }
        // Posar moviments i mandangón
    }
}
