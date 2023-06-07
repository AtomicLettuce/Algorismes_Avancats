package P6.Vista;

import P6.Model.Estat;

import javax.swing.*;
import java.awt.*;

public class PanellPuzzle extends JPanel {
    private Estat model;

    public PanellPuzzle(int width, int height, Estat model) {
        super();
        this.model = model;
        this.setPreferredSize(new Dimension(width, height));
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getHeight(), getWidth());

        dibuixa_graella(g2);
        if (model.imatgepuzzle != null) {
            dibuixa_imatge(g2);
        } else {
            dibuixa_nums(g2);
        }
        dibuixa_graella(g2);
    }

    public void dibuixa_imatge(Graphics2D g2) {

        //g2.drawImage(model.imatgepuzzle, 0,0,getWidth(),getHeight(),null);
        int incrementX = getWidth() / model.getDimensioPuzzle();
        int incrementY = getHeight() / model.getDimensioPuzzle();

        int imatgeIncrementX = model.imatgepuzzle.getHeight();
        int imatgeIncrementY = model.imatgepuzzle.getWidth();
        for (int y = 0; y < model.getDimensioPuzzle(); y++) {
            for (int x = 0; x < model.getDimensioPuzzle(); x++) {
                if (model.getPosicio(x, y) > 0) {
                    int sx1 = tradueixCoordenadesX(model.getPosicio(x, y)) * incrementX;
                    int sy1 = tradueixCoordenadesY(model.getPosicio(x, y)) * incrementY;
                    int sx2 = sx1 + incrementX;
                    int sy2 = sy1 + incrementY;
                    g2.drawImage(model.imatgepuzzle,
                            y * incrementX,
                            x * incrementY,
                            (y + 1) * incrementX,
                            (x + 1) * incrementY,
                            sx1,
                            sy1,
                            sx2,
                            sy2,
                            null);
                }
            }
        }
    }

    public int tradueixCoordenadesX(int num) {
        return (num - 1) % model.getDimensioPuzzle();
    }

    public int tradueixCoordenadesY(int num) {
        return (num - 1) / model.getDimensioPuzzle();
    }

    public void dibuixa_nums(Graphics2D g2) {
        int incrementX = getWidth() / model.getDimensioPuzzle();
        int incrementY = getHeight() / model.getDimensioPuzzle();
        int x = incrementX / 2;
        int y = incrementY / 2;
        for (int i = 0; i < model.getDimensioPuzzle(); i++) {
            for (int j = 0; j < model.getDimensioPuzzle(); j++) {
                g2.drawString("" + model.getPosicio(i, j), x, y);
                // System.out.print(model.getPosicio(i,j)+" ");
                x = x + incrementX;
            }
            x = incrementX / 2;
            y = y + incrementY;
            System.out.println();
        }
    }

    public void dibuixa_graella(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        int incrementX = getWidth() / model.getDimensioPuzzle();
        int incrementY = getHeight() / model.getDimensioPuzzle();
        for (int i = 0; i < model.getDimensioPuzzle() + 1; i++) {
            g2.drawLine(i * incrementX, 0, i * incrementX, getHeight());
        }
        for (int i = 0; i < model.getDimensioPuzzle() + 1; i++) {
            g2.drawLine(0, i * incrementY, getWidth(), i * incrementY);
        }


    }


}