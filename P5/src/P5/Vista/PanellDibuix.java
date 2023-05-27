package P5.Vista;

import P5.Model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

public class PanellDibuix extends JPanel {

    private Model model;
    public PanellDibuix(int width, int height, Model model) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.model = model;
    }


    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 =(Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0,0,getWidth(),getHeight());
        g2.setColor(Color.BLACK);

        /*Dupla resultats[]=new Dupla[model.idiomes.length];
        for (int i = 0; i < model.idiomes.length; i++) {
            resultats[i]=new Dupla(model.idiomes[i],+model.getResultats().get(model.idiomes[i]));
        }
        Arrays.sort(resultats);

        g2.setFont(g2.getFont().deriveFont(20f));
        int separacio=(getWidth()-40)/ resultats.length;
        for (int i = 0; i < resultats.length; i++) {
            g2.drawString(resultats[i].nom,20+(separacio*i),getHeight()-60);
        }

        g2.drawLine(20,getHeight()-60,20,200);*/






    }



    private class Dupla implements Comparable<Dupla>{
        public String nom;
        public double valor;

        public Dupla(String nom,double valor){
            this.nom=nom;
            this.valor=valor;
        }

        @Override
        public int compareTo(Dupla dupla) {
            if(this.valor>dupla.valor){
                return 1;
            }else if (this.valor< dupla.valor){
                return -1;
            }else{
                return 0;
            }
        }
    }





}
