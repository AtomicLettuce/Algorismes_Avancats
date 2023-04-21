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
    private Nuvol nuvol;

    public Vista(String nom, Main main, Nuvol nuvol) {
        super(nom);
        this.main = main;
        addWindowListener(this);

        this.nuvol=nuvol;
        // Panell de dibuixat
        panellNuvol = new PanellNuvol(500, 500, nuvol);

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

        play.setVisible(false);
        reset.setVisible(false);


        mv = new MonitorVista();
        Dibuixador dibuxador = new Dibuixador(panellNuvol, this, mv);
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

    public void controladorAcaba(){
        reset.setVisible(true);
    }


    // Manejador d'events de la zona botonera
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "play":
                play.setVisible(false);
                main.comunicacio("play");
                break;
            case "stop":
                dispose();
                main.comunicacio("stop");
                break;
            case "reset":
                reset.setVisible(false);
                main.comunicacio("reset");
                opcions.setVisible(true);
                break;
            case "opcions":
                // demana a l'usuari amb quines opcions vol treballar i ho notifica a main
                demana_opcions();
                break;
        }
    }

    public void setNuvol(Nuvol nuvol) {
        this.nuvol = nuvol;
        panellNuvol.setNuvol(nuvol);
    }

    private void demana_opcions() {
        int n;
        // Demana quantitat de punts
        try {
            String str = JOptionPane.showInputDialog(this, "Enter");
            n = Integer.parseInt(str);
            System.out.println("Quantitat de punts: " + n);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, ":( Fica un número sencer major que 0");
            return;
        }

        // Demana quin algorisme vol emprar
        String[] algorismes = {"n^2", "nlog n"};
        int a = JOptionPane.showOptionDialog(this, "Quin algorisme vols emprar", "Elegeix-ne un",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, algorismes, algorismes[1]);
        System.out.println("L'usuari ha agafat la opció " + algorismes[a]);
        // Si no n'agafa cap, cancel·la
        if (a == -1) {
            return;
        }

        // Demana quina distribuicó aleatòria vol emprar
        String[] distribucions = {"Equiprobable", "Gausiana"};
        int d = JOptionPane.showOptionDialog(this, "Quina distribucio vols emprar?", "Elegeix-ne una",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, distribucions, distribucions[1]);
        System.out.println("L'usuari ha agafat la opció " + distribucions[a]);
        // Si no n'agafa cap, cancel·la
        if (d == -1) {
            return;
        }
        //format: opcions: 'n' 'a' 'd'
        main.comunicacio("opcions: " + n + " " + a + " " + d);
        play.setVisible(true);
        opcions.setVisible(false);


    }

    // Per rebre notificacio de que s'ha de refrescar la pantalla
    public void actualitzar() {
        mv.notificarActualitzar();
    }

    public void notificarSortida() {
        mv.notificarSortida();
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
}
