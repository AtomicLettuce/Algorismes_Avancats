package P1.view;

import P1.model.Model;

import javax.swing.*;
import java.awt.*;

public class BarraProgres extends JPanel {
    private Model mod;
    private Vista vis;
    private Monitor_Vista mv;

    public BarraProgres(int xdim, int ydim, Model mod, Vista vis, Monitor_Vista mv){
        this.setPreferredSize(new Dimension(xdim, ydim));
        this.mod=mod;
        this.vis=vis;
        this.mv=mv;
    }

    public void paint(Graphics gr){
        gr.setColor(Color.GRAY);
        gr.fillRect(0,0,this.getWidth(),this.getHeight());
        gr.setColor(Color.GREEN);
        int height=getHeight();
        int width=getWidth();
        Float[] matriu_temps=mod.getMatriu_temps()[0];
        int max=0;
        for(int i=0;i<matriu_temps.length;i++){
            if(matriu_temps[i]==null){
                max=i;
                break;
            }
        }

        int percentatge= (max*100)/matriu_temps.length;



        gr.fillRoundRect(20,height/6,percentatge*(width-40)/100 +20,(4*height)/6,50,30);
        gr.setColor(Color.BLACK);
        gr.drawString(percentatge+" %",25,25);


    }
    public void repaint(){
        if(this.getGraphics()!=null){
            paint(this.getGraphics());
        }
    }





}
