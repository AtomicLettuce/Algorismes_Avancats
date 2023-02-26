import Controlador.Controlador;
import model.Model;
import view.Vista;

public class Main {

    private Model model = new Model();
    private Controlador controlador = new Controlador(model);
    private Vista vista = new Vista();

    public static void main(String[] args) {
        new Main().inici();
    }

    public void inici() {
        System.out.println("Hola!");
        model.comunicacio("100000000");
        controlador.comunicacio("oN");
    }

}