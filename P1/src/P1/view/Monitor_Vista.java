package P1.view;

public class Monitor_Vista {

    private boolean actualitzar;
    public Monitor_Vista(){
        actualitzar=false;
    }

    public synchronized void notificarActualitzar(){
        actualitzar=true;
        notifyAll();
    }

    public synchronized void actualitzar(PanellDibuix pd) throws InterruptedException {
        while (!actualitzar){
            wait();
        }
        pd.repaint();
        actualitzar=false;
        notifyAll();
    }
}
