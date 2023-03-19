package P2.Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Peca {
    protected BufferedImage img;
    protected int movx[];
    protected int movy[];

    protected int X;
    protected int Y;
    protected boolean afecta_dimensio;

    public Peca(int x , int y){
        X = x;
        Y = y;
    }

    public BufferedImage getImg() {
        return img;
    }

    public boolean isAfecta_dimensio() {
        return afecta_dimensio;
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
