package P4.Model;

import java.util.ArrayList;

public class Node {
    private String etiqueta;
    private ArrayList<Aresta> salientes;
    private int X;
    private int Y;

    public Node(String et, int x, int y) {
        etiqueta = et;
        X = x;
        Y = y;
        salientes = new ArrayList <Aresta> ();
    }

    protected void ponArista(Aresta a) {
        salientes.add(a);
    }

    protected String getEtiqueta() {
        return etiqueta;
    }

    protected int getX() {
        return X;
    }

    protected int getY() {
        return Y;
    }

    protected int getNAristas() {
        return salientes.size();
    }

    protected Aresta getArista(int i) {
        return salientes.get(i);
    }
}
