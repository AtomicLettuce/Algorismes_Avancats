package P1.view;

import P1.Main;
import P1.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLOutput;

public class PanellDibuix extends JPanel {
    private BufferedImage bimg=null;
    private Model mod;
    private Vista vis;
    private Dibuxador dibuixador;

    public PanellDibuix(int xdim, int ydim, Model mod, Vista vis){
        this.setPreferredSize(new Dimension(xdim, ydim));
        this.mod=mod;
        this.vis=vis;
        dibuixador=new Dibuxador(this);
        dibuixador.start();
    }

    public void paint(Graphics gr){
        gr.setColor(Color.red);
        gr.fillRect(0,0,this.getWidth(),this.getHeight());
    }

    public void repaint(){
        if(this.getGraphics()!=null){
            paint(this.getGraphics());
        }
    }





    class Dibuxador extends Thread{
        private PanellDibuix pd;
        public Dibuxador(PanellDibuix pd){
            this.pd=pd;
        }


        public void run(){
            while(true){
                if(pd.vis.actualitzar){
                    pd.repaint();
                }
                // Si s'ha rebut la notificació d'aturar el programa, atural el fil de forma segura
                if(!Main.CONTINUAR){
                    break;
                }
            }
            // 1.- DORMIR FINS QUE REBI UNA NOTIFICACIÓ DE QUE HI HA HAGUT CANVI DE DADES
            // 2.- DESPERTAR-SE I DIBUIXAR ELS CANVIS
            // 3.- PUBLICAR-LOS A LA PANTALLA
            // 4.- TORNAR A PAS 1
        }
    }

}
