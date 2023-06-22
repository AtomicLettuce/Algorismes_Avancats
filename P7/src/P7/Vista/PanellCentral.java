package P7.Vista;


import P7.Controllador.controllador;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class PanellCentral extends JPanel {

    private Vista vista;
    private String opcions;
    private static final int  GRAFIC_QT_VALORS=10;
    public PanellCentral(int width, int height, Vista vista) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.vista =vista;
        this.opcions="";
    }

    public void setOpcions(String opcions) {
        this.opcions = opcions;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        switch (opcions){
            case "Grafic":
                dibuixaGrafica(g2);
                break;
        }
    }

    private void dibuixaGrafica(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(10));
        g2.drawLine(50,0,50,getHeight()-50);
        g2.drawLine(50,getHeight()-50,getWidth(),getHeight()-50);

        g2.setStroke(new BasicStroke(1));



        int incrementX=(getWidth()-60)/GRAFIC_QT_VALORS;
        for (int i = 0; i <GRAFIC_QT_VALORS ; i++) {
            g2.drawLine(50+i*incrementX,0,50+i*incrementX,getHeight()-50);
            g2.drawString(""+i*2,50+i*incrementX,getHeight()-25);
        }
        double valors_grafic[]=new double[GRAFIC_QT_VALORS];
        for (int i = 0; i < GRAFIC_QT_VALORS; i++) {
            valors_grafic[i]=controllador.temps_aproximat(2*i);
        }

        int incrementY=(getHeight()-50)/GRAFIC_QT_VALORS;


        for (int i = 0; i < GRAFIC_QT_VALORS; i++) {
            g2.drawLine(50,i*incrementY,getWidth(),i*incrementY);
            double llegenda=(((getHeight()-50)-(i*incrementY))*valors_grafic[GRAFIC_QT_VALORS-1])/(getHeight()-50);
            String result = String.format("%.2f", llegenda);
            g2.drawString(result, 0,i*incrementY);
        }
        g2.drawString("(n)",getWidth()-20,getHeight()-10);
        g2.drawString("(s)",10,getHeight()-60);


        for (int i = 0; i < GRAFIC_QT_VALORS-1; i++) {
            int a= (int) ((Math.ceil(valors_grafic[i])*(getHeight()-50))/valors_grafic[GRAFIC_QT_VALORS-1]);
            int b= (int) ((Math.ceil(valors_grafic[i+1])*(getHeight()-50))/valors_grafic[GRAFIC_QT_VALORS-1]);

            a=(getHeight()-50)-a;
            b=(getHeight()-50)-b;


            g2.drawLine(50+(i*incrementX),a,50+((i+1)*incrementX),b);
        }



    }



}