package Controlador;

import Model.Node;
import Model.Estat;

import java.util.*;

public class Controlador extends Thread {

    private Estat estat;
    public Controlador(Estat estat) {
        this.estat = estat;
    }

    @Override
    public void run() {
    }

    public List<String> trobarSolucio(Node nodoInicial) {
        PriorityQueue<Node> colaPrioridad = new PriorityQueue<>(new nodeWeightComparator());
        colaPrioridad.add(nodoInicial);
        ArrayList<Node> nodosVisitados = new ArrayList<>();
        long inicio = System.currentTimeMillis();




        while(colaPrioridad.size() > 0) {
             Node nodoActual = colaPrioridad.poll();

             System.out.println(nodoActual);
             System.out.println(nodoActual.getHeuristica());

             if (nodoActual.esSolucio()) {
                 long fin = System.currentTimeMillis();
                 long tiempoTranscurrido = fin - inicio;
                 double tiempoSegundos = tiempoTranscurrido / 1000.0; // Convertir a segundos

                 System.out.println("Tiempo transcurrido: " + tiempoSegundos + " segundos");
                 return construirCamino(nodoActual);
             }

             nodoActual.generarFills();

            for (Node hijo : nodoActual.getFills()) {
                boolean toAdd = true;
                for (Node node: nodosVisitados) {
                    if(hijo.isEqual(node)) {
                        toAdd = false;
                        break;
                    }
                }
                if(toAdd) {
                    nodosVisitados.add(hijo);
                    colaPrioridad.offer(hijo);
                }
            }
        }
        long fin = System.currentTimeMillis();
        long tiempoTranscurrido = fin - inicio;
        double tiempoSegundos = tiempoTranscurrido / 1000.0; // Convertir a segundos

        System.out.println("Tiempo transcurrido: " + tiempoSegundos + " segundos");
         return new ArrayList<>();
    }

    private class nodeWeightComparator implements Comparator<Node> {
        @Override
        public int compare(Node nodo1, Node nodo2) {
            return Integer.compare(nodo1.getHeuristica(), nodo2.getHeuristica());
        }
    }



    private ArrayList<String> construirCamino(Node nodo) {
        ArrayList<String> camino = new ArrayList<>();
        Node nodoActual = nodo;

        while (nodoActual != null) {
            camino.add(0, nodoActual.toString());
            nodoActual = nodoActual.getPare();
        }

        return camino;
    }
}
