package P6.Controlador;

import P6.Model.Node;
import P6.Model.Estat;

import java.util.*;

public class Controlador extends Thread {

    private final Estat estat;
    public Controlador(Estat estat) {
        this.estat = estat;
    }

    // Cream el thread.
    @Override
    public void run() {
    }

    public List<String> trobarSolucio(Node nodeInicial) {
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

                return reconstruirCami(nodeActual);
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


    private ArrayList<String> reconstruirCami(Node node) {
        // Cream el camí
        ArrayList<String> cami = new ArrayList<>();
        Node nodeActual = node;
        // Mentres tengui pare seguim.
        while (nodeActual != null) {
            // Afegim al principi de la llista, evitam fer un reverse.
            cami.add(0, nodeActual.toString());
            // Agafam el següent element.
            nodeActual = nodeActual.getPare();
        }
        // Retornam la solució.
        return cami;
    }
}
