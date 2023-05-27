package P5;


import P5.Controlador.Controlador;
import P5.Model.Model;
import P5.Vista.Vista;

import java.util.HashMap;
import java.util.HashSet;

public class Main implements P5.Interficies.InterficieComunicacio {
    public static boolean CONTINUAR =true;
    private Vista vista;





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
        c.totsAmbTots();
       // System.out.println(c.distanciaEntreDosIdiomes("catala", "castella" ));
    //    System.out.println(c.reconeixerIdioma("i go home"));
      //   HashMap<String, Double>resultats= c.distanciaTotsIdiomes("prova");
       // for (String i:m.idiomes) {
       //     System.out.println("reuyfhbs"+resultats.get(i));
      //  }
        vista=new Vista("mondongo",this,m);
        vista.actualitzar();
        System.out.println("pingala");
    }



    @Override
    public void comunicacio(String instruccio) {
        switch (instruccio) {
            case "play":
                // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]
                break;
            case "stop":
                System.out.println("Aturant...");
                CONTINUAR=false;
                vista.actualitzar();

                // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]
                break;
            case "reset":
                // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]
                break;
            case "actualitzar":
                vista.actualitzar();
                break;

        }
    }
}