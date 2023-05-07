import Controlador.Controlador;

public class Main implements Interficies.InterficieComunicacio {



    private Controlador controlador;

    public static void main(String[] args) {
        System.out.println("UEP!!");
        new Main().inici();
    }
    public void inici(){

    }


    @Override
    public void comunicacio(String instruccio) {
        switch (instruccio) {
            case "stop":
                System.out.println("Aturant...");
        }
    }
}