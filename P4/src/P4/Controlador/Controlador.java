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
        getCami(graf);
        System.out.println("******************SOLUCIÓ******************");
        for(int i = 0; i<graf.getCami().getNodes().size(); i++){
            System.out.println(graf.getCami().getNodes().get(i).toString());
        }
    }

    private void calcularCamiMesCurt(Node origen, Node destino, Graf graf) {
        // Inicializar distancias a infinito y el origen a distancia cero
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
            // Obtener el vértice con la menor distancia en la cola
            Node u = coa.poll();
            u.setVisitat(true);

            // Si llegamos al destino, podemos salir del bucle
            if (u == destino) {
                break;
            }

            // Actualizar las distancias de los vértices adyacentes
            for (Aresta a : u.getSortints()) {
                Node v = a.apunta();
                if (!v.isVisitat()) {
                    int distancia = (int) (u.getDistancia() + a.getValor());
                    if (distancia < v.getDistancia()) {
                        coa.remove(v);  // Actualizar la cola para reordenar
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
        calcularCamiMesCurt(graf.getIntermig(graf.getIntermigsSize() - 1), desti, graf);
        Graf camino = new Graf();

        for (Node v = desti; v != null; v = v.getAnterior()) {
            camino.addNode(v);
        }
        boolean inter = true;

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

        calcularCamiMesCurt(inici, graf.getNodesIntermigs().get(0), graf);
        for (Node v = graf.getNodesIntermigs().get(0); v != null; v = v.getAnterior()) {
            if (inter) {
                inter = false;
            } else {
                camino.addNode(v);
            }

        }
        camino.reverseNodes();

        graf.setCami(camino);
        main.comunicacio("controlador_acabat");
        main.comunicacio("Actualitzar");
    }


}
