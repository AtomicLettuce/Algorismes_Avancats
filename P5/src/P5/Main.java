package P5;


import P5.Controlador.Controlador;
import P5.Model.Model;

import java.util.HashSet;

public class Main implements P5.Interficies.InterficieComunicacio {





    public static void main(String[] args) {
        System.out.println("UEP!!");
        new Main().inici();
    }
    public void inici(){
        Model m = new Model();
//        m.carregaDiccionari("catala");
//        HashSet <String> alemany = m.getDiccionari("catala");
//        for (String elementoActual : alemany) {
//            System.out.println(elementoActual);
//        }
        Controlador c =new Controlador(this,m);
        System.out.println(c.distanciaEntreDosIdiomes("catala", "italia" ));
      //  System.out.println(c.reconeixerIdioma("coche tenedor sfsfsfsd"));
//        double[] res = c.distanciaTotsIdiomes("catala");
//        for (int i = 0; i < res.length; i++) {
//            System.out.println(res[0]);
//        }
    }


    @Override
    public void comunicacio(String instruccio) {
        switch (instruccio) {
            case "stop":
                System.out.println("Aturant...");
        }
    }
}