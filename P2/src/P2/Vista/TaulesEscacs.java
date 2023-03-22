package P2.Vista;

import P2.Model.Cavall;
import P2.Model.Moviment;
import P2.Model.Peca;
import P2.Model.Tauler;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class TaulesEscacs extends JPanel {
    private Tauler tauler;

    // Mida del tauler
    public TaulesEscacs(int width, int height, Tauler tauler) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.tauler = tauler;
    }

    public void setTauler(Tauler tauler) {
        this.tauler = tauler;
    }

    @Override
    public void paintComponent(Graphics g) {
        int n = tauler.getDim();
        Graphics2D g2 = (Graphics2D) g;


        int squareXdim = getWidth() / n;
        int squareYdim = getHeight() / n;

        // Dibuixar tauler
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.BLACK);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i + j) % 2 == 0) {
                    g2.fillRect(squareXdim * i, squareYdim * j, squareXdim, squareYdim);
                }
            }
        }

        // Dibuixar peces
        Object peces[] = tauler.getPeces().toArray();
        for (int i = 0; i < peces.length; i++) {
            Peca p = (Peca) peces[i];
            g2.drawImage(p.getImg(), p.getX() * squareXdim, p.getY() * squareYdim, squareXdim, squareYdim, null);
        }

        // Dibuixar moviments


        Color colors[] = {Color.GREEN, Color.BLUE, Color.RED, Color.ORANGE, Color.CYAN, Color.MAGENTA, Color.PINK};
        g2.setFont(new Font("Arial", Font.BOLD, squareXdim / 3));
        //g2.drawString("10",squareXdim/3,squareYdim/2);
        g2.setStroke(new BasicStroke(8));

        int torn;
        int moviment;
        for (int i = 0; i < tauler.getDim(); i++) {
            for (int j = 0; j < tauler.getDim(); j++) {
                torn =tauler.getMoviment(i,j).getTorn();
                moviment =tauler.getMoviment(i,j).getnMoviment();
                if (torn != -1) {
                    g2.setColor(colors[torn%colors.length]);
                    g2.drawString(""+moviment,i*squareXdim+squareXdim/3,j*squareYdim+ squareYdim/2);
                    g2.drawOval(i*squareXdim,j*squareYdim,squareXdim,squareYdim);
                }
            }
        }


    }
}
