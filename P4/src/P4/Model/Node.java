package P4.Model;

import java.util.ArrayList;

public class Node implements Comparable<Node>{
    private String etiqueta;
    private ArrayList<Aresta> sortints;
    private int X;
    private int Y;
    private boolean visitat;
    private int distancia;
    private Node anterior;

    public Node(String et, int x, int y) {
        etiqueta = et;
        X = x;
        Y = y;
        sortints = new ArrayList <Aresta> ();
    }

    public Node(String et){
        this.sortints = new ArrayList<Aresta>();
        etiqueta = et;
    }

    public void insertaAresta(Aresta a) {
        sortints.add(a);
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

    public int getNArestes() {
        return sortints.size();
    }

    public ArrayList<Aresta> getSortints() {
        return sortints;
    }

    public Aresta getArista(int i) {
        return sortints.get(i);
    }

    public int getDistancia(){return this.distancia;}

    public void setDistancia(int distancia){this.distancia = distancia;}

    public boolean isVisitat() {
        return visitat;
    }
    public void setVisitat(boolean visitat) {
        this.visitat = visitat;
    }
    public Node getAnterior() {
        return anterior;
    }
    public void setAnterior(Node anterior) {
        this.anterior = anterior;
    }

    @Override
    public int compareTo(Node o) {
        if(this.getDistancia()> o.getDistancia()){
            return 1;
        } else if (this.getDistancia()< o.getDistancia()) {
            return -1;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "etiqueta='" + etiqueta + '\'' +
                '}';
    }
}
