package P2.Vista;

import P2.Model.Cavall;
import P2.Model.Peca;
import P2.Model.Tauler;

import javax.swing.*;
import java.awt.*;

public class TaulesEscacs extends JPanel {
    Tauler tauler;
    // Mida del tauler
    public TaulesEscacs(int width, int height, Tauler tauler){
        super();
        this.setPreferredSize(new Dimension(width,height));
        this.tauler=tauler;
    }

    @Override
    public void paintComponent(Graphics g) {
        int n=tauler.getDim();
        Graphics2D g2 = (Graphics2D) g;


        int squareXdim=getWidth()/n;
        int squareYdim=getHeight()/n;

        // Dibuixar tauler
        g2.setColor(Color.WHITE);
        g2.fillRect(0,0,getWidth(),getHeight());
        g2.setColor(Color.BLACK);
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j < n; j++) {
                if((i+j)%2==0){
                    g2.fillRect(squareXdim*i,squareYdim*j,squareXdim,squareYdim);
                }
            }
        }

        // Dibuixar peces
        Object peces[]=tauler.getPeces().toArray();
        for (int i = 0; i < peces.length; i++) {
            Peca p= (Peca) peces[i];
            g2.drawImage(p.getImg(),p.getX()*squareXdim,p.getY()*squareYdim,squareXdim,squareYdim,null);
        }





    }
}
