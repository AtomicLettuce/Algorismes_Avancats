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
    public String toString(){
        return etiqueta+" X: "+X+" Y: "+Y;
    }
    public void ponArista(Aresta a) {
        salientes.add(a);
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getNAristas() {
        return salientes.size();
    }

    public Aresta getArista(int i) {
        return salientes.get(i);
    }
}
