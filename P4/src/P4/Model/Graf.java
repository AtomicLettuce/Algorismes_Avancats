package P4.Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Graf {
    private ArrayList<Node> nodes;
    private ArrayList<Aresta> arestes;
    private String tipo = "";
    private BufferedImage mapa;

    private Node inici;
    private Node desti;
    private Graf cami;
    private boolean es_dirigit;


    public Graf(){
            nodes = new ArrayList<>();
            arestes = new ArrayList<>();
            tipo = "nodirigido";
    }

    public void setInici(Node inici) {
        this.inici = inici;
    }

    public void setDesti(Node desti) {
        this.desti = desti;
    }

    public Node getInici() {
        return inici;
    }

    public Node getDesti() {
        return desti;
    }

    public boolean isEs_dirigit() {
        return es_dirigit;
    }
    public void setEs_dirigit(boolean es_dirigit){
        this.es_dirigit=es_dirigit;
    }

    public BufferedImage getMapa() {
        return mapa;
    }

    public void ponNodo(String e, int x, int y) {
        if (!existe(e)) {
            nodes.add(new Node(e, x, y));
        } else {
            System.out.println("Etiqueta ja existent. " + e);
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

    public void ponMapa(String str) {
        try {
            mapa = ImageIO.read(new File(str));
        } catch (IOException ioe) {
            System.out.println(ioe.toString());
        }
    }

    public void ponArista(String eti, String etf, double v) {
        Aresta ar = new Aresta(getNode(etf), v);
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
            System.out.println("Node Inexistent " + i + ".");
        }
        return res;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public ArrayList<Aresta> getArestes() {
        return arestes;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }
    public void reverseNodes(){
        Collections.reverse(this.nodes);
    }
}
