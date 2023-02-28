package P1.view;

import P1.Main;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {

    private Main main;
    private Container contingut;

    public Vista(String nom, Main main){
        super(nom);
        this.main=main;
        // Inicialitzar interfície gràfica
        contingut = this.getContentPane();
        contingut.setLayout(new BorderLayout());
        PanellDibuix pd = new PanellDibuix(1000,800,null,null);
        this.add(BorderLayout.CENTER,pd);
        mostrar();
    }

    public void mostrar(){
        this.pack();
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

}
