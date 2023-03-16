package P2.Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Reina extends Peca{

    public Reina()  {
        try{
            img= ImageIO.read(new File("img/reina.png"));
        }catch (IOException ioe){
            System.out.println(ioe.toString());
        }
        // Posar moviments i mandangón
    }
}
