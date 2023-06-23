package P7.Vista;


import P7.Controllador.controllador;
import P7.Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;

public class PanellCentral extends JPanel {

    private Vista vista;
    private String opcions;
    private Model model;
    private static final int GRAFIC_QT_VALORS = 10;

    public PanellCentral(int width, int height, Vista vista, Model model) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.vista = vista;
        this.opcions = "";
        this.model = model;
    }

    public void setOpcions(String opcions) {
        this.opcions = opcions;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        switch (opcions) {
            case "Grafic":
                dibuixaGrafica(g2);
                break;
            case "Factoritzar":
                mostraFactoritzacio(g2);
                break;
        }
        if (opcions.startsWith("Factoritzar")) {
            mostraFactoritzacio(g2);
        }
    }

    private void mostraFactoritzacio(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        Font currentFont = g2.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
        g2.setFont(newFont);

        String numero = opcions.split(":")[1];
        String text = "Factors de " + numero + " :";
        Font font = g2.getFont();
        int x = getWidth() / 2;
        int y = getHeight() / 10;

        FontRenderContext frc = g2.getFontRenderContext();
        GlyphVector gv = font.createGlyphVector(frc, text);
        Rectangle2D bounds = gv.getVisualBounds();
        int textWidth = (int) bounds.getWidth();
        int textHeight = (int) bounds.getHeight();
        int centerX = x - textWidth / 2;
        int centerY = y - textHeight / 2;
        g2.drawString(text, centerX, centerY + font.getSize());


        x = getWidth() / 2;
        y = 2 * getHeight() / 10;

        frc = g2.getFontRenderContext();
        gv = font.createGlyphVector(frc, text);
        bounds = gv.getVisualBounds();
        textWidth = (int) bounds.getWidth();
        textHeight = (int) bounds.getHeight();
        centerX = x - textWidth / 2;
        centerY = y - textHeight / 2;

        FontMetrics metrics = g2.getFontMetrics(font);

        text = model.printNumeros();
        for (String line : text.split("\n")) {
            g2.drawString(line, x, y);
            y += metrics.getHeight();
        }


    }

    private void dibuixaGrafica(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(10));
        g2.drawLine(50, 0, 50, getHeight() - 50);
        g2.drawLine(50, getHeight() - 50, getWidth(), getHeight() - 50);

        g2.setStroke(new BasicStroke(1));


        int incrementX = (getWidth() - 60) / GRAFIC_QT_VALORS;
        for (int i = 0; i < GRAFIC_QT_VALORS; i++) {
            g2.drawLine(50 + i * incrementX, 0, 50 + i * incrementX, getHeight() - 50);
            g2.drawString("" + i * 2, 50 + i * incrementX, getHeight() - 25);
        }
        double valors_grafic[] = new double[GRAFIC_QT_VALORS];
        for (int i = 0; i < GRAFIC_QT_VALORS; i++) {
            valors_grafic[i] = controllador.temps_aproximat(2 * i);
        }

        int incrementY = (getHeight() - 50) / GRAFIC_QT_VALORS;


        for (int i = 0; i < GRAFIC_QT_VALORS; i++) {
            g2.drawLine(50, i * incrementY, getWidth(), i * incrementY);
            double llegenda = (((getHeight() - 50) - (i * incrementY)) * valors_grafic[GRAFIC_QT_VALORS - 1]) / (getHeight() - 50);
            String result = String.format("%.2f", llegenda);
            g2.drawString(result, 0, i * incrementY);
        }
        g2.drawString("(n)", getWidth() - 20, getHeight() - 10);
        g2.drawString("(s)", 10, getHeight() - 60);


        for (int i = 0; i < GRAFIC_QT_VALORS - 1; i++) {
            int a = (int) ((Math.ceil(valors_grafic[i]) * (getHeight() - 50)) / valors_grafic[GRAFIC_QT_VALORS - 1]);
            int b = (int) ((Math.ceil(valors_grafic[i + 1]) * (getHeight() - 50)) / valors_grafic[GRAFIC_QT_VALORS - 1]);

            a = (getHeight() - 50) - a;
            b = (getHeight() - 50) - b;


            g2.drawLine(50 + (i * incrementX), a, 50 + ((i + 1) * incrementX), b);
        }


    }


}