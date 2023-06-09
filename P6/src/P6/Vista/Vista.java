package P6.Vista;

import P6.Main;
import P6.Model.Estat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Vista extends JFrame implements ActionListener, WindowListener, MouseListener {
    private Main main;
    private JButton play;
    private JButton stop;
    private JButton next;
    private JButton blitz;
    private JButton opcions;
    private MonitorVista mv;
    private PanellPuzzle panellPuzzle;
    private Estat model;
    private boolean fentPuzzle;
    private int nouPuzzle[][];

    public Vista(String nom, Main main, Estat model) {
        super(nom);
        fentPuzzle = false;
        this.main = main;
        this.model = model;
        addWindowListener(this);

        panellPuzzle = new PanellPuzzle(800, 800, model, this);

        this.getContentPane().setLayout(new BorderLayout());
        this.add(panellPuzzle, BorderLayout.CENTER);
        // Zona botonera
        play = new JButton(new ImageIcon("img/play.png"));
        stop = new JButton(new ImageIcon("img/stop.png"));
        next = new JButton(new ImageIcon("img/next.png"));
        blitz = new JButton(new ImageIcon("img/blitz.jpg"));
        opcions = new JButton(new ImageIcon("img/elegirN.png"));

        // Zona botonera (identificadors)
        play.setActionCommand("play");
        stop.setActionCommand("stop");
        next.setActionCommand("next");
        blitz.setActionCommand("blitz");
        opcions.setActionCommand("opcions");

        // Zona botonera (manejador d'esdeveniments)
        play.addActionListener(this);
        stop.addActionListener(this);
        next.addActionListener(this);
        blitz.addActionListener(this);
        opcions.addActionListener(this);

        // Zona botonera (motius estètics)
        play.setBackground(Color.WHITE);
        stop.setBackground(Color.WHITE);
        next.setBackground(Color.WHITE);
        blitz.setBackground(Color.WHITE);
        opcions.setBackground(Color.WHITE);

        // Zona botonera (afegir-los en pantalla)
        JPanel botonera = new JPanel();
        botonera.setBackground(Color.DARK_GRAY);
        botonera.add(play);
        botonera.add(next);
        botonera.add(blitz);
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

    public void setModel(Estat model) {
        this.model = model;
        panellPuzzle.setModel(model);
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
            case "next":
                main.comunicacio("actualitzar");
                break;
            case "opcions":
                // demana a l'usuari amb quines opcions vol treballar i ho notifica a main
                demana_opcions();
                break;
            case "blitz":
                activaBlitz();
                break;
        }
    }

    public void desactivaBlitz() {
        mv.desactivaBlitz();
    }

    public void activaBlitz() {
        mv.activaBlitz();
    }

    private void demana_opcions() {
        String[] options = {"Canviar dimensió (n)", "Crear Puzzle (usuari)", "Generar puzzle (automàtic full random)",
                "Generar puzzle (automàtic fàcil)", "Set Foto", "Esborra Foto", "OK"};

        int option = JOptionPane.showOptionDialog(this, "Què vols fer?", "Opcions de Programa", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
        switch (option) {
            case 0:
                String str = JOptionPane.showInputDialog(this, "Enter");
                int n = Integer.parseInt(str);
                System.out.println("Dimensió (n) escollit: " + n);
                main.comunicacio("dimensio:" + n);
                break;
            case 1:
                nouPuzzle = new int[model.getDimensioPuzzle()][model.getDimensioPuzzle()];
                for (int i = 0; i < nouPuzzle.length; i++) {
                    for (int j = 0; j < nouPuzzle.length; j++) {
                        nouPuzzle[i][j] = -1;
                    }
                }
                model.setPuzzle(nouPuzzle);
                actualitzar();
                fentPuzzle = true;
                break;
            case 2:
                main.comunicacio("generar");
                break;
            case 3:
                main.comunicacio("generarFacil");
                break;
            case 4:
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);

                // If the user selects a file, print its name
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        Image imatge = ImageIO.read(selectedFile);
                        imatge = imatge.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
                        model.imatgepuzzle = new BufferedImage(imatge.getWidth(null), imatge.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                        Graphics g = model.imatgepuzzle.getGraphics();
                        g.drawImage(imatge, 0, 0, null);
                    } catch (IOException ioe) {
                        System.out.println(ioe.toString());
                    }
                    actualitzar();
                }
                break;
            case 5:
                model.imatgepuzzle = null;
                actualitzar();
                break;

        }
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
        if (fentPuzzle) {
            int x = mouseEvent.getX() / (panellPuzzle.getWidth() / model.getDimensioPuzzle());
            int y = mouseEvent.getY() / (panellPuzzle.getHeight() / model.getDimensioPuzzle());

            int max = Integer.MIN_VALUE;
            for (int i = 0; i < nouPuzzle.length; i++) {
                for (int j = 0; j < nouPuzzle[i].length; j++) {
                    if (nouPuzzle[i][j] > max) {
                        max = nouPuzzle[i][j];
                    }
                }
            }
            if (nouPuzzle[y][x] == -1) {
                nouPuzzle[y][x] = max + 1;
                if (max + 1 == ((model.getDimensioPuzzle() * model.getDimensioPuzzle()) - 1)) {
                    fentPuzzle = false;
                    if (!model.esResoluble(nouPuzzle)) {
                        JOptionPane.showMessageDialog(this, "ALERTA, AQUEST PUZZLE NO TÉ SOL·LUCIÓ", "AVÍS", JOptionPane.WARNING_MESSAGE);
                    }

                }
                model.setPuzzle(nouPuzzle);
                actualitzar();
            }

        }


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
