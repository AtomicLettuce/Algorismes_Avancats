package P7;

import P7.Controllador.Primalidad;
import P7.Controllador.RSA;
import P7.Interficies.InterficieComunicacio;
import P7.Model.Model;
import P7.Vista.Vista;
import P7.Controllador.controllador;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Main implements InterficieComunicacio {
    public static boolean CONTINUAR=true;
    private Vista vista;
    private controllador controllador;
    private Model model;

    public static void main(String[] args) {
        new Main().inici();
    }


    private void inici(){
        System.out.println("UEP!");

        vista = new Vista("mondongo",this);
        model = new Model();
        controllador = new controllador(vista, model);
        /*BigInteger[] numeros = {
                new BigInteger("4661"),
                new BigInteger("453007"),
                new BigInteger("41136371"),
                new BigInteger("5900338729"),
                new BigInteger("648773765743"),
                new BigInteger("47081630774621"),
                new BigInteger("5674018715441897")
        };
        /*for(BigInteger numero : numeros ){
            unNumero(numero);
            model.vaciarHashMap();
        }*/

        RSA rsa = new RSA();
        rsa.generarClaus();

    }


    public void codificar(String filePath, RSA rsa){
        try {
            byte[] fitxerNormal = entrada(filePath);
            String fitxerEncriptat = rsa.encriptar(fitxerNormal);
            sortida(filePath, fitxerEncriptat);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void decodificar(String filePath, RSA rsa){
        try {
            byte[] fitxerNormal = entrada(filePath);
            String fitxerDesencriptat = rsa.desencriptar(fitxerNormal);
            sortida(filePath, fitxerDesencriptat);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public byte[] entrada(String filePath) throws IOException {
        Path fitxer = Path.of(filePath);
        byte[] bytes = Files.readAllBytes(fitxer);
        return bytes;
    }

    public  void sortida(String content, String filePath) throws IOException {
        Path path = Path.of(filePath);
        Files.writeString(path, content, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void unNumero(BigInteger numero){
        System.out.println("Longitud del número a factorizar: "+numero.toString().length()+" cifras.");
        Long t1 = System.nanoTime();
        controllador.run(numero);
        Long t2 = System.nanoTime();
        System.out.println("Voy a factorizar " + numero);
        model.printNumeros();
        System.out.println("He tardado "+(t2-t1)+" nanosec");
        System.out.println("He tardado "+((double)(t2-t1)/1000000000)+" sec");
    }

    @Override
    public void comunicacio(String instruccio) {
        switch (instruccio){
            case "stop":
                CONTINUAR=false;
                break;
            case "GeneraClausRSA":
                //[IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]
                break;
        }if (instruccio.startsWith("Verificar primer")){
            String numero =instruccio.split(":")[1];
//            Primalidad.esPrimer(numero);
            //
        } else if (instruccio.startsWith("Factoritzar:")) {
            String numero =instruccio.split(":")[1];
            controllador = new controllador(vista, model);
            //controllador.inici(numero);
            //sout(model.prinnums)
        }

    }
}