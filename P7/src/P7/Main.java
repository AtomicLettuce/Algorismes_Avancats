package P7;

import P7.Interficies.InterficieComunicacio;
import P7.Vista.Vista;

public class Main implements InterficieComunicacio {

    private Vista vista;


    public static void main(String[] args) {
        new Main().inici();
    }

    private void inici(){
        System.out.println("UEP!");

        vista = new Vista("mondongo",this);
    }

    @Override
    public void comunicacio(String instruccio) {

    }
}