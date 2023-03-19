package P2.Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Alfil extends Peca{

    public Alfil()  {
        try{
            img= ImageIO.read(new File("img/alfil.png"));
        }catch (IOException ioe){
            System.out.println(ioe.toString());
        }
        // Posar moviments i mandangón
    }
}
