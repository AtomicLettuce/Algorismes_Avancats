package P2.Vista;

import P2.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Vista extends JFrame implements WindowListener, ActionListener {

    private Main main;

    public Vista (String nom, Main main){
        super(nom);
        this.main = main;

        TaulesEscacs taulesEscacs = new TaulesEscacs(500,500,8);
        this.getContentPane().setLayout(new BorderLayout());
        this.add(taulesEscacs,BorderLayout.CENTER);








        this.setResizable(false);
        mostrar();
    }

    private void mostrar(){
        this.pack();
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        dispose();
        //main.comunicacio("Aturar");
    }
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}