package P1;

import Controlador.Controlador;
import P1.interfaces.InterficieComunicacio;
import P1.model.Model;
import P1.view.Vista;

public class Main implements InterficieComunicacio {

    private Model model = new Model();
    private Controlador controlador = new Controlador(model);
    private Vista vista;


    public static void main(String[] args) {
        new Main().inici();
    }

    public void inici() {
        System.out.println("Hola!");
        vista=new Vista("pinga", this);
        //model.comunicacio("100000000");
        //controlador.comunicacio("oN");
    }

    @Override
    public void comunicacio(String instruccio) {
        switch (instruccio){
            case "Aturar":
                System.out.println("Aturant...");
                // Aturar tots els fils i aturar el programa
                break;
            case "Actualitzar":
                // Tornar a pintar la GUI
                break;

        }
    }
}