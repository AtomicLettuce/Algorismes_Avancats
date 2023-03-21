package P2;

import P2.Controlador.Controlador;
import P2.Model.*;
import P2.Vista.Vista;
import P2.Interficies.InterficieComunicacio;

import java.util.ArrayList;
import java.util.Calendar;


public class Main implements InterficieComunicacio {



    public static boolean CONTINUAR=true;
    private Vista v;
    private Controlador controlador;
    private Tauler tablero;

    public static void main(String[] args) {


        System.out.println("UEP!!");
        new Main().inici();
    }
    public void inici(){
        tablero = new Tauler(5);
        v =new Vista("pinga",this, tablero);
        ArrayList<Peca> peces = new ArrayList<>();
        tablero.afegirPeca(new Cavall(1,0));
        controlador = new Controlador(this,tablero);

    }


    @Override
    public void comunicacio(String instruccio) {
        switch (instruccio){
            case "stop":
                System.out.println("Aturant...");
                // Aturar tots els fils i aturar el programa
                CONTINUAR=false;
                v.actualitzar();
                break;
            case "Actualitzar":
                // Tornar a pintar la GUI
                v.actualitzar();
                break;
            case "play":
                // Envia l'ordre de començar
                controlador.start();
                break;
            case "reset":
                controlador = new Controlador(this,tablero);
                break;

        }if (instruccio.startsWith("dimensio:")){
            // Format "dimensio:'n'"
            int n = Integer.parseInt(instruccio.split(":")[1]);
            // Ajustar valor de n perquè se canviï
            // model.setdim(n)
        }





    }
}