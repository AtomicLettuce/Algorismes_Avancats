package P1;

import P1.controlador.Controlador;
import P1.interfaces.InterficieComunicacio;
import P1.model.Model;
import P1.view.Monitor_Vista;
import P1.view.Vista;

public class Main implements InterficieComunicacio {

    public static boolean CONTINUAR=true;
    private Monitor_Vista mv;
    private Model model = new Model(this);
    private Controlador controlador = new Controlador(this, model);
    private Vista vista;

    public Model getModel() {
        return model;
    }

    public static void main(String[] args) {
        new Main().inici();
    }

    public void inici() {
        System.out.println("Hola!");
        mv=new Monitor_Vista();
        vista=new Vista("P1: Algorismes Avançats", this);
    }

    @Override
    public void comunicacio(String instruccio) {
        switch (instruccio){
            case "Aturar":
                System.out.println("Aturant...");
                // Aturar tots els fils i aturar el programa
                CONTINUAR=false;
                // Per permetra a la vista acabar correctament
                vista.actualitzar();
                break;
            case "Actualitzar":
                vista.actualitzar();
                // Tornar a pintar la GUI
                break;
            case "Arrancar":
                // Envia l'ordre de començar
                controlador.start();
                break;
        }

        // Format: "Inicialitzar_Temps 'rows' 'columns'"
        if(instruccio.startsWith("Inicialitzar_Temps")){
            String params[] = instruccio.split(" ");
            int rows=Integer.parseInt(params[1]);
            int columns = Integer.parseInt(params[2]);
            model.inicialitzar_temps(rows, columns);
        }

        // Format: "Generar_vectors 'rows' 'columns' 'valor'"
        if(instruccio.startsWith("Actualitzar_Temps")){
            String params[] = instruccio.split(" ");
            int rows = Integer.parseInt(params[1]);
            int columns = Integer.parseInt(params[2]);
            Float nouValor = Float.parseFloat(params[3]);
            model.setTemps(rows, columns, nouValor);
        }

        // Format: "Generar_vectors 'longitud' 'N algoritmes'"
        if(instruccio.startsWith("Generar_vectors")) {
            String params[] = instruccio.split(" ");
            int longitud = Integer.parseInt(params[1]);
            int nAlgoritmes = Integer.parseInt(params[2]);
            model.generarVectors(longitud, nAlgoritmes);
        }
    }
}