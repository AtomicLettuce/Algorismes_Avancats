package P1.view;

import P1.Main;
import P1.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class PanellDibuix extends JPanel {
    private BufferedImage bimg=null;
    private Model mod;
    private Vista vis;
    private Dibuxador dibuixador;
    private Monitor_Vista mv;

    public PanellDibuix(int xdim, int ydim, Model mod, Vista vis, Monitor_Vista mv){
        this.setPreferredSize(new Dimension(xdim, ydim));
        this.mod=mod;
        this.vis=vis;
        this.mv=mv;
        dibuixador=new Dibuxador(this, mv);
        dibuixador.start();
    }

    public void paint(Graphics gr){
        gr.setColor(Color.BLACK);
        gr.fillRect(0,0,this.getWidth(),this.getHeight());
        //dibuixarGraella(gr);
        dibuixarLinies(gr);
    }


    private void dibuixarGraella(Graphics gr){
        gr.setColor(Color.WHITE);
        for(int i=0;i<getWidth()/10;i++){
            gr.drawLine(i*getWidth()/10,0,i*getWidth()/10,getHeight());
            gr.drawLine(0,i*getHeight()/10,getWidth(),i*getHeight()/10);
        }
    }

    private void dibuixarLinies(Graphics g){
        Graphics2D gr = (Graphics2D) g;

        Color colors[]={Color.GREEN,Color.CYAN,Color.RED, Color.MAGENTA,Color.YELLOW, Color.BLUE};
        Float [][] matriu =mod.getMatriu_temps();
        // Per poder fer el dibuix a escala
        int iteracions_max = Collections.max(mod.NITS);
        float max=0;
        // Obtenim el màxim temps per poder fer el dibuix a escala
        for (int i = 0; i < matriu.length; ++i) {
            // Inner loop for columns
            for (int j = 0; j < matriu[0].length; ++j) {
                // Storing the maximum element
                if(matriu[i][j]==null){
                    break;
                }
                max = Math.max(matriu[i][j], max);
            }
        }
        gr.setStroke(new BasicStroke(5));

        for(int i=0;i< matriu.length;i++){
            // inicialment serà l'origen de coordenades
            int xorigen=0;
            int yorigen=0;
            for(int j=0;j<matriu[i].length&&matriu[i][j]!=null;j++){
                // Pintar la línia que cada algorisme ha anat descrivint
                gr.setColor(colors[i]);
                int xdesti=(((100*mod.NITS.get(j))/iteracions_max)*getWidth())/100;
                int ydesti=Math.round((((100*matriu[i][j])/max)*getHeight())/100);
                gr.drawLine(xorigen,yorigen,xdesti,ydesti);
                xorigen=xdesti;
                yorigen=ydesti;
            }
        }





    }

    public void repaint(){
        if(this.getGraphics()!=null){
            paint(this.getGraphics());
        }
    }





    // 1.- DORMIR FINS QUE REBI UNA NOTIFICACIÓ DE QUE HI HA HAGUT CANVI DE DADES
    // 2.- DESPERTAR-SE I DIBUIXAR ELS CANVIS
    // 3.- PUBLICAR-LOS A LA PANTALLA
    // 4.- TORNAR A PAS 1
    class Dibuxador extends Thread{
        private PanellDibuix pd;
        private Monitor_Vista mv;
        public Dibuxador(PanellDibuix pd, Monitor_Vista mv){
            this.pd=pd;
            this.mv=mv;
        }


        public void run(){
            while(true){
                // Espera a que hi hagi canvis per actualitzar
                try {
                    mv.actualitzar(pd);
                    // Ja ha dibuixat els nous canvis
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Si s'ha rebut la notificació d'aturar el programa, atural el fil de forma segura
                if(!Main.CONTINUAR){
                    pd.vis.setVisible(false);
                    break;
                }
            }
        }
    }

}
