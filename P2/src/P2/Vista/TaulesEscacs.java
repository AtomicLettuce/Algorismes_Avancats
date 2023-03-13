package P2.Vista;

import javax.swing.*;
import java.awt.*;

public class TaulesEscacs extends JPanel {
    // Mida del tauler
    private int n;
    public TaulesEscacs(int width, int height, int n){
        super();
        this.setPreferredSize(new Dimension(width,height));
        this.n=n;

    }

    @Override
    public void paintComponent(Graphics g) {
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


    }
}
