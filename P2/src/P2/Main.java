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
        v =new Vista("pinga",this);
        tablero = new Tauler(5);
        tablero.afegirPeca(new Cavall(0,0));
        controlador = new Controlador(this,tablero);
        //controlador.start();

        comunicacio("play");

    }


    @Override
    public void comunicacio(String instruccio) {
        switch (instruccio){
            case "stop":
                System.out.println("Aturant...");
                // Aturar tots els fils i aturar el programa
                CONTINUAR=false;
                break;
            case "Actualitzar":
                // Tornar a pintar vista
                break;
            case "play":
                // Envia l'ordre de començar
                controlador.start();
                break;

        }if (instruccio.startsWith("dimensio:")){
            int n = Integer.parseInt(instruccio.split(":")[1]);
            // Ajustar valor de n perquè se canviï
            // model.setdim(n)

        }






    }
}