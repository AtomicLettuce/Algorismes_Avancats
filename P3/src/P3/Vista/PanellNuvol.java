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

        Graphics2D g2= (Graphics2D)g;
        g2.setStroke(new BasicStroke(2));


        Punt[] punts = nuvol.getNuvol();
        int width = getWidth();
        int height = getHeight();

        g2.setColor(Color.WHITE);
        g2.fillRect(0,0,width,height);
        g2.setColor(Color.BLACK);

        // Dibuixam el núvol de punts
        if(punts[0]==null){
            return;
        }

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
                    // Per dibuixar un punt, dibuixam una línia que comença i acaba allà mateix
                    g2.drawLine(xi, yi, xi, yi);
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

                        // Per dibuixar un punt, dibuixam una línia que comença i acaba allà mateix
                        g2.drawLine(xi, yi, xi, yi);
                    }

                }
                break;
        }

        // Dibuixam punts mes propers

        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(5));
        switch (nuvol.getDistribucio()){
            case 0:
                if(nuvol.getParells()==null){
                    System.out.println("parells null");
                    return;
                }
                for (int i = 0; i <nuvol.getParells().length ; i++) {
                    if(nuvol.getParells()[i]==null){
                        System.out.println("parells[i] null" );
                        return;
                    }
                    // Cordenada del punt
                    double x = nuvol.getParells()[i].getPunt1().getPunt()[0];
                    double y = nuvol.getParells()[i].getPunt1().getPunt()[1];

                    // Coordenada que ocupara a la pantalla
                    int xi = (int) ((x / nuvol.getMax()) * width);
                    int yi = (int) ((y / nuvol.getMax()) * height);
                    // Per dibuixar un punt, dibuixam una línia que comença i acaba allà mateix
                    g2.drawLine(xi, yi, xi, yi);

                    x = nuvol.getParells()[i].getPunt2().getPunt()[0];
                    y = nuvol.getParells()[i].getPunt2().getPunt()[1];

                    // Coordenada que ocupara a la pantalla
                    xi = (int) ((x / nuvol.getMax()) * width);
                    yi = (int) ((y / nuvol.getMax()) * height);
                    // Per dibuixar un punt, dibuixam una línia que comença i acaba allà mateix
                    g2.drawLine(xi, yi, xi, yi);
                }


                break;
            case 1:
                if(nuvol.getParells()==null){
                    return;
                }
                for (int i = 0; i <nuvol.getParells().length ; i++) {
                    if(nuvol.getParells()[i]==null){
                        return;
                    }
                    // Cordenada del punt
                    double x = nuvol.getParells()[i].getPunt1().getPunt()[0];
                    double y = nuvol.getParells()[i].getPunt1().getPunt()[1];

                    if ((x>-5 &&x < 5) && (y>-5&&y < 5)) {
                        // Coordenada que ocupara a la pantalla
                        int xi = (int) (((x / 5) * (width/2))+width/2);
                        int yi = (int) (((y / 5) * (height/2))+height/2);

                        // Per dibuixar un punt, dibuixam una línia que comença i acaba allà mateix
                        g2.drawLine(xi, yi, xi, yi);
                    }


                    x = nuvol.getParells()[i].getPunt2().getPunt()[0];
                    y = nuvol.getParells()[i].getPunt2().getPunt()[1];

                    if ((x>-5 &&x < 5) && (y>-5&&y < 5)) {
                        // Coordenada que ocupara a la pantalla
                        int xi = (int) (((x / 5) * (width/2))+width/2);
                        int yi = (int) (((y / 5) * (height/2))+height/2);

                        // Per dibuixar un punt, dibuixam una línia que comença i acaba allà mateix
                        g2.drawLine(xi, yi, xi, yi);
                    }

                }


                break;
        }
    }


}
