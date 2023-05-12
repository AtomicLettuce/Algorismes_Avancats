package P4.Vista;



import P4.Model.Aresta;
import P4.Model.Graf;
import P4.Model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class PanellMapa extends JPanel {
    private Graf graf;
    private final int NODE_RADIUS=7;
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

        //Dibuixam node inici
        if(graf.getInici()!=null) {
            g2.setColor(Color.BLUE);
            g2.fillOval(graf.getInici().getX()-NODE_RADIUS, graf.getInici().getY()-NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS);
        }

        //Dibuixam node desti
        if(graf.getDesti()!=null) {
            g2.setColor(Color.MAGENTA);
            g2.fillOval(graf.getDesti().getX()-NODE_RADIUS, graf.getDesti().getY()-NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS);
        }


        g2.setColor(Color.RED);
        ArrayList<Aresta> arestes=graf.getArestes();
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.get(i).getNAristas(); j++) {
                Aresta aresta=nodes.get(i).getArista(j);
                //drawArrow(g2,nodes.get(i).getX(),nodes.get(i).getY(),aresta.apunta().getX(),aresta.apunta().getY());
                g2.drawLine(nodes.get(i).getX(),nodes.get(i).getY(),aresta.apunta().getX(),aresta.apunta().getY());
            }

        }
    }

    public void drawArrow(Graphics2D g2, int x1, int y1, int x2, int y2) {
        // create a line from (x1, y1) to (x2, y2)
        Line2D line = new Line2D.Double(x1, y1, x2, y2);

        // create a path for the arrowhead
        Path2D arrowhead = new Path2D.Double();
        arrowhead.moveTo(-5, 10);
        arrowhead.lineTo(0, 0);
        arrowhead.lineTo(5, 10);

        // calculate the angle between the line and the x-axis
        double angle = Math.atan2(y2 - y1, x2 - x1);

        // rotate the arrowhead to the correct angle
        arrowhead.transform(java.awt.geom.AffineTransform.getRotateInstance(angle));

        // translate the arrowhead to the endpoint of the line
        arrowhead.transform(java.awt.geom.AffineTransform.getTranslateInstance(x2, y2));

        // draw the line and arrowhead
        g2.draw(line);
        g2.fill(arrowhead);
    }
}

