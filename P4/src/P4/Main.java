package P4;

import P4.Interficies.InterficieComunicacio;
import P4.Model.Graf;
import P4.Model.SAX.MeuSax;
import P4.Vista.Vista;

public class Main implements InterficieComunicacio {
    private Vista vista;
    public static boolean CONTINUAR=true;
    public static void main(String[] args) {
        new Main().inici();
    }

    public void inici() {
        //new Mesurament().mesura();
        Graf g=new Graf();
        vista = new Vista("mondongo", this,g);
        MeuSax sax=new MeuSax("mapes/grafobase.ltim",this,g);
        sax.llegir();
        vista.repaint();
        System.out.println(g.getMapa());
    }

    @Override
    public void comunicacio(String instruccio) {
        switch (instruccio) {
            case "stop":
                System.out.println("Aturant...");
                // Aturar tots els fils i aturar el programa
                CONTINUAR = false;
                vista.actualitzar();
                break;
            case "Actualitzar":
                // Tornar a pintar la GUI
                vista.actualitzar();
                break;
            case "play":
                // Envia l'ordre de començar
                //controlador.start();
                break;
            case "reset":
                // Envia l'ordre de reinici
                break;
        }

    }
}