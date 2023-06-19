package P7;

import P7.Interficies.InterficieComunicacio;
import P7.Vista.Vista;
import P7.Controllador.controllador;

import java.math.BigInteger;

public class Main implements InterficieComunicacio {

    private Vista vista;
    private controllador controllador;

    public static void main(String[] args) {
        new Main().inici();
    }

    private void inici(){
        System.out.println("UEP!");

        vista = new Vista("mondongo",this);
        controllador = new controllador();
        controllador.inici(new BigInteger("825899332"));
    }

    @Override
    public void comunicacio(String instruccio) {

    }
}