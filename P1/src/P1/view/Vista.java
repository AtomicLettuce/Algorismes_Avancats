package P1.view;

import P1.Main;
import P1.interfaces.InterficieComunicacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Vista extends JFrame implements InterficieComunicacio, WindowListener{

    private Main main;
    private Container contingut;

    protected boolean actualitzar=true;

    public Vista(String nom, Main main){
        super(nom);
        this.main=main;
        // Inicialitzar interfície gràfica
        contingut = this.getContentPane();
        contingut.setLayout(new BorderLayout());
        PanellDibuix pd = new PanellDibuix(1000,800,main.getModel(),this);
        this.add(BorderLayout.CENTER,pd);
        addWindowListener(this);
        mostrar();
    }
    public void actualitzar(){
        actualitzar=true;
    }

    public void mostrar(){
        this.pack();
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void comunicacio(String instruccio) {
        // IMPLEMENTAR
    }
    @Override
    public void windowClosing(WindowEvent e) {
        dispose();
        main.comunicacio("Aturar");
    }


    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}


}
