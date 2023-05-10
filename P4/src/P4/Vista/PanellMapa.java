package P4.Vista;



import P4.Model.Aresta;
import P4.Model.Graf;
import P4.Model.Node;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanellMapa extends JPanel {
    private Graf graf;
    private final int NODE_RADIUS=5;
    private boolean dibuixa_mapa;

    public PanellMapa(int width, int height, Graf g) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.graf=g;
        this.dibuixa_mapa=true;
    }

    protected boolean isDibuixa_mapa() {
        return dibuixa_mapa;
    }

    protected void setDibuixa_mapa(boolean dibuixa_mapa) {
        this.dibuixa_mapa = dibuixa_mapa;
        System.out.println("sidgbu"+dibuixa_mapa);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2= (Graphics2D)g;

        g2.setColor(Color.WHITE);
        g2.fillRect(0,0,getHeight(),getWidth());

        // Dibuixam mapa
        if(dibuixa_mapa) {
            g2.drawImage(graf.getMapa(), 0, 0, getWidth(), getHeight(), null);
        }

        // Dibuixam nodes
        g2.setColor(Color.BLACK);
        ArrayList<Node> nodes =graf.getNodes();
        for (int i = 0; i < nodes.size(); i++) {
            Node node=nodes.get(i);
            g2.fillOval(node.getX()-NODE_RADIUS, node.getY()-NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS);
        }
        g2.setColor(Color.RED);
        ArrayList<Aresta> arestes=graf.getArestes();
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.get(i).getNAristas(); j++) {
                Aresta aresta=nodes.get(i).getArista(j);
                g2.drawLine(nodes.get(i).getX(),nodes.get(i).getY(),aresta.apunta().getX(),aresta.apunta().getY());
            }

        }

    }


}

