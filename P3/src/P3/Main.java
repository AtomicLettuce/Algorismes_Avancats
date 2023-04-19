package P3;

import P3.Controlador.Controlador;
import P3.Interficies.InterficieComunicacio;
import P3.Model.Nuvol;
import P3.Model.Parells;

import P3.Model.Punt;

import P3.Vista.Vista;


public class Main implements InterficieComunicacio {
    private Nuvol model;
    private Vista vista;
    public static boolean CONTINUAR = true;
    public static void main(String[] args) {
        new Main().inici();
    }

    public void inici(){
        Nuvol nuvol = new Nuvol(100,10);
        nuvol.generarNuvol();

        Punt[] aux = nuvol.getNuvol();
        vista=new Vista("Mondongo",this,nuvol);


        Controlador controlador = new Controlador(this, nuvol);
        Parells parells[] = controlador.n2();

        for (int i = 0; i < 3; i++) {
            System.out.println(parells[i].getPunt1().toString() + " i " +parells[i].getPunt2().toString());
            System.out.println(parells[i].getDistancia());
        }
    }

    @Override
    public void comunicacio(String instruccio) {
        switch (instruccio){
            case "stop":
                System.out.println("Aturant...");
                // Aturar tots els fils i aturar el programa
                CONTINUAR=false;
                vista.actualitzar();
                break;
            case "Actualitzar":
                // Tornar a pintar la GUI
                vista.actualitzar();
                break;
            case "play":
                // Envia l'ordre de començar

                // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]

                break;
            case "reset":
                // Envia l'ordre de reinici

                // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]
                break;
        }
        // Per canviar quantitat de punts que generam i algorisme que empram per resoldre-ho
        if (instruccio.startsWith("opcions:")){
            // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]
        }
    }
}
