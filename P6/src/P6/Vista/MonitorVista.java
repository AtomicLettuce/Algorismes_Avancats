package P6.Vista;

import javax.swing.*;

public class MonitorVista {
    private boolean actualitzar;
    private boolean sortir;
    private boolean blitz;

    public MonitorVista() {
        actualitzar = false;
        sortir = false;
        blitz=false;
    }

    public synchronized void notificarActualitzar() {
        actualitzar = true;
        notifyAll();
    }

    public synchronized void activaBlitz() {
        blitz=true;
        notifyAll();
    }
    public synchronized void desactivaBlitz(){
        blitz=false;
    }

    public synchronized void notificarSortida() {
        sortir = true;
        notifyAll();
    }

    public synchronized void actualitzar(JPanel panells) throws InterruptedException {
        while (!actualitzar && !sortir&&!blitz) {
            wait();
        }
        if (sortir) {
            notifyAll();
            return;
        }
        panells.repaint();
        if (!blitz) {
            actualitzar = false;
        }
        notifyAll();
    }
}
