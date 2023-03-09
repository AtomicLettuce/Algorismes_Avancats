package P1.view;

import P1.Main;

import javax.swing.*;
// 1.- DORMIR FINS QUE REBI UNA NOTIFICACIÓ DE QUE HI HA HAGUT CANVI DE DADES
// 2.- DESPERTAR-SE I DIBUIXAR ELS CANVIS
// 3.- PUBLICAR-LOS A LA PANTALLA
// 4.- TORNAR A PAS 1
public class Dibuixador extends Thread {

        private JPanel[] panells;
        private Monitor_Vista mv;
        private Vista vis;
        public Dibuixador(JPanel[] p, Monitor_Vista mv,Vista vis){
            this.panells=p;
            this.mv=mv;
            this.vis=vis;
        }


        public void run(){
            while(true){
                // Espera a que hi hagi canvis per actualitzar
                try {
                    mv.actualitzar(panells);
                    // Ja ha dibuixat els nous canvis
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Si s'ha rebut la notificació d'aturar el programa, atural el fil de forma segura
                if(!Main.CONTINUAR){
                    vis.setVisible(false);
                    break;
                }
            }
        }
}
