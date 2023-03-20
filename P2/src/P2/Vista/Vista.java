package P2.Vista;

import P2.Main;
import P2.Model.Tauler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Vista extends JFrame implements WindowListener, ActionListener, MouseListener {
private JButton play;
private JButton stop;
private JButton dimensio;
private JButton insertpeca;
private String peca="";
private TaulesEscacs taulesEscacs;
private Tauler tauler;

    private Main main;

    public Vista (String nom, Main main, Tauler tauler){
        super(nom);
        this.main = main;
        this.tauler=tauler;

        taulesEscacs = new TaulesEscacs(500,500, tauler);
        taulesEscacs.addMouseListener(this);
        this.getContentPane().setLayout(new BorderLayout());
        this.add(taulesEscacs,BorderLayout.CENTER);

        play = new JButton(new ImageIcon("img/play.png"));
        stop=new JButton(new ImageIcon("img/stop.png"));
        dimensio=new JButton(new ImageIcon("img/elegirN.png"));
        insertpeca=new JButton(new ImageIcon("img/elegirpeca.png"));


        play.setActionCommand("play");
        stop.setActionCommand("stop");
        dimensio.setActionCommand("dimensio");
        insertpeca.setActionCommand("insertpeca");


        play.addActionListener(this);
        stop.addActionListener(this);
        dimensio.addActionListener(this);
        insertpeca.addActionListener(this);

        play.setBackground(Color.WHITE);
        stop.setBackground(Color.WHITE);
        dimensio.setBackground(Color.WHITE);
        insertpeca.setBackground(Color.WHITE);

        JPanel botonera=new JPanel();
        botonera.setBackground(Color.YELLOW);
        botonera.add(play);
        botonera.add(stop);
        botonera.add(dimensio);
        botonera.add(insertpeca);


        this.add(botonera, BorderLayout.NORTH);

        this.setResizable(false);
        mostrar();
    }

    private void mostrar(){
        this.pack();
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    private void llegir_dimensio(){
        try {
            String str = JOptionPane.showInputDialog("Enter");
            System.out.println("dimensió: "+Integer.parseInt(str));
            main.comunicacio("dimensio:"+str);

        }catch (NumberFormatException nfe){
            JOptionPane.showMessageDialog(this, ":( Fica un número sencer major que 0");
        }

    }

    private void insertPeca(){
        String[] opcions ={"Alfil","Cavall","Rei", "Reina", "Super Cavall","Torre"};


        int n = JOptionPane.showOptionDialog(this,
                "Quina peça vols introduir",
                "Elegeix una peça",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcions,
                opcions[1]);

        System.out.println("L'usuari ha agafat la opció " + opcions[n]);
        if(n==-1){
            peca="";

        }else{
            peca=opcions[n];
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "play":
                main.comunicacio("play");
                break;
            case "stop":
                break;
            case "dimensio":
                llegir_dimensio();
                break;
            case "insertpeca":
                insertPeca();
                // Falta fer que l'usuari elegeixi on vol ficar la peça
                // Falta fer que enviï aquesta informació a alguna part

                break;
        }

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(peca.equals("")){
            return;
        }

        int x=e.getX()/(taulesEscacs.getWidth()/tauler.getDim());
        int y=e.getY()/(taulesEscacs.getHeight()/tauler.getDim());

        // Enviar ordre de crear peça
        peca="";

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
