package P4.Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Graf {
    private ArrayList<Node> nodes;
    private BufferedImage mapa;
    private Node inici;
    private Node desti;
    private ArrayList<Node> nodesIntermigs;
    private Graf cami;
    private boolean es_dirigit;

    public Graf(){
            nodes = new ArrayList<>();
            nodesIntermigs=new ArrayList<>();
    }

    public void addNodeIntermig(Node i){
        nodesIntermigs.add(i);
    }

    public int getIntermigsSize(){
        return nodesIntermigs.size();
    }

    public Node getIntermig(int i){
        return nodesIntermigs.get(i);
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

    public void insertaNode(String e, int x, int y) {
        if (!existeixNode(e)) {
            nodes.add(new Node(e, x, y));
        } else {
            System.out.println("Etiqueta ja existent. " + e);
        }
    }

    private boolean existeixNode(String e) {
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

    public void defineixMapa(String str) {
        try {
            mapa = ImageIO.read(new File(str));
        } catch (IOException ioe) {
            System.out.println(ioe.toString());
        }
    }

    public void insertaAresta(String inicial, String seguent, double cost) {
        Aresta ar = new Aresta(getNode(seguent), cost);
        getNode(inicial).insertaAresta(ar);
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

    public void addNode(Node node) {
        nodes.add(node);
    }
    public void reverseNodes(){
        Collections.reverse(this.nodes);
    }
    public ArrayList<Node> getNodesIntermigs() {
        return nodesIntermigs;
    }

    public void setCami(Graf cami) {
        this.cami = cami;
    }
    public Graf getCami() {
        return cami;
    }
}
