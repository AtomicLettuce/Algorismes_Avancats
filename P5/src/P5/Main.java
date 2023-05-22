package P5;


import P5.Controlador.Controlador;
import P5.Model.Model;
import P5.Vista.Vista;

import java.util.HashSet;

public class Main implements P5.Interficies.InterficieComunicacio {
    public static boolean CONTINUAR =true;
    private Vista vista;





    public static void main(String[] args) {
        System.out.println("UEP!!");
        new Main().inici();
    }
    public void inici(){
        //vista=new Vista("mondongo",this,null);
        Model m = new Model();
//        m.carregaDiccionari("catala");
//        HashSet <String> alemany = m.getDiccionari("catala");
//        for (String elementoActual : alemany) {
//            System.out.println(elementoActual);
//        }
        Controlador c =new Controlador(this,m);
        System.out.println(c.distanciaEntreDosIdiomes("prova", "prova2" ));
    //    System.out.println(c.reconeixerIdioma("i go home"));
//        double[] res = c.distanciaTotsIdiomes("catala");
//        for (int i = 0; i < res.length; i++) {
//            System.out.println(res[0]);
//        }
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