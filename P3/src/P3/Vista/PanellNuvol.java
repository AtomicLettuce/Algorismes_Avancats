package P3.Vista;

import P3.Model.Nuvol;
import P3.Model.Punt;

import javax.swing.*;
import java.awt.*;

public class PanellNuvol extends JPanel {
    private Nuvol nuvol;

    // Mida del tauler
    public PanellNuvol(int width, int height, Nuvol nuvol) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.nuvol = nuvol;
    }


    @Override
    public void paintComponent(Graphics g) {

        // Per dibuixar un punt, dibuixam una línia que comença i acaba allà mateix
        g.drawLine(10, 10, 10, 10);
        Punt[] punts = nuvol.getNuvol();
        int width = getWidth();
        int height = getHeight();

        // Cas per dibuixar una equiprobable
        switch (nuvol.getDistribucio()) {
            case 0:
                int max = nuvol.getMax();
                for (int i = 0; i < punts.length; i++) {
                    // Cordenada del punt
                    double x = punts[i].getPunt()[0];
                    double y = punts[i].getPunt()[1];

                    // Coordenada que ocupara a la pantalla
                    int xi = (int) ((x / max) * width);
                    int yi = (int) ((y / max) * height);


                    g.drawLine(xi, yi, xi, yi);

                }
                break;

            // Cas per dibuixar una gausiana
            case 1:
                for (int i = 0; i < punts.length; i++) {
                    double x = punts[i].getPunt()[0];
                    double y = punts[i].getPunt()[1];

                    if ((x>-5 &&x < 5) && (y>-5&&y < 5)) {
                        // Coordenada que ocupara a la pantalla
                        int xi = (int) (((x / 5) * (width/2))+width/2);
                        int yi = (int) (((y / 5) * (height/2))+height/2);


                        g.drawLine(xi, yi, xi, yi);
                    }

                }
                break;
        }
    }


}
