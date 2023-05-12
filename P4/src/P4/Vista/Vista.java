package P4.Vista;

import P4.Main;
import P4.Model.Graf;
import P4.Model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class Vista extends JFrame implements ActionListener, WindowListener, MouseListener {
    private Main main;
    private JButton play;
    private JButton stop;
    private JButton reset;
    private JButton opcions;
    private MonitorVista mv;
    private Graf graf;
    private boolean seleccionant_node_inici;
    private boolean seleccionant_node_desti;
    private boolean seleccionant_node_intermig;
private PanellMapa panellMapa;
    public Vista(String nom, Main main, Graf graf) {
        super(nom);
        this.main = main;
        this.graf=graf;
        addWindowListener(this);

        panellMapa=new PanellMapa(800,800,graf);

        this.getContentPane().setLayout(new BorderLayout());
        this.add(panellMapa,BorderLayout.CENTER);
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
        Dibuixador dibuxador = new Dibuixador(panellMapa, this, mv);
        dibuxador.start();
        panellMapa.addMouseListener(this);
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


    private void demana_opcions() {
        seleccionant_node_inici =false;
        seleccionant_node_desti =false;
        seleccionant_node_intermig=false;

        // Create the JOptionPane
        Object[] options = {"Sel·leccionar fitxer", "Sel·leccionar node inici", "Sel·leccionar node final", "Sel·leccionar node intermig","OK"};
        JCheckBox checkBox = new JCheckBox("Pintar mapa?",panellMapa.isDibuixa_mapa());

        Object[] message = {"Què vols fer?", checkBox};
        int option = JOptionPane.showOptionDialog(this, message, "Opcions de Programa", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

        // Update the boolean variable if the checkbox is checked
        panellMapa.setDibuixa_mapa(checkBox.isSelected());
        if(option<0||option==4){
            System.out.println("Dibuixa mapa: " + checkBox.isSelected());
        }else{
            // Print the selected option and the checkbox state
            System.out.println("Selected option: " + options[option]);
            System.out.println("Dibuixa mapa: " + checkBox.isSelected());

            switch (option){
                case 0:
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showOpenDialog(null);

                    // If the user selects a file, print its name
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        System.out.println("Selected file: " + selectedFile.getName());
                    }
                    break;
                case 1:
                    seleccionant_node_inici =true;
                    break;
                case 2:
                    seleccionant_node_desti =true;
                    break;
                case 3:
                    seleccionant_node_intermig=true;
                    break;

            }
        }
        actualitzar();



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
        System.out.println(""+seleccionant_node_inici+seleccionant_node_desti+seleccionant_node_intermig);
        if(seleccionant_node_inici||seleccionant_node_desti||seleccionant_node_intermig){
            System.out.println(" x: "+mouseEvent.getX()+" y: "+mouseEvent.getY());
            int x=mouseEvent.getX();
            int y=mouseEvent.getY();
            double closest_dist=Integer.MAX_VALUE;
            ArrayList<Node> nodes=graf.getNodes();
            Node node;
            Node elegit=null;
            int elegit_index=0;
            for (int i = 0; i < nodes.size(); i++) {
                node=nodes.get(i);
                double distance = Math.sqrt(Math.pow(node.getX() - x, 2) + Math.pow(node.getY() - y, 2));
                if(distance<closest_dist){
                    elegit=nodes.get(i);
                    closest_dist=distance;
                    elegit_index=i;
                }
            }
            if(seleccionant_node_desti){
                main.comunicacio("Desti:"+elegit_index);
            }else if(seleccionant_node_inici){
                main.comunicacio("Origen:"+elegit_index);
            }else{
                main.comunicacio("Intermig:"+elegit_index);
                System.out.println("pingala");
            }

            seleccionant_node_inici=false;
            seleccionant_node_desti=false;
            seleccionant_node_intermig=false;
            System.out.println(elegit);
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
