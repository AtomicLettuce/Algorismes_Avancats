package P1.vista;

import javax.swing.*;

public class Monitor_Vista {

    private boolean actualitzar;
    public Monitor_Vista(){
        actualitzar=false;
    }

    public synchronized void notificarActualitzar(){
        actualitzar=true;
        notifyAll();
    }

    public synchronized void actualitzar(JPanel[] panells) throws InterruptedException {
        while (!actualitzar){
            wait();
        }
        for(int i=0;i<panells.length;i++){
            panells[i].repaint();
        }
        actualitzar=false;
        notifyAll();
    }
}
