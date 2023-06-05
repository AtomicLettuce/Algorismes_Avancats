package P6.Vista;

import P6.Main;
import P6.Model.Estat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Vista extends JFrame implements ActionListener, WindowListener, MouseListener {
    private Main main;
    private JButton play;
    private JButton stop;
    private JButton reset;
    private JButton opcions;
    private MonitorVista mv;
    private PanellPuzzle panellPuzzle;
    private Estat model;
    public Vista(String nom, Main main, Estat model) {
        super(nom);
        this.main = main;
        this.model=model;
        addWindowListener(this);

        panellPuzzle=new PanellPuzzle(800,800, model);

        this.getContentPane().setLayout(new BorderLayout());
        this.add(panellPuzzle, BorderLayout.CENTER);
        // Zona botonera
        play = new JButton(new ImageIcon("img/play.png"));
        stop = new JButton(new ImageIcon("img/stop.png"));
        reset = new JButton(new ImageIcon("img/reset.png"));
        opcions = new JButton(new ImageIcon("img/elegirN.png"));

        // Zona botonera (identificadors)
        play.setActionCommand("play");
        stop.setActionCommand("stop");
        reset.setActionCommand("reset");
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
        mv = new MonitorVista();
        Dibuixador dibuxador = new Dibuixador(panellPuzzle, this, mv);
        dibuxador.start();
        panellPuzzle.addMouseListener(this);
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
                main.comunicacio("reset");
                break;
            case "opcions":
                // demana a l'usuari amb quines opcions vol treballar i ho notifica a main
                demana_opcions();
                break;
        }
    }



    private void demana_opcions() {

    }

    // Per rebre notificació que s'ha de refrescar la pantalla
    public void actualitzar() {
        mv.notificarActualitzar();
    }

    public void notificarSortida() {
        mv.notificarSortida();
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println(" x: "+mouseEvent.getX()+" y: "+mouseEvent.getY());
    }
    @Override
    public void windowClosing(WindowEvent e) {
        main.comunicacio("stop");
        mv.notificarSortida();
        dispose();
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

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
