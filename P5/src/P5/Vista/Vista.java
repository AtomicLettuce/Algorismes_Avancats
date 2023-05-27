package P5.Vista;

import P5.Main;
import P5.Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Vista extends JFrame implements WindowListener, ActionListener {
    private Main main;
    private Model graf;
    private PanellDibuix panellMapa;
    private JButton play;
    private JButton stop;
    private JButton reset;
    private JButton opcions;
    private MonitorVista mv;

    private Dibuixador dibuixador;


    public Vista(String nom, Main main, Model graf) {
        super(nom);
        this.main = main;
        this.graf = graf;
        addWindowListener(this);


        panellMapa = new PanellDibuix(800, 800, graf);

        this.getContentPane().setLayout(new BorderLayout());
        this.add(panellMapa, BorderLayout.CENTER);
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
        //botonera.add(reset);
        botonera.add(stop);
        //botonera.add(opcions);

        //play.setVisible(false);
        //reset.setVisible(false);

        this.add(botonera, BorderLayout.NORTH);
        mv = new MonitorVista();
        dibuixador = new Dibuixador(panellMapa, this, mv);
        dibuixador.start();
        this.setResizable(false);
        mostrar();
    }

    private void mostrar() {
        this.pack();
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void actualitzar() {
        mv.notificarActualitzar();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "play":
                demana_opcions();
                main.comunicacio("play");
                break;
            case "stop":
                dispose();
                main.comunicacio("stop");
                break;
            case "reset":
                main.comunicacio("reset");
                break;
        }
    }

    public void demana_opcions() {

        String[] options = {"Un amb un", "Tots amb un", "Arbre filolèxic", "Graf de distàncies", "Reconeixedor lingüístic", "OK"};

        int option = JOptionPane.showOptionDialog(this, "Què vols fer?", "Opcions de Programa", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
        if (option == 5||option<0) {
            return;
        } else if (option==0) {
            String[] options2 = {"alemany", "angles", "castella", "catala", "checho", "euskera", "frances", "italia", "portugues", "rumano"};
            int option2 = JOptionPane.showOptionDialog(this, "Quin?", "Opcions de Programa", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, null);
            int option3 = JOptionPane.showOptionDialog(this, "Quin altre?", "Opcions de Programa", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, null);
            panellMapa.setOpcions("Un amb un:"+options2[option2]+":"+options2[option3]);
            main.comunicacio("play:Un amb un" + options[option] + ":" + options2[option2]+":"+options2[option3]);

        } else if (option == 1) {
            String[] options2 = {"alemany", "angles", "castella", "catala", "checho", "euskera", "frances", "italia", "portugues", "rumano"};
            int option2 = JOptionPane.showOptionDialog(this, "Quin?", "Opcions de Programa", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, null);
            panellMapa.setOpcions(options[option] + ":" + options2[option2]);
            main.comunicacio("play:" + options[option] + ":" + options2[option2]);
        }else if(option ==4){
            TextDialog dialog=new TextDialog(this);
            String enteredText= dialog.enteredText;
            main.comunicacio("play:Reconeixedor:"+enteredText);
        }
        else {
            panellMapa.setOpcions(options[option]);
            main.comunicacio("play:" + options[option]);
        }

    }

    public void popup(String s){
        JOptionPane.showMessageDialog(this,s);
    }


    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        dispose();
        main.comunicacio("stop");

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

    public class TextDialog extends JDialog {

        private JTextField textField;
        private JButton okButton;
        private JButton cancelButton;
        private String enteredText;

        public TextDialog(JFrame parentFrame) {
            super(parentFrame, "Enter some text", true);

            // Create text field
            textField = new JTextField(20);

            // Create OK button
            okButton = new JButton("OK");
            okButton.addActionListener(e -> {
                enteredText = textField.getText();
                dispose();
            });

            // Create cancel button
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> dispose());

            // Add components to dialog
            JPanel panel = new JPanel();
            panel.add(textField);
            panel.add(okButton);
            panel.add(cancelButton);
            getContentPane().add(panel);

            // Set dialog properties
            pack();
            setLocationRelativeTo(parentFrame);
            setResizable(false);
            setVisible(true);
        }

        public String getEnteredText() {
            return enteredText;
        }
    }
}
