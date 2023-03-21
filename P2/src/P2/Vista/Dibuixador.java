package P2.Vista;

import P2.Main;

import javax.swing.*;

public class Dibuixador extends Thread{

    private JPanel panells;
    private Vista vista;
    private MonitorVista mv;

    public Dibuixador(JPanel p, Vista vista, MonitorVista mv){
        this.panells=p;
        this.vista=vista;
        this.mv =mv;

    }

    public void run(){
        while(true){
            // Si s'ha rebut la notificació d'aturar el programa, atural el fil de forma segura
            if(!Main.CONTINUAR){
                vista.setVisible(false);
                break;
            }
            // Espera a que hi hagi canvis per actualitzar
            try {
                mv.actualitzar(panells);
                // Ja ha dibuixat els nous canvis
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    }


}