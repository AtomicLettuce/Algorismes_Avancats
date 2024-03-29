package P7.Vista;

import P7.Controllador.controllador;
import P7.Main;
import P7.Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Vista extends JFrame implements ActionListener, WindowListener {
    private Main main;
    private JButton stop;
    private JButton opcions;
    private PanellCentral panellCentral;
    private MonitorVista mv;
    private Model model;

    public Vista(String nom, Main main, Model model) {
        super(nom);
        this.main = main;
        this.model=model;
        addWindowListener(this);


        this.getContentPane().setLayout(new BorderLayout());

        panellCentral = new PanellCentral(800, 800, this, model);
        this.add(panellCentral, BorderLayout.CENTER);
        // Zona botonera
        stop = new JButton(new ImageIcon("img/stop.png"));
        opcions = new JButton(new ImageIcon("img/elegirN.png"));

        // Zona botonera (identificadors)
        stop.setActionCommand("stop");
        opcions.setActionCommand("opcions");

        // Zona botonera (manejador d'esdeveniments)
        stop.addActionListener(this);
        opcions.addActionListener(this);

        // Zona botonera (motius estètics)
        stop.setBackground(Color.WHITE);
        opcions.setBackground(Color.WHITE);

        // Zona botonera (afegir-los en pantalla)
        JPanel botonera = new JPanel();
        botonera.setBackground(Color.DARK_GRAY);
        botonera.add(stop);
        botonera.add(opcions);

        //play.setVisible(false);
        //reset.setVisible(false);

        this.add(botonera, BorderLayout.NORTH);
        mv = new MonitorVista();
        Dibuixador dibuxador = new Dibuixador(panellCentral, this, mv);
        dibuxador.start();
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
            case "stop":
                dispose();
                mv.notificarSortida();
                main.comunicacio("stop");
                break;
            case "opcions":
                // demana a l'usuari amb quines opcions vol treballar i ho notifica a main
                demana_opcions();
                break;
        }
    }

    private void demana_opcions() {
        panellCentral.setOpcions("");
        String[] options = {"Mostra gràfic", "Obtenir temps aproximat per n", "Verificar primer", "Factoritzar nombre",
                "Generar claus RSA", "Xifrar RSA", "Desxifrar RSA"};

        int option = JOptionPane.showOptionDialog(this, "Què vols fer?", "Opcions de Programa", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
        switch (option) {
            case 0:
                panellCentral.setOpcions("Grafic");
                actualitzar();
                break;
            case 1:
                int n = Integer.parseInt(JOptionPane.showInputDialog(this, "N de dígits per calcular temps aproximat"));
                double temps = controllador.temps_aproximat(n);

                int year = (int) temps / (365 * 24 * 60 * 60);
                int day = (int) (temps / (24 * 60 * 60)) % 365;
                int hour = (int) (temps / (60 * 60)) % 24;
                int minute = (int) (temps / 60) % 60;
                int second = (int) temps % 60;

                String tempsFormatat = String.format("%d anys, %d dies, %d hores, %d minuts, %d segons", year, day, hour, minute, second);
                popup(tempsFormatat);
                break;
            case 2:
                String num = JOptionPane.showInputDialog(this, "Número a verificar");
                main.comunicacio("Verificar primer:" + num);
                break;
            case 3:
                String numFactoritzar = JOptionPane.showInputDialog(this, "Número a factoritzar");
                panellCentral.setOpcions("Factoritzar:" + numFactoritzar);
                actualitzar();
                main.comunicacio("Factoritzar:" + numFactoritzar);
                break;
            case 4:
                main.comunicacio("GeneraClausRSA");
                break;
            case 5:
                String clauX = JOptionPane.showInputDialog(this, "Introdueix clau");
                String clauN =JOptionPane.showInputDialog(this, "Introdueix clau PúblicaN");

                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);

                // If the user selects a file, print its name
                if (result == JFileChooser.APPROVE_OPTION) {
                    model.fitxer= fileChooser.getSelectedFile();
                }
                main.comunicacio("XifrarRSA:"+clauX+":"+clauN);
                break;
            case 6:
                String clauD = JOptionPane.showInputDialog(this, "Introdueix clau");
                String clauuN =JOptionPane.showInputDialog(this, "Introdueix clau PúblicaN");
                JFileChooser jfc = new JFileChooser();
                int resultat = jfc.showOpenDialog(null);

                // If the user selects a file, print its name
                if (resultat == JFileChooser.APPROVE_OPTION) {
                    model.fitxer= jfc.getSelectedFile();
                }
                main.comunicacio("DesxifrarRSA:"+clauD+":"+clauuN);

                break;
        }
        actualitzar();
    }

    public void popup(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    // Per rebre notificació que s'ha de refrescar la pantalla
    public void actualitzar() {
        mv.notificarActualitzar();
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
