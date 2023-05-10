package P3;

import P3.Controlador.Controlador;
import P3.Interficies.InterficieComunicacio;
import P3.Model.Nuvol;
import P3.Vista.Vista;
import mesurament.Mesurament;


public class Main implements InterficieComunicacio {
    private Vista vista;
    private Nuvol nuvol;
    private Controlador controlador;
    public static boolean CONTINUAR = true;

    public static void main(String[] args) {
        new Main().inici();
    }

    public void inici() {
        new Mesurament().mesura();

        nuvol = new Nuvol(1, 10);
        vista = new Vista("P3: Dividir i vèncer", this, nuvol);
        controlador = new Controlador(this, nuvol);
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
                controlador.start();
                break;
            case "reset":
                // Envia l'ordre de reinici
                // Reiniciam model i controlador
                nuvol = new Nuvol(1, 10);
                controlador = new Controlador(this, nuvol);
                // Actualitzam canvis a la vista
                vista.setNuvol(nuvol);
                vista.actualitzar();
                break;
            case "controladorAcaba":
                vista.controladorAcaba();
                break;
        }


        // Per canviar quantitat de punts que generam i algorisme que empram per resoldre-ho
        // Fromat:
        // opcions: 'n' 'a' 'd'
        // sent n: numero de punts
        // a: algorisme a emprar (n^2, nlogn...)
        // d: distrubució aleatòria (gausiana, equiprobable, chi^2, etc.)
        if (instruccio.startsWith("opcions:")) {
            String aux = instruccio.split(":")[1];
            String[] aux2 = aux.split(" ");
            System.out.println(aux2.length);

            // Canviam valor de 'n'
            nuvol.setDimensio(Integer.parseInt(aux2[1]));
            // Canviam algorisme sel·leccionat
            controlador.setAlgorisme(Integer.parseInt(aux2[2]));
            // Canviam distribucio aleatòria
            nuvol.setDistribucio(Integer.parseInt(aux2[3]));

            nuvol.generarNuvol();
            vista.actualitzar();
        }
    }
}
