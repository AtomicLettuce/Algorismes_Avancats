package P4.Controlador;
import P4.Model.Aresta;
import P4.Model.Graf;
import P4.Model.Node;

import java.util.*;

public class Controlador {

        private void calcularCaminoMasCorto(Node origen, Node destino, Graf graf) {
            // Inicializar distancias a infinito y el origen a distancia cero
            PriorityQueue<Node> cola = new PriorityQueue<>();
            for (Node v : graf.getNodes()) {
                v.setDistancia(Integer.MAX_VALUE);
                v.setVisitat(false);
                v.setAnterior(null);
            }
            origen.setDistancia(0);
            cola.add(origen);

            while (!cola.isEmpty()) {
                // Obtener el vértice con la menor distancia en la cola
                Node u = cola.poll();
                u.setVisitat(true);

                // Si llegamos al destino, podemos salir del bucle
                if (u == destino) {
                    break;
                }

                // Actualizar las distancias de los vértices adyacentes
                for (Aresta a : u.getSalientes()) {
                    Node v = a.apunta();
                    if (!v.isVisitat()) {
                        int distancia = (int) (u.getDistancia() + a.getValor());
                        if (distancia < v.getDistancia()) {
                            cola.remove(v);  // Actualizar la cola para reordenar
                            v.setDistancia(distancia);
                            v.setAnterior(u);
                            cola.add(v);
                        }
                    }
                }
            }
        }

    public Graf getCamino(Graf graf) {
        Node inici = graf.getInici();
        Node desti = graf.getDesti();
        calcularCaminoMasCorto(graf.getIntermig(graf.getIntermigsSize()-1), desti, graf);
        Graf camino = new Graf();

        for (Node v = desti; v != null; v = v.getAnterior()) {
            camino.addNode(v);
        }
        boolean inter = true;

        for(int i = graf.getIntermigsSize()-1; i>=1; i--){
            calcularCaminoMasCorto(graf.getNodesIntermigs().get(i-1), graf.getNodesIntermigs().get(i), graf);
            for (Node v = graf.getNodesIntermigs().get(i); v != null; v = v.getAnterior()) {
                if(inter){
                    inter = false;
                }else{
                    camino.addNode(v);
                }
            }
            inter = true;
        }

        calcularCaminoMasCorto(inici, graf.getNodesIntermigs().get(0), graf);
        for (Node v = graf.getNodesIntermigs().get(0); v != null; v = v.getAnterior()) {
            if(inter){
                inter = false;
            }else{
                camino.addNode(v);
            }

        }
        camino.reverseNodes();

        graf.setCami(camino);
        return camino;
    }


}
