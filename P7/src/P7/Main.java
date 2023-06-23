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
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Main implements InterficieComunicacio {
    public static boolean CONTINUAR = true;
    private Vista vista;
    private controllador controllador;
    private RSA rsa;
    private Model model;

    public static void main(String[] args) {
        new Main().inici();
    }


    private void inici() {
        model = new Model();
        vista = new Vista("mondongo", this, model);
        controllador = new controllador(this, vista, model);
        rsa = new RSA();
    }


    public void codificar(String filePath, RSA rsa) {
        try {
            byte[] fitxerNormal = entrada(filePath);
            System.out.println(new String(fitxerNormal));
            byte[] fitxerEncriptat = rsa.encriptar(fitxerNormal);
            sortida(fitxerEncriptat, filePath + "cod.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void decodificar(String filePath, RSA rsa) {
        try {
            byte[] fitxerNormal = entrada(filePath);
            System.out.println(new String(fitxerNormal));
            byte[] fitxerDesencriptat = rsa.desencriptar(fitxerNormal);
            System.out.println("--" + fitxerDesencriptat);
            System.out.println("++" + (new String(fitxerDesencriptat)));
            sortida(fitxerDesencriptat, filePath + "decod.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] entrada(String filePath) throws IOException {
        Path fitxer = Path.of(filePath);
        byte[] bytes = Files.readAllBytes(fitxer);
        return bytes;
    }

    public void sortida(byte[] content, String filePath) throws IOException {
        System.out.println(filePath);
        Path path = Path.of(filePath);
        String s = new String(content);
        Files.writeString(path, new String(content), StandardOpenOption.CREATE);
    }

    public void sortida(String content, String filePath) throws IOException {
        System.out.println(filePath);
        Path path = Path.of(filePath);
        Files.writeString(path, content, StandardOpenOption.CREATE);
    }

    @Override
    public void comunicacio(String instruccio) {
        switch (instruccio) {
            case "stop":
                CONTINUAR = false;
                break;
            case "actualitzar":
                vista.actualitzar();
                break;
            case "GeneraClausRSA":
                rsa.generarClaus();

                Path path = Paths.get(System.getProperty("user.home")).resolve("claus.txt");
                try {
                    if (Files.exists(path)) {
                        Files.delete(path);
                    }
                    Files.createFile(path);
                    sortida("Clau pública: " + rsa.getPublicaE() + "\n Clau privada: " + rsa.getPrivadE(), path.toString());
                    vista.popup("Claus generades. Pots consultar-les a: " + path.toString());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
        }
        if (instruccio.startsWith("Verificar primer")) {
            String numero = instruccio.split(":")[1];
            if (Primalidad.esPrimer(new BigInteger(numero))) {
                vista.popup("El número " + numero + " és probablement primer");
            } else {
                vista.popup("El número " + numero + " és definitivament no primer");
            }
        } else if (instruccio.startsWith("Factoritzar:")) {
            String numero = instruccio.split(":")[1];
            controllador = new controllador(this, vista, model);
            controllador.setNumber(new BigInteger(numero));
            controllador.run();
        } else if (instruccio.startsWith("XifrarRSA:")) {
            String clau = instruccio.split(":")[1];
            //rsa.setPublicaE(new BigInteger(clau));
            codificar(model.fitxer.getPath(), rsa);


        } else if (instruccio.startsWith("DesxifrarRSA:")) {
            String clau = instruccio.split(":")[1];
            //rsa.setPrivadE(new BigInteger(clau));
            decodificar(model.fitxer.getPath(), rsa);


        } else if (instruccio.startsWith("Temps:")) {
            vista.popup(instruccio);
        }
    }
}