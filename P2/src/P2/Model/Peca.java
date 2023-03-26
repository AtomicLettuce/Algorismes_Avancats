package P2.Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Peca {

    //Clase pare que permet implementar qualsevol tipus de peća
    protected BufferedImage img;

    //Moviments possible de la peća
    protected int movx[];
    protected int movy[];

    //Posició de la peća
    protected int X;
    protected int Y;


    public Peca(int x , int y){
        X = x;
        Y = y;
    }

    public BufferedImage getImg() {
        return img;
    }

    public int[] getMovy() {
        return movy;
    }

    public int[] getMovx() {
        return movx;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }
}
