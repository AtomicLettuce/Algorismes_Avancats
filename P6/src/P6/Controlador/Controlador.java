package P6.Controlador;

import P6.Main;
import P6.Model.Node;
import P6.Model.Estat;

import java.util.*;

public class Controlador extends Thread {

    private final Estat estat;
    private Node nInicial;

    public Controlador(Estat estat,Node nInicial) {
        this.estat = estat;
        this.nInicial=nInicial;
    }

    // Cream el thread.
    @Override
    public void run() {
        System.out.println("Controlador begins");
        long t1 = System.nanoTime();
        trobarSolucio(nInicial);
        System.out.println("Controlador acaba");
    }

    public List<Estat> trobarSolucio(Node nodeInicial) {
        // Coa de prioritat per nodes ordenat per la seva heurística. (Implementat a node)
        PriorityQueue<Node> coaPrioritat = new PriorityQueue<>();
        // Afegim la configuració actual.
        coaPrioritat.add(nodeInicial);

        // HashSet per a la llista de nodes tancats. (Configuracions per les quals ja hem passat)
        Set<Node> nodosVisitados = new HashSet<>();
        // Afegim la configuració actual.
        nodosVisitados.add(nodeInicial);

        // Contador de temps.
        long inicio = System.currentTimeMillis();

        // Mentres la coa tengui elements segueix.
        while (coaPrioritat.size() > 0) {
            if (!Main.CONTINUAR) {
                return null;
            }
            // Agafam el node amb millor heurística.
            Node nodeActual = coaPrioritat.poll();

            // ! DEBUG: BORRAR !
            // System.out.println(nodeActual);

            // Si la configuració és solució aturam.
            if (nodeActual.esSolucio()) {
                // Calculam que hem tardat fins la solució.
                long fin = System.currentTimeMillis();
                long tiempoTranscurrido = fin - inicio;
                double tiempoSegundos = tiempoTranscurrido / 1000.0; // Convertir a segundos
                System.out.println("Tiempo transcurrido: " + tiempoSegundos + " segundos");
                List<Estat> sol = reconstruirCami(nodeActual);
                estat.sol = sol;
                System.out.println("Trobat en "+sol.size()+" pases.");
                return sol;
            }

            // Si no es solució generam els seus fills.
            nodeActual.generarFills();

            // Si els fills no han estat visitats els afegim a una possible solució, sinó els descartem.
            for (Node hijo : nodeActual.getFills()) {
                if (!nodosVisitados.contains(hijo)) {
                    nodosVisitados.add(hijo);
                    coaPrioritat.offer(hijo);
                }
            }
        }

        // ERROR! ALGUNA COSA HA ANAT MALAMENT
        long fin = System.currentTimeMillis();
        long tiempoTranscurrido = fin - inicio;
        double tiempoSegundos = tiempoTranscurrido / 1000.0;
        System.err.println("Tiempo transcurrido: " + tiempoSegundos + " segundos");
        return new ArrayList<>();
    }


    private ArrayList<Estat> reconstruirCami(Node node) {
        // Cream el camí
        ArrayList<Estat> cami = new ArrayList<>();
        Node nodeActual = node;
        // Mentres tengui pare seguim.
        while (nodeActual != null) {
            // Afegim al principi de la llista, evitam fer un reverse.
            cami.add(0, nodeActual.getDisposicio());
            // Agafam el següent element.
            nodeActual = nodeActual.getPare();
        }


        // Retornam la solució.
        return cami;
    }
}
