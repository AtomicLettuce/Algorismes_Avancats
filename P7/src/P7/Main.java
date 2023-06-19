package P7;

import P7.Interficies.InterficieComunicacio;
import P7.Model.Model;
import P7.Vista.Vista;
import P7.Controllador.controllador;

import java.math.BigInteger;

public class Main implements InterficieComunicacio {

    private Vista vista;
    private controllador controllador;
    private Model model;

    public static void main(String[] args) {
        new Main().inici();
    }

    //DEIX AIXÒ AQUÍ DE FORMA PROVISIONAL. LLAVORS HO ARREGLARÉ I HO POS ON TOCA
    double  func(int n){
        double i =0.000368357066*Math.pow(n,6)-0.0198795751*Math.pow(n,5)+0.43510339*Math.pow(n,4)-4.92310776*Math.pow(n,3)+30.2531138*Math.pow(n,2)-95.3486723*n+119.892874;
        return i;
    }

    private void inici(){
        System.out.println("UEP!");

        vista = new Vista("mondongo",this);
        model = new Model();
        controllador = new controllador(vista, model);
        BigInteger[] numeros = {
                new BigInteger("4661"),
                new BigInteger("453007"),
                new BigInteger("41136371"),
                new BigInteger("5900338729"),
                new BigInteger("648773765743"),
                new BigInteger("47081630774621"),
                new BigInteger("5674018715441897")
        };
        System.out.println(func(16));
        /*for(BigInteger numero : numeros ){
            unNumero(numero);
            model.vaciarHashMap();
        }*/

    }

    public void unNumero(BigInteger numero){
        System.out.println("Longitud del número a factorizar: "+numero.toString().length()+" cifras.");
        Long t1 = System.nanoTime();
        controllador.inici(numero);
        Long t2 = System.nanoTime();
        System.out.println("Voy a factorizar " + numero);
        model.printNumeros();
        System.out.println("He tardado "+(t2-t1)+" nanosec");
        System.out.println("He tardado "+((double)(t2-t1)/1000000000)+" sec");
    }

    @Override
    public void comunicacio(String instruccio) {

    }
}