package P5;


import P5.Controlador.Controlador;
import P5.Model.Model;
import P5.Vista.Vista;
import mesurament.Mesurament;

import java.util.HashMap;
import java.util.HashSet;

public class Main implements P5.Interficies.InterficieComunicacio {
    public static boolean CONTINUAR =true;
    private Vista vista;
    private Model m;
    private Controlador c;
    public static void main(String[] args) {
        new Mesurament().mesura();
        new Main().inici();
    }
    public void inici(){
        m = new Model();
        c =new Controlador(this,m);
        vista=new Vista("P5: Programació Dinàmica",this,m);
        vista.actualitzar();
    }



    @Override
    public void comunicacio(String instruccio) {
        switch (instruccio) {
            case "stop":
                System.out.println("Aturant...");
                CONTINUAR=false;
                vista.actualitzar();
                break;
            case "actualitzar":
                vista.actualitzar();
                break;
        }if (instruccio.startsWith("play:Arbre")||instruccio.startsWith("play:Graf")){
            System.out.println(instruccio);
            long t1=System.nanoTime();
            c.totsAmbTots();
            long t2=System.nanoTime();
            vista.popup("Temps: "+(t2-t1)+" nanosegons.");
            vista.actualitzar();
        } else if (instruccio.startsWith("play:Un amb un")) {
            String tokens[] = instruccio.split(":");
            long t1=System.nanoTime();
            c.distanciaEntreDosIdiomes(tokens[2],tokens[3]);
            long t2=System.nanoTime();
            vista.popup("Temps: "+(t2-t1)+" nanosegons.");
            vista.actualitzar();
        } else if (instruccio.startsWith("play:Tots amb un")) {
            String tokens[] = instruccio.split(":");
            long t1=System.nanoTime();
            c.distanciaTotsIdiomes(tokens[2]);
            long t2=System.nanoTime();
            vista.popup("Temps: "+(t2-t1)+" nanosegons.");
            vista.actualitzar();
        } else if (instruccio.startsWith("play:Reconeixedor")) {
            String tokens[] = instruccio.split(":");
            long t1=System.nanoTime();
            String res =(c.reconeixerIdioma(tokens[2]));
            long t2=System.nanoTime();
            vista.popup("Temps: "+(t2-t1)+" nanosegons.");
            vista.popup(res);
        }
    }
}