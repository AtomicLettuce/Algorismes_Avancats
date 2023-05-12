package P4.Controlador;

import P4.Main;
import P4.Model.Aresta;
import P4.Model.Graf;
import P4.Model.Node;

import java.util.*;

public class Controlador extends Thread {
    private Main main;
    private Graf graf;

    public Controlador(Main main, Graf graf) {
        this.main = main;
        this.graf = graf;
    }
    

    public void run() {
        long t1=System.nanoTime();
        getCami(graf);
        long t2 =System.nanoTime();
        System.out.println("Temps d'execució: "+ (t2-t1)+" nanosegons.");


        System.out.println("******************SOLUCIÓ******************");
        for (int i = 0; i < graf.getCami().getNodes().size(); i++) {
            System.out.println(graf.getCami().getNodes().get(i).toString());
        }
    }

    private void calcularCamiMesCurt(Node origen, Node destino, Graf graf) {
        // Inicialitzar distancies a infinit i l'origen a distancia zero
        PriorityQueue<Node> coa = new PriorityQueue<>();
        for (Node v : graf.getNodes()) {
            v.setDistancia(Integer.MAX_VALUE);
            v.setVisitat(false);
            v.setAnterior(null);
        }
        origen.setDistancia(0);
        coa.add(origen);

        while (!coa.isEmpty()) {
            if (!Main.CONTINUAR) {
                return;
            }
            // Obtenir el vertex amb la menor distancia a la coa
            Node u = coa.poll();
            u.setVisitat(true);

            // Si arribam al desti podem sortir del bucle
            if (u == destino) {
                break;
            }

            // Actualitzar les distancies entre els vertetsos adjecents
            for (Aresta a : u.getSortints()) {
                Node v = a.apunta();
                if (!v.isVisitat()) {
                    int distancia = (int) (u.getDistancia() + a.getValor());
                    if (distancia < v.getDistancia()) {
                        coa.remove(v);  // Actualizar la coa per reordenar
                        v.setDistancia(distancia);
                        v.setAnterior(u);
                        coa.add(v);
                    }
                }
            }
        }
    }

    public void getCami(Graf graf) {
        Node inici = graf.getInici();
        Node desti = graf.getDesti();

        //cridam a l'algorisme de dijkstra per el node destí i el darrer intermig que s'ha seleccionat
        calcularCamiMesCurt(graf.getIntermig(graf.getIntermigsSize() - 1), desti, graf);
        Graf camino = new Graf();
        //ficam al cami els nodes que el componen
        for (Node v = desti; v != null; v = v.getAnterior()) {
            camino.addNode(v);
        }
        boolean inter = true;

        //si hi ha mes d'un node intermig aleshores calculem els camins entre
        //els nodes, seguint l'ordre invers de introduccio
        for (int i = graf.getIntermigsSize() - 1; i >= 1; i--) {
            calcularCamiMesCurt(graf.getNodesIntermigs().get(i - 1), graf.getNodesIntermigs().get(i), graf);
            for (Node v = graf.getNodesIntermigs().get(i); v != null; v = v.getAnterior()) {
                if (inter) {
                    inter = false;
                } else {
                    camino.addNode(v);
                }
            }
            inter = true;
        }

        //finalment calculem el cami entre el primer node intermig i el node desti
        calcularCamiMesCurt(inici, graf.getNodesIntermigs().get(0), graf);
        for (Node v = graf.getNodesIntermigs().get(0); v != null; v = v.getAnterior()) {
            if (inter) {
                inter = false;
            } else {
                camino.addNode(v);
            }
        }
        //per acabar invertim el cami ja que el tenim en l'ordre invers del que
        //ens interesa
        camino.reverseNodes();

        graf.setCami(camino);
        main.comunicacio("controlador_acabat");
        main.comunicacio("Actualitzar");
    }
}
