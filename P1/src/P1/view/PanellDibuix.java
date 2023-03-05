package P1.view;

import P1.model.Model;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;

public class PanellDibuix extends JPanel {

    Model mod;
    Vista vis;


    public PanellDibuix(int xdim, int ydim, Model mod, Vista vis){
        this.setPreferredSize(new Dimension(xdim, ydim));
        this.mod=mod;
        this.vis=vis;
    }



    class Dibuxador extends Thread{
        private PanellDibuix pd;
        public Dibuxador(PanellDibuix pd){
            this.pd=pd;
        }


        public void run(){
            System.out.println("b");
            // 1.- DORMIR FINS QUE REBI UNA NOTIFICACIÓ DE QUE HI HA HAGUT CANVI DE DADES
            // 2.- DESPERTAR-SE I DIBUIXAR ELS CANVIS
            // 3.- PUBLICAR-LOS A LA PANTALLA
            // 4.- TORNAR A PAS 1
        }
    }

}
