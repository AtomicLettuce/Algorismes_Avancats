import Controlador.Controlador;
import Model.Estat;
import Model.Node;
import Vista.Vista;

import java.util.List;

public class Main {
    private Controlador controlador;
    private Estat tablero;

    public static void main(String[] args) {
        new Main().inici();
    }

    public void inici() {
//        tablero = new Estat(4);

        tablero = new Estat(new int[][] {{1,2,3,4},{5,6,7,8},{10,11,9,12},{14,0,13,15}});
        controlador = new Controlador(tablero);
        Node nodeInici = new Node(null, 0, tablero, 0);

        System.out.println("----------- TAULER INICIAL -----------");
        System.out.println(nodeInici);
        List<String> sol = controlador.trobarSolucio(nodeInici);
        if(sol.size() != 0) {
            System.out.println("----------- SOLUCIÓ -----------");
            System.out.println(sol);
        } else {
            System.err.println("La configuració inicial no té solució vàlida.");
        }
    }
}