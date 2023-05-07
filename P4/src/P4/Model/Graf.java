package P4.Model;

import java.util.ArrayList;

public class Graf {
    private ArrayList<Node> nodes;
    private ArrayList<Aresta> arestes;
    private String tipo = "";

    public void ponNodo(String e, int x, int y) {
        if (!existe(e)) {
            nodes.add(new Node(e, x, y));
        } else {
            System.out.println("Etiqueta ja existent. " +e);
        }
    }

    private boolean existe(String e) {
        boolean res = false;
        int i = 0;
        while ((!res) && (i < nodes.size())) {
            if (e.contentEquals((nodes.get(i)).getEtiqueta())) {
                res = true;
            }
            i++;
        }
        return res;
    }

    public void ponArista(String eti, String etf, double v) {
        Aresta ar = new Aresta(getNode(etf),v);
        getNode(eti).ponArista(ar);
    }
    public void ponTipo(String t) {
        tipo = t;
    }
    private Node getNode(String e) {
        boolean res = false;
        Node n = null;
        int i = 0;
        while ((!res) && (i < nodes.size())) {
            if (e.contentEquals((nodes.get(i)).getEtiqueta())) {
                res = true;
                n = nodes.get(i);
            }
            i++;
        }
        if (n == null) {
            System.out.println("Node Inexistent");
        }
        return n;
    }
    public Node getNode(int i) {
        Node res = null;
        if (i < nodes.size()) {
            res = nodes.get(i);
        } else {
            System.out.println("Node Inexistent "+i+".");
        }
        return res;
    }


}
