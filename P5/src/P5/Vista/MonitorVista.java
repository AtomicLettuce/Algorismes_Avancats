package P5.Vista;

import javax.swing.*;

public class MonitorVista {
    private boolean actualitzar;
    private boolean sortir;

    public MonitorVista() {
        actualitzar = false;
        sortir=false;
    }

    public synchronized void notificarActualitzar() {
        actualitzar = true;
        notifyAll();
    }

    public synchronized void notificarSortida(){
        sortir=true;
        notifyAll();
    }

    public synchronized void actualitzar(JPanel panells) throws InterruptedException {
        while (!actualitzar&&!sortir) {
            wait();
        }
        if(sortir){
            notifyAll();
            return;
        }
        panells.repaint();
        actualitzar = false;
        notifyAll();
    }
}

