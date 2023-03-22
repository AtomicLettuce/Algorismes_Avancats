package P2.Vista;

import P2.Main;
import P2.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Vista extends JFrame implements WindowListener, ActionListener, MouseListener {
    private JButton play;
    private JButton stop;
    private JButton reset;
    private JButton dimensio;
    private JButton insertpeca;
    private String pecaEscollida = "";
    private TaulesEscacs taulesEscacs;
    private Tauler tauler;

    private Main main;
    private MonitorVista mv;
    public Vista(String nom, Main main, Tauler tauler) {
        super(nom);
        this.main = main;
        this.tauler = tauler;

        // Panell de dibuixat
        taulesEscacs = new TaulesEscacs(500, 500, tauler);
        taulesEscacs.addMouseListener(this);
        this.getContentPane().setLayout(new BorderLayout());
        this.add(taulesEscacs, BorderLayout.CENTER);

        // Zona botonera
        play = new JButton(new ImageIcon("img/play.png"));
        stop = new JButton(new ImageIcon("img/stop.png"));
        reset = new JButton(new ImageIcon("img/reset.png"));
        dimensio = new JButton(new ImageIcon("img/elegirN.png"));
        insertpeca = new JButton(new ImageIcon("img/elegirpeca.png"));

        // Zona botonera (identificadors)
        play.setActionCommand("play");
        stop.setActionCommand("stop");
        reset.setActionCommand("reset");
        dimensio.setActionCommand("dimensio");
        insertpeca.setActionCommand("insertpeca");

        // Zona botonera (manejador d'events)
        play.addActionListener(this);
        stop.addActionListener(this);
        reset.addActionListener(this);
        dimensio.addActionListener(this);
        insertpeca.addActionListener(this);

        // Zona botonera (motius estètics)
        play.setBackground(Color.WHITE);
        stop.setBackground(Color.WHITE);
        reset.setBackground(Color.WHITE);
        dimensio.setBackground(Color.WHITE);
        insertpeca.setBackground(Color.WHITE);

        // Zona botonera (afegir-los en pantalla)
        JPanel botonera = new JPanel();
        botonera.setBackground(Color.DARK_GRAY);
        botonera.add(play);
        botonera.add(reset);
        botonera.add(stop);
        botonera.add(dimensio);
        botonera.add(insertpeca);

        reset.setVisible(false);


        mv=new MonitorVista();
        Dibuixador dibuxador=new Dibuixador(taulesEscacs,this,mv);
        dibuxador.start();

        this.add(botonera, BorderLayout.NORTH);

        this.setResizable(false);
        mostrar();
    }

    // Per rebre notificacio de que s'ha de refrescar la pantalla
    public void actualitzar(){
        mv.notificarActualitzar();
    }

    public void setTauler(Tauler tauler){
        this.tauler = tauler;
        taulesEscacs.setTauler(tauler);
    }

    private void mostrar() {
        this.pack();
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void notificarNoSolucio(){
        JOptionPane.showMessageDialog(this, ":( No s'ha trobat solució");
    }


    // Mètode emprat per llegir input de l'usuari i que aquest estableixi la dimensióm del tauler que vol
    private void llegir_dimensio() {
        try {
            String str = JOptionPane.showInputDialog("Enter");
            System.out.println("dimensió: " + Integer.parseInt(str));
            main.comunicacio("dimensio:" + str);

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, ":( Fica un número sencer major que 0");
        }

    }

    private void insertPeca() {
        String[] opcions = {"Alfil", "Cavall", "Rei", "Reina", "Super Cavall", "Torre"};

        int n = JOptionPane.showOptionDialog(this,
                "Quina peça vols introduir",
                "Elegeix una peça",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcions,
                opcions[1]);

        System.out.println("L'usuari ha agafat la opció " + opcions[n]);
        if (n == -1) {
            pecaEscollida = "";

        } else {
            pecaEscollida = opcions[n];
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "play":
                play.setVisible(false);
                reset.setVisible(true);
                dimensio.setVisible(false);
                insertpeca.setVisible(false);
                main.comunicacio("play");
                break;
            case "stop":
                main.comunicacio("stop");
                dispose();
                break;
            case "reset":
                play.setVisible(true);
                reset.setVisible(false);
                dimensio.setVisible(true);
                insertpeca.setVisible(true);
                main.comunicacio("reset");
                break;
            case "dimensio":
                llegir_dimensio();
                break;
            case "insertpeca":
                insertPeca();
                break;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Si l'usuari no ha sel·leccionat peça, no feim res
        if (pecaEscollida.equals("")) {
            return;
        }
        // Obtenir casella on vol ficar peça
        int x = e.getX() / (taulesEscacs.getWidth() / tauler.getDim());
        int y = e.getY() / (taulesEscacs.getHeight() / tauler.getDim());

        // Enviar ordre de crear peça
        Peca p=null;
        switch (pecaEscollida) {
            case "Alfil":
                p = new Alfil(tauler.getDim(),x,y);
                break;
            case "Cavall":
                p =new Cavall(x,y);
                break;
            case "Rei":
                p =new Rei(x,y);
                break;
            case "Reina":
                p =new Reina(tauler.getDim(),x,y);
                break;
            case "Super Cavall":
                p =new SuperCavall(x,y);
                break;
            case "Torre":
                p =new Torre(tauler.getDim(),x,y);
                break;
        }
        System.out.println("L'usuari fica un "+pecaEscollida+" a la posició X= "+x+" Y= "+y);


        tauler.afegirPeca(p);
        mv.notificarActualitzar();
        pecaEscollida = "";
    }


    @Override
    public void windowClosing(WindowEvent e) {
        dispose();
        main.comunicacio("stop");
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

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
