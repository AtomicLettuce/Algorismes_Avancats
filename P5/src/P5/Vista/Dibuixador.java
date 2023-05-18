package P5.Vista;

import P5.Main;

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
        System.out.println("dibuixador begins");
        while(true){
            // Espera a que hi hagi canvis per actualitzar
            try {
                mv.actualitzar(panells);
                //  System.out.println("refreshed");
                // Ja ha dibuixat els nous canvis
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Si s'ha rebut la notificació d'aturar el programa, atura el fil de forma segura
            if(!Main.CONTINUAR){
                vista.setVisible(false);
                break;
            }
        }
        System.out.println("Dibuixador acaba");
    }
}
