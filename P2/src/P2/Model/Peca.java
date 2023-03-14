package P2.Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Peca {
    protected BufferedImage img;
    protected int movx[];
    protected int movy[];
    protected boolean afecta_dimensio;

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
}
