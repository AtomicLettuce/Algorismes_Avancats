package P6.Vista;

import P6.Model.Estat;

import javax.swing.*;
import java.awt.*;

public class PanellPuzzle extends JPanel {
    private Estat model;
    private Vista vista;

    public PanellPuzzle(int width, int height, Estat model, Vista vista) {
        super();
        this.model = model;
        this.setPreferredSize(new Dimension(width, height));
        this.vista =vista;
    }

    public void setModel(Estat model) {
        this.model = model;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getHeight(), getWidth());
        dibuixa_graella(g2);
        if (model.sol != null) {
            dibuixa_solucio(g2);
        } else {
            if (model.imatgepuzzle != null) {
                dibuixa_imatge(g2,model);
            } else {
                dibuixa_nums(g2,model);
            }
        }
    }
    public void dibuixa_solucio(Graphics2D g2){
        Estat estat=model.sol.remove(0);
        if(model.sol.size()==0){
            model.sol=null;
            model.setPuzzle(estat.getPuzzle());
            JOptionPane.showMessageDialog(vista,"JA HAS TROBAT LA SOLUCIÓ","AVÍS",JOptionPane.WARNING_MESSAGE);
            vista.desactivaBlitz();
        }
        if (model.imatgepuzzle != null) {
            dibuixa_imatge(g2,estat);
        } else {
            dibuixa_nums(g2,estat);
        }
    }

    public void dibuixa_imatge(Graphics2D g2, Estat estat) {

        //g2.drawImage(model.imatgepuzzle, 0,0,getWidth(),getHeight(),null);
        int incrementX = getWidth() / estat.getDimensioPuzzle();
        int incrementY = getHeight() / estat.getDimensioPuzzle();

        int imatgeIncrementX = model.imatgepuzzle.getHeight();
        int imatgeIncrementY = model.imatgepuzzle.getWidth();
        for (int y = 0; y < estat.getDimensioPuzzle(); y++) {
            for (int x = 0; x < estat.getDimensioPuzzle(); x++) {
                if (estat.getPosicio(x, y) > 0) {
                    int sx1 = tradueixCoordenadesX(estat.getPosicio(x, y)) * incrementX;
                    int sy1 = tradueixCoordenadesY(estat.getPosicio(x, y)) * incrementY;
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
                }else{
                    g2.setColor(Color.CYAN);
                    g2.fillRect(y*incrementX,x*incrementY,(y+1)*incrementX,(x+1)*incrementY);
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

    public void dibuixa_nums(Graphics2D g2, Estat estat) {
        int incrementX = getWidth() / estat.getDimensioPuzzle();
        int incrementY = getHeight() / estat.getDimensioPuzzle();
        int x = incrementX / 2;
        int y = incrementY / 2;
        for (int i = 0; i < estat.getDimensioPuzzle(); i++) {
            for (int j = 0; j < estat.getDimensioPuzzle(); j++) {
                if(estat.getPosicio(i,j)==0){
                    g2.setColor(Color.CYAN);
                    g2.fillOval(x - (incrementX/2), y - (incrementY/2), incrementX, incrementY);
                    g2.setColor(Color.BLACK);
                }
                g2.drawString("" + estat.getPosicio(i, j), x, y);
                x = x + incrementX;
            }
            x = incrementX / 2;
            y = y + incrementY;
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