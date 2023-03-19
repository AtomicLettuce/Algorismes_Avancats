package P2.Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Torre extends Peca{

    public Torre()  {
        try{
            img= ImageIO.read(new File("img/Torre.png"));
        }catch (IOException ioe){
            System.out.println(ioe.toString());
        }
        // Posar moviments i mandangón
    }
}
