package P3.Vista;

import P3.Main;
import P3.Model.Nuvol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Vista extends JFrame implements ActionListener, WindowListener {
    private Main main;
    private PanellNuvol panellNuvol;
    private JButton play;
    private JButton stop;
    private JButton reset;
    private JButton opcions;
    private MonitorVista mv;

    public Vista(String nom, Main main, Nuvol nuvol) {
        super(nom);
        this.main = main;

        // Panell de dibuixat
        panellNuvol = new PanellNuvol(500,500, nuvol);

        this.getContentPane().setLayout(new BorderLayout());
        this.add(panellNuvol, BorderLayout.CENTER);

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

        // Zona botonera (manejador d'events)
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

        reset.setVisible(false);


        mv=new MonitorVista();
        Dibuixador dibuxador=new Dibuixador(panellNuvol,this,mv);
        dibuxador.start();

        this.add(botonera, BorderLayout.NORTH);

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
        switch (ae.getActionCommand()){
            case "play":
                // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]
                break;
            case "stop":
                dispose();
                main.comunicacio("stop");
                break;
            case "reset":
                // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]
                break;
            case "opcions":
                // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]
                break;
        }
    }

    // Per rebre notificacio de que s'ha de refrescar la pantalla
    public void actualitzar(){
        mv.notificarActualitzar();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        dispose();
        main.comunicacio("stop");
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
