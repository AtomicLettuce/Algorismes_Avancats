package Controlador;

import Model.Node;
import Model.Tauler;

import java.util.*;

public class Controlador extends Thread {

    private Tauler tauler;
    public Controlador(Tauler tauler) {
        this.tauler = tauler;
    }

    @Override
    public void run() {
    }

    public List<String> trobarSolucio(Node nodoInicial) {

        if(this.teSolucio(nodoInicial) == false) {
            return (new ArrayList<>());
        }

        PriorityQueue<Node> colaPrioridad = new PriorityQueue<>(new nodeWeightComparator());
        colaPrioridad.add(nodoInicial);
        HashSet<Tauler> nodosVisitados = new HashSet<>();

        while(colaPrioridad.size() > 0) {
             Node nodoActual = colaPrioridad.poll();


             if (nodoActual.esSolucio()) {
                 return construirCamino(nodoActual);
             }

             nodoActual.generarFills();

            for (Node hijo : nodoActual.getFills()) {
                if (!nodosVisitados.contains(hijo.getDisposicio())) {
                    colaPrioridad.offer(hijo);
                    nodosVisitados.add(hijo.getDisposicio());
                }
            }
        }

         return new ArrayList<>();
    }

    private class nodeWeightComparator implements Comparator<Node> {
        @Override
        public int compare(Node nodo1, Node nodo2) {
            return Integer.compare(nodo1.getHeuristica(), nodo2.getHeuristica());
        }
    }

    private List<String> construirCamino(Node nodo) {
        List<String> camino = new ArrayList<>();
        Node nodoActual = nodo;

        while (nodoActual != null) {
            camino.add(0, nodoActual.toString());
            nodoActual = nodoActual.getPare();
        }

        return camino;
    }

    public boolean teSolucio(Node nodoInicial) {
        int[] listaNumeros = new int[tauler.getDimensioPuzzle() * tauler.getDimensioPuzzle() - 1];
        int k = 0;
        for (int i = 0; i < tauler.getDimensioPuzzle(); i++) {
            for (int j = 0; j < tauler.getDimensioPuzzle(); j++) {
                if (tauler.getPosicio(i,j) != 0) {
                    listaNumeros[k++] = tauler.getPosicio(i,j);
                }
            }
        }

        int numeroInversiones = 0;
        for (int i = 0; i < listaNumeros.length - 1; i++) {
            for (int j = i + 1; j < listaNumeros.length; j++) {
                if (listaNumeros[i] > listaNumeros[j]) {
                    numeroInversiones++;
                }
            }
        }

        return numeroInversiones % 2 == 0;

    }

}
