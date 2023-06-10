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
    }

    @Override
    public void comunicacio(String instruccio) {
        switch (instruccio){
            case "generar":
                // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]
                tablero.generaFullRandom();
                vista.actualitzar();
                break;
            case "generarFacil":
                //[DESORDENARFACIL][DESORDENARFACIL][DESORDENARFACIL]
                Node nodeIniciGuiat = new Node(null, 0, tablero, 0, Node.tipusHeuristica.MANHATTAN);
                nodeIniciGuiat.desordenarEstatFinal(100);
                controlador = new Controlador(tablero, nodeIniciGuiat);
                vista.actualitzar();
                break;
            case "play":
                Node nodeInici = new Node(null, 0, tablero, 0, Node.tipusHeuristica.MANHATTAN);
                controlador = new Controlador(tablero,nodeInici);
                controlador.run();
                //controlador.trobarSolucio(nodeInici);
                break;
            case "actualitzar":
                vista.actualitzar();
                break;
            case "stop":
                CONTINUAR=false;
                vista.actualitzar();

                break;
        }
        if (instruccio.startsWith("dimensio")) {
            int n = Integer.parseInt(instruccio.split(":")[1]);
            tablero=new Estat(n);
            vista.setModel(tablero);
            vista.actualitzar();
            // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]
        }
    }
}