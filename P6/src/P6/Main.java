package P6;

import P6.Controlador.Controlador;
import P6.Interficies.InterficieComunicacio;
import P6.Model.Estat;
import P6.Model.Node;
import P6.Vista.Vista;
import mesurament.Mesurament;

public class Main implements InterficieComunicacio {
    private Estat tauler;
    private Vista vista;
    private Controlador controlador;
    public static boolean CONTINUAR = true;
    private Node.tipusHeuristica heuristicaSeleccionada;
    public static void main(String[] args) {
        new Main().inici();
    }

    public void inici() {
        new Mesurament().mesura();
        tauler = new Estat(4);
        System.out.println(tauler.esResoluble(tauler.getPuzzle()));
        vista = new Vista("P6: Branch&Bound", this, tauler);
        vista.actualitzar();
        heuristicaSeleccionada=Node.tipusHeuristica.MANHATTAN;
    }

    @Override
    public void comunicacio(String instruccio) {
        switch (instruccio){
            case "generar":
                tauler.generaFullRandom();
                vista.actualitzar();
                break;
            case "play":
                Node nodeInici = new Node(null, 0, tauler, 0, heuristicaSeleccionada);
                controlador = new Controlador(tauler,nodeInici,this);
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
            case "Manhattan":
                heuristicaSeleccionada= Node.tipusHeuristica.MANHATTAN;
                break;
            case "Trivial":
                heuristicaSeleccionada= Node.tipusHeuristica.TRIVIAL;
                break;
        }
        if (instruccio.startsWith("dimensio")) {
            int n = Integer.parseInt(instruccio.split(":")[1]);
            tauler =new Estat(n);
            vista.setModel(tauler);
            vista.actualitzar();
            // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]
        }
        if(instruccio.startsWith("generarFacil")){
            int n = Integer.parseInt(instruccio.split(":")[1]);
            Node nodeIniciGuiat = new Node(null, 0, tauler, 0, heuristicaSeleccionada);
            nodeIniciGuiat.desordenarEstatFinal(n);
            tauler.setPuzzle(nodeIniciGuiat.getDisposicio().getPuzzle());
            vista.actualitzar();
        }if(instruccio.startsWith("Trobat")){
            vista.popup(instruccio);
        }
    }
}