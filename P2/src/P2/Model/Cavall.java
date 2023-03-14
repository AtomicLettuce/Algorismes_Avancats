package P2.Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Cavall extends Peca{
    public Cavall()  {
        try{
        img= ImageIO.read(new File("img/cavall.png"));
        }catch (IOException ioe){
            System.out.println(ioe.toString());
        }
        // Posar moviments i mandangón


    }


}
