package P2.Vista;

import javax.swing.*;

public class MonitorVista {

    private boolean actualitzar;

    public MonitorVista() {
        actualitzar = false;
    }

    public synchronized void notificarActualitzar() {
        actualitzar = true;
        notifyAll();
    }

    public synchronized void actualitzar(JPanel panells) throws InterruptedException {
        while (!actualitzar) {
            wait();
        }
        panells.repaint();
        actualitzar = false;
        notifyAll();
    }


}
