package P5.Vista;

import P5.Model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class PanellDibuix extends JPanel {

    private Model model;
    private String opcions;

    public PanellDibuix(int width, int height, Model model) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.model = model;
        opcions = "";
    }

    public void setOpcions(String s) {
        opcions = s;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.BLACK);

        if (opcions.startsWith("Graf")) {
            ferGraf(g2);
        } else if (opcions.startsWith("Arbre")) {
            ferArbre(g2);
        } else if (opcions.startsWith("Un amb un:")) {
            unAmbUn(g2);
        } else if (opcions.startsWith("Tots amb un")) {
            totsAmbUn(g2);
        }
    }

    private void ferArbre(Graphics2D g2) {

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 14));

        // Dibuixa noms de idiomes
        int totalWidth = 0;
        for (String s : model.idiomes) {
            totalWidth += g2.getFontMetrics().stringWidth(s);
        }
        int spacing = (getWidth() - totalWidth) / (model.idiomes.length - 1);
        int x = 0;
        for (int i = 0; i < model.idiomes.length; i++) {
            g2.drawString(model.idiomes[i], x, 700);
            x += g2.getFontMetrics().stringWidth(model.idiomes[i]) + spacing;
        }

        double distancies[][] = new double[model.idiomes.length][model.idiomes.length];
        ArrayList<Fulla> fulles = new ArrayList<>();
        x = 0;
        for (int i = 0; i < model.idiomes.length; i++) {
            for (int j = 0; j < model.idiomes.length; j++) {
                if (i == j) {
                    distancies[i][j] = Double.MAX_VALUE;
                } else {
                    double dist1 = model.getResultatsTotsAmbTots().get(model.idiomes[i]).get(model.idiomes[j]);
                    double dist2 = model.getResultatsTotsAmbTots().get(model.idiomes[j]).get(model.idiomes[i]);
                    double distReal = Math.sqrt((Math.pow(dist1, 2) + Math.pow(dist2, 2)));
                    distancies[i][j] = distReal;
                }
            }
            fulles.add(new Fulla(i, x, 700, distancies[i]));
            x += g2.getFontMetrics().stringWidth(model.idiomes[i]) + spacing;
        }

        int idioma1 = 0;
        int idioma2 = 0;
        double minim = Double.MAX_VALUE;
        Color colors[] = {Color.BLACK, Color.BLUE, Color.RED, Color.MAGENTA, Color.CYAN, Color.YELLOW};
        boolean acabat = false;
        while (!acabat) {
            // agafar mínim
            minim = Double.MAX_VALUE;
            for (int i = 0; i < fulles.size(); i++) {
                for (int j = 0; j < fulles.size(); j++) {
                    if (fulles.get(i).distancies[j] < minim) {
                        minim = fulles.get(i).distancies[j];
                        idioma2 = j;
                        idioma1 = i;
                    }
                }
            }
            if (minim != Double.MAX_VALUE) {
                uneix(fulles.get(idioma1).x, fulles.get(idioma1).y, fulles.get(idioma2).x, fulles.get(idioma2).y, g2, fulles.get(idioma1), fulles.get(idioma2));
            }
            if (ajustarFulles(fulles, idioma1, idioma2)) {
                acabat = true;
            }
        }
    }

    private boolean ajustarFulles(ArrayList<Fulla> fulles, int idioma1, int idioma2) {
        for (int i = 0; i < fulles.size(); i++) {
            if (fulles.get(i).distancies[idioma1] < fulles.get(i).distancies[idioma2]) {
                fulles.get(i).distancies[idioma2] = Double.MAX_VALUE;
            } else {
                fulles.get(i).distancies[idioma1] = Double.MAX_VALUE;
            }
        }
        for (int i = 0; i < fulles.size(); i++) {
            double firstElement = fulles.get(i).distancies[0];
            boolean rowEqual = true;
            for (int j = 1; j < fulles.get(i).distancies.length; j++) {
                if (fulles.get(i).distancies[j] != firstElement) {
                    rowEqual = false;
                    break;
                }
            }
            if (rowEqual) {
                return true;
            }
        }
        return false;
    }


    private void uneix(int x1, int y1, int x2, int y2, Graphics2D g2, Fulla fulla1, Fulla fulla2) {
        int y = Integer.min(y1, y2);
        y = y - 50;
        g2.drawLine(x1, y1, x1, y);
        g2.drawLine(x2, y2, x2, y);
        g2.drawLine(x1, y, x2, y);


        fulla1.y = y;
        fulla2.y = y;
        fulla1.distancies[fulla2.id] = Double.MAX_VALUE;
        fulla2.distancies[fulla1.id] = Double.MAX_VALUE;
        fulla1.x = (fulla1.x + fulla2.x) / 2;
        fulla2.x = (fulla1.x + fulla2.x) / 2;


    }

    private void ferGraf(Graphics2D g2) {
        int x = 50;
        int y = 50;
        int cercleSize = 100;
        int coordsx[] = {70, 200, 400, 90, 650, 50, 200, 650, 350, 500};
        int coordsy[] = {70, 200, 600, 400, 300, 750, 650, 750, 400, 70};


        g2.setColor(Color.BLACK);
        // Dibuixa arestes i pesos
        for (int i = 0; i < model.idiomes.length; i++) {
            for (int j = 0; j < model.idiomes.length; j++) {
                if (j != i) {
                    g2.drawLine(coordsx[i], coordsy[i], coordsx[j], coordsy[j]);
                    double dist1 = model.getResultatsTotsAmbTots().get(model.idiomes[i]).get(model.idiomes[j]);
                    double dist2 = model.getResultatsTotsAmbTots().get(model.idiomes[j]).get(model.idiomes[i]);
                    double distReal = Math.sqrt((Math.pow(dist1, 2) + Math.pow(dist2, 2)));
                    String text = "" + String.format("%.3f", distReal);

                    g2.setColor(Color.WHITE);
                    int boxWidth = g2.getFontMetrics().stringWidth(text) + 10;
                    int boxHeight = g2.getFontMetrics().getHeight() + 10;
                    int boxX = (coordsx[i] + coordsx[j] - boxWidth) / 2;
                    int boxY = (coordsy[i] + coordsy[j] - boxHeight) / 2;
                    g2.fillRect(boxX, boxY, boxWidth, boxHeight);
                    g2.setColor(Color.BLACK);
                    int textX = (coordsx[i] + coordsx[j]) / 2 - g2.getFontMetrics().stringWidth(text) / 2;
                    int textY = (coordsy[i] + coordsy[j]) / 2 + g2.getFontMetrics().getAscent() / 2;
                    g2.drawString(text, textX, textY);
                }
            }
        }

        // Dibuixa nodes
        for (int i = 0; i < model.idiomes.length; i++) {
            g2.setColor(Color.BLACK);
            g2.fillOval(coordsx[i] - cercleSize / 2, coordsy[i] - cercleSize / 2, cercleSize, cercleSize);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 14));
            g2.drawString(model.idiomes[i], coordsx[i] - 40, coordsy[i]);
        }


    }

    private void totsAmbUn(Graphics2D g2) {
        String principal = opcions.split(":")[1];

        g2.setFont((new Font("Arial", Font.BOLD, 20)));

        for (int i = 0; i < model.idiomes.length; i++) {
            int x1 = getWidth() / 6;
            int y1 = ((i * getHeight()) / model.idiomes.length) + 20 + 20;
            int x2 = 4 * getWidth() / 5;
            int y2 = ((i * getHeight()) / model.idiomes.length) + 20 + 20;
            g2.drawString(principal, getWidth() / 6, ((i * getHeight()) / model.idiomes.length) + 20);
            g2.drawString(model.idiomes[i], 4 * getWidth() / 5, ((i * getHeight()) / model.idiomes.length) + 20);
            g2.drawLine(x1, y1, x2, y2);
            g2.drawString("" + model.getResultats().get(model.idiomes[i]), (x1 + x2) / 2, 20 + ((y1 + y2) / 2));


        }


    }

    private void unAmbUn(Graphics2D g2) {
        String tokens[] = opcions.split(":");


        g2.setFont((new Font("Arial", Font.BOLD, 20)));
        g2.drawString(tokens[1], getWidth() / 6, getHeight() / 2);
        g2.drawString(tokens[2], 4 * getWidth() / 5, getHeight() / 2);

        int x1 = getWidth() / 6;
        int y1 = 20 + (getHeight() / 2);
        int x2 = 4 * getWidth() / 5;
        int y2 = 20 + (getHeight() / 2);
        g2.drawLine(x1, y1, x2, y2);
        g2.drawString("" + model.distancia, (x1 + x2) / 2, 20 + ((y1 + y2) / 2));


    }

    private class Fulla {
        int id;
        int x;
        int y;
        double distancies[];

        public Fulla(int id, int x, int y, double[] distancies) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.distancies = distancies;
        }
    }


}
