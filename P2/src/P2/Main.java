package P2;

import P2.Controlador.Controlador;
import P2.Model.*;
import P2.Vista.Vista;
import P2.Interficies.InterficieComunicacio;

import java.util.ArrayList;
import java.util.Calendar;


public class Main implements InterficieComunicacio {


    private Vista v;
    private Controlador controlador;
    private Tablero tablero;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        new Main().inici();
    }
    public void inici(){
        v =new Vista("pinga",this);
        tablero = new Tablero(5);
        ArrayList<Peca> peces = new ArrayList<>();


        peces.add(new Torre(5,0,4));
        controlador = new Controlador(tablero,peces);
        controlador.start();
        System.out.println(tablero.toString());
    }


    @Override
    public void comunicacio(String instruccio) {

    }
}