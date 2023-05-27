package P5.Vista;

import P5.Model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

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
            //ferArbre();
        } else if (opcions.startsWith("Un amb un:")) {
            unAmbUn(g2);
        } else if (opcions.startsWith("Tots amb un")) {
            totsAmbUn(g2);
        }
    }

    private void ferGraf(Graphics2D g2){
        

    }

    private void totsAmbUn(Graphics2D g2){
        String principal = opcions.split(":")[1];

        g2.setFont((new Font("Arial",Font.BOLD,20)));

        for (int i = 0; i <model.idiomes.length ; i++) {
            int x1=getWidth()/6;
            int y1=((i*getHeight())/model.idiomes.length)+20+20;
            int x2=4*getWidth()/5;
            int y2=((i*getHeight())/model.idiomes.length)+20+20;
            g2.drawString(principal,getWidth()/6,((i*getHeight())/model.idiomes.length)+20);
            g2.drawString(model.idiomes[i],4*getWidth()/5,((i*getHeight())/model.idiomes.length)+20);
            g2.drawLine(x1,y1,x2,y2);
            g2.drawString(""+model.getResultats().get(model.idiomes[i]),(x1+x2)/2,20+((y1+y2)/2));


        }





    }
    private void unAmbUn(Graphics2D g2) {
        String tokens[] = opcions.split(":");


        g2.setFont((new Font("Arial",Font.BOLD,20)));
        g2.drawString(tokens[1],getWidth()/6,getHeight()/2);
        g2.drawString(tokens[2],4*getWidth()/5,getHeight()/2);

        int x1=getWidth()/6;
        int y1=20+(getHeight()/2);
        int x2=4*getWidth()/5;
        int y2=20+(getHeight()/2);
        g2.drawLine(x1,y1,x2,y2);
        g2.drawString(""+model.distancia,(x1+x2)/2,20+((y1+y2)/2));


    }


}
