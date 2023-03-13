package P2;

import P2.Vista.Vista;
import P2.Interficies.InterficieComunicacio;



public class Main implements InterficieComunicacio {


    private Vista v;


    public void inici(){
        v =new Vista("pinga",this);
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
        new Main().inici();
    }

    @Override
    public void comunicacio(String instruccio) {

    }
}