package P4;

import P4.Interficies.InterficieComunicacio;
import P4.Model.Graf;
import P4.Model.SAX.MeuSax;
import P4.Vista.Vista;

public class Main implements InterficieComunicacio {
    private Vista vista;
    private Graf g;
    public static boolean CONTINUAR=true;
    public static void main(String[] args) {
        new Main().inici();
    }

    public void inici() {
        //new Mesurament().mesura();
        g=new Graf();
        vista = new Vista("mondongo", this,g);
        MeuSax sax=new MeuSax("mapes/grafobase.ltim",this,g);
        sax.llegir();
        vista.repaint();
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
        // Format Origen:'n'
        // on n és índex de node
        if(instruccio.startsWith("Origen:")){
            int n = Integer.parseInt(instruccio.split(":")[1]);
            // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]


        }
        // Format Desti:'n'
        // on n és índex de node
        else if(instruccio.startsWith("Desti:")){
            int n = Integer.parseInt(instruccio.split(":")[1]);
            // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]

        }

    }
}