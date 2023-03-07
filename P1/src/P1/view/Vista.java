package P1.view;

import P1.Main;
import P1.interfaces.InterficieComunicacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Vista extends JFrame implements  WindowListener, ActionListener {
    private JButton arrancar;
    private JButton aturar;

    private Main main;
    private Container contingut;
    private Monitor_Vista mv;

    public Vista(String nom, Main main){
        super(nom);
        this.main=main;
        // Inicialitzar interfície gràfica
        contingut = this.getContentPane();
        contingut.setLayout(new BorderLayout());
        // Monitor per poder controlar quan s'ha de refrescar la interfície
        mv=new Monitor_Vista();
        // Àrea de dibuixat
        PanellDibuix pd = new PanellDibuix(1000,900,main.getModel(),this,mv);
        this.add(BorderLayout.CENTER,pd);

        // Botó d'inici de programa
        arrancar=new JButton("Arrancar");
        arrancar.addActionListener(this);

        aturar = new JButton("Aturar");
        aturar.addActionListener(this);
        aturar.setVisible(false);


        JPanel botonera =new JPanel();
        botonera.add(arrancar);
        botonera.add(aturar);



        this.add(botonera,BorderLayout.NORTH);
        this.add(new JPanel(),BorderLayout.EAST);
        this.add(new JPanel(),BorderLayout.WEST);
        this.add(new JPanel(),BorderLayout.SOUTH);
        addWindowListener(this);
        mostrar();
    }
    // Per rebre notificacio de que s'ha de refrescar la pantalla
    public void actualitzar(){
        mv.notificarActualitzar();
    }


    // Feim visible la pantalla
    public void mostrar(){
        this.pack();
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    // Per fer que quan es tanqui la pantalla, s'enviï l'ordre d'aturada a tots els altres fils
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Arrancar":
                main.comunicacio("Arrancar");
                arrancar.setVisible(false);
                aturar.setVisible(true);
                break;
            case "Aturar":
                dispose();
                main.comunicacio("Aturar");
                break;
        }
    }
}
