package P6;

import P6.Controlador.Controlador;
import P6.Interficies.InterficieComunicacio;
import P6.Model.Estat;
import P6.Model.Node;
import P6.Vista.Vista;

import java.util.List;

public class Main implements InterficieComunicacio {
    private Controlador controlador;
    private Vista vista;
    public static boolean CONTINUAR = true;
    private Estat tablero;

    public static void main(String[] args) {
        new Main().inici();
    }

    public void inici() {
        tablero = new Estat(4);
        System.out.println(tablero.esResoluble(tablero.getPuzzle()));
        vista = new Vista("mondongo", this, tablero);
        vista.actualitzar();

        /*controlador = new Controlador(tablero);
        Node nodeInici = new Node(null, 0, tablero, 0);

        System.out.println("----------- TAULER INICIAL -----------");
        System.out.println(nodeInici);
        List<Estat> sol = controlador.trobarSolucio(nodeInici);
        if (sol.size() != 0) {
            System.out.println("----------- SOLUCIÓ -----------");
            System.out.println("\t   Trobat en " + sol.size() + " pases.");
            System.out.println(sol);
        } else {
            System.err.println("La configuració inicial no té solució vàlida.");
        }*/
    }

    @Override
    public void comunicacio(String instruccio) {
        switch (instruccio){
            case "generar":
                // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]
                break;
            case "play":
                Node nodeInici = new Node(null, 0, tablero, 0);
                controlador = new Controlador(tablero);
                controlador.trobarSolucio(nodeInici);
                break;
            case "actualitzar":
                vista.actualitzar();
                break;
        }
        if (instruccio.startsWith("dimensio")) {
            int n = Integer.parseInt(instruccio.split(":")[1]);
            // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]
        }
    }
}