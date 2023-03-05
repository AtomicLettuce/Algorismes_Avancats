package P1;

import P1.controlador.Controlador;
import P1.interfaces.InterficieComunicacio;
import P1.model.Model;
import P1.view.Vista;

public class Main implements InterficieComunicacio {
    private Model model = new Model(this);
    private Controlador controlador = new Controlador(this, model);
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

        // Format: "Inicialitzar_Temps 'rows' 'columns'"
        if(instruccio.startsWith("Inicialitzar_Temps")){
            String params[]=instruccio.split(" ");
            int rows=Integer.parseInt(params[1]);
            int columns = Integer.parseInt(params[2]);
            model.inicialitzar_temps(rows, columns);
        }
    }
}