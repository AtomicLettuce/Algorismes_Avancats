package P4.Vista;


import P4.Model.Aresta;
import P4.Model.Graf;
import P4.Model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class PanellMapa extends JPanel {
    private Graf graf;
    private final int NODE_RADIUS = 7;
    private boolean dibuixa_mapa;

    public PanellMapa(int width, int height, Graf g) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.graf = g;
        this.dibuixa_mapa = true;
    }

    protected boolean isDibuixa_mapa() {
        return dibuixa_mapa;
    }

    protected void setDibuixa_mapa(boolean dibuixa_mapa) {
        this.dibuixa_mapa = dibuixa_mapa;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getHeight(), getWidth());

        // Dibuixam mapa
        if (dibuixa_mapa) {
            g2.drawImage(graf.getMapa(), 0, 0, getWidth(), getHeight(), null);
        }

        // Dibuixam nodes
        g2.setColor(Color.BLACK);
        ArrayList<Node> nodes = graf.getNodes();
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            g2.fillOval(node.getX() - NODE_RADIUS, node.getY() - NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS);
        }

        //Dibuixam node inici
        if (graf.getInici() != null) {
            g2.setColor(Color.BLUE);
            g2.fillOval(graf.getInici().getX() - NODE_RADIUS, graf.getInici().getY() - NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS);
        }

        //Dibuixam node desti
        if (graf.getDesti() != null) {
            g2.setColor(Color.MAGENTA);
            g2.fillOval(graf.getDesti().getX() - NODE_RADIUS, graf.getDesti().getY() - NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS);
        }

        // Dibuixam nodes intermitjos
        g2.setColor(Color.ORANGE);
        for (int i = 0; i < graf.getIntermigsSize(); i++) {
            g2.fillOval(graf.getIntermig(i).getX() - NODE_RADIUS, graf.getIntermig(i).getY() - NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS);
        }



        // Dibuixa arestes + costs de les arestes
        g2.setColor(Color.RED);
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.get(i).getNAristas(); j++) {
                Aresta aresta = nodes.get(i).getArista(j);
                int x1 = nodes.get(i).getX();
                int x2 = aresta.apunta().getX();
                int y1 = nodes.get(i).getY();
                int y2 = aresta.apunta().getY();
                g2.drawLine(x1, y1, x2, y2);
                int centerX = (x1 + x2) / 2;
                int centerY = (y1 + y2) / 2;
                // draw the box with text at the midpoint of the line
                g2.setFont(new Font("Arial", Font.BOLD, 9));
                int textWidth = g2.getFontMetrics().stringWidth(""+aresta.getValor());
                int textHeight = g2.getFontMetrics().getHeight();
                int boxWidth = textWidth + 10;
                int boxHeight = textHeight + 10;
                int boxX = centerX - boxWidth / 2;
                int boxY = centerY - boxHeight / 2;
                Rectangle2D box = new Rectangle2D.Double(boxX, boxY, boxWidth, boxHeight);
                g2.setColor(Color.BLACK);
                g2.fill(box);
                g2.setColor(Color.WHITE);
                g2.draw(box);
                int textX = centerX - textWidth / 2;
                int textY = centerY + textHeight / 2;
                g2.drawString(""+aresta.getValor(), textX, textY);
                g2.setColor(Color.BLACK);
            }
        }
    }

}

