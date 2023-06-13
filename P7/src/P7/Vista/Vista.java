package P7.Vista;

import P7.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Vista extends JFrame implements ActionListener, WindowListener {
    private Main main;
    private JButton play;
    private JButton stop;
    private JButton reset;
    private JButton opcions;

    public Vista(String nom, Main main) {
        super(nom);
        this.main = main;
        addWindowListener(this);

        // Zona botonera
        play = new JButton(new ImageIcon("img/play.png"));
        stop = new JButton(new ImageIcon("img/stop.png"));
        reset = new JButton(new ImageIcon("img/reset.png"));
        opcions = new JButton(new ImageIcon("img/elegirN.png"));

        // Zona botonera (identificadors)
        play.setActionCommand("play");
        stop.setActionCommand("stop");
        reset.setActionCommand("rest");
        opcions.setActionCommand("opcions");

        // Zona botonera (manejador d'esdeveniments)
        play.addActionListener(this);
        stop.addActionListener(this);
        reset.addActionListener(this);
        opcions.addActionListener(this);

        // Zona botonera (motius estètics)
        play.setBackground(Color.WHITE);
        stop.setBackground(Color.WHITE);
        reset.setBackground(Color.WHITE);
        opcions.setBackground(Color.WHITE);

        // Zona botonera (afegir-los en pantalla)
        JPanel botonera = new JPanel();
        botonera.setBackground(Color.DARK_GRAY);
        botonera.add(play);
        botonera.add(reset);
        botonera.add(stop);
        botonera.add(opcions);

        //play.setVisible(false);
        //reset.setVisible(false);

        this.add(botonera, BorderLayout.NORTH);
        /*mv = new MonitorVista();
        Dibuixador dibuxador = new Dibuixador(panellPuzzle, this, mv);
        dibuxador.start();
        panellPuzzle.addMouseListener(this);*/
        this.setResizable(false);
        mostrar();
    }

    private void mostrar() {
        this.pack();
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    // Manejador d'events de la zona botonera
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "play":
                main.comunicacio("play");
                break;
            case "stop":
                dispose();
                main.comunicacio("stop");
                break;
            case "reset":
                main.comunicacio("actualitzar");
                break;
            case "opcions":
                // demana a l'usuari amb quines opcions vol treballar i ho notifica a main
                demana_opcions();
                break;
        }
    }
    public void popup(String s){
        JOptionPane.showMessageDialog(this,s);
    }

    private void demana_opcions() {
        String[] options = {"Canviar dimensió (n)", "Crear Puzzle (usuari)", "Generar puzzle (automàtic full random)",
                "Generar puzzle (automàtic fàcil)", "Set Foto", "Esborra Foto","Set Heurística"};

        int option = JOptionPane.showOptionDialog(this, "Què vols fer?", "Opcions de Programa", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
        switch (option) {

        }
    }

    // Per rebre notificació que s'ha de refrescar la pantalla
    public void actualitzar() {
        //mv.notificarActualitzar();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        main.comunicacio("stop");
        //mv.notificarSortida();
        //dispose();
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {
    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }

}
