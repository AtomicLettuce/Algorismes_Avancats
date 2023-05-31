import Controlador.Controlador;
import Model.Tauler;
import Model.Node;
import Vista.Vista;

import java.util.List;

public class Main {
    public static boolean CONTINUAR = true;
    private Vista v;
    private Controlador controlador;
    private Tauler tablero;

    public static void main(String[] args) {
        new Main().inici();
    }

    public void inici() {
        tablero = new Tauler(3);
        controlador = new Controlador(tablero);
        Node nodeInici = new Node(null, 0, tablero, 0);



        System.out.println("----------- TAULER INICIAL -----------");
        System.out.println(nodeInici);

        List<String> sol = controlador.trobarSolucio(nodeInici);
        if(sol.size() != 0) {
            System.out.println(sol);
        } else {
            System.err.println("La configuració inicial no té solució vàlida.");
        }
    }
}