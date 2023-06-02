import Controlador.Controlador;
import Model.Estat;
import Model.Node;
import Vista.Vista;

import java.util.List;

public class Main {
    public static boolean CONTINUAR = true;
    private Vista v;
    private Controlador controlador;
    private Estat tablero;

    public static void main(String[] args) {
        new Main().inici();
    }

    public void inici() {
        tablero = new Estat(4);
        /*tablero = new Estat(new int[][] {
            {1, 3, 14, 13},
                {7,11,0,12},
                {6,10,5,9},{
                15,4,8,2}});*/
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