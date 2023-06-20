package P7.Controllador;

import P7.Main;
import P7.Vista.Vista;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

    private Vista vista;
    private Main main;

    private int KeyLength = 617;
    private BigInteger publicaN;
    private BigInteger publicaE;
    private BigInteger privadE;

    public RSA(int i){
        KeyLength = Integer.SIZE - Integer.numberOfLeadingZeros(i);
    }

    public RSA(){}

    public void generarClaus(){

        //Generar els dos primers grans
        BigInteger p = generarPrimers(KeyLength / 2);
        BigInteger q = generarPrimers(KeyLength / 2);

        // Calcular el modul
        publicaN = p.multiply(q);

        // calcular funcion de Euler
        BigInteger phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Elegir exponent públic
        publicaE = BigInteger.valueOf(65537); // Commonly used value for e

        // Calcular l'exponent privat
        privadE = publicaE.modInverse(phiN);
    }

    private static BigInteger generarPrimers(int bitLength) {
        SecureRandom random = new SecureRandom();
        return BigInteger.probablePrime(bitLength, random);
    }

    public BigInteger getPublicaN() {
        return publicaN;
    }

    public void setPublicaN(BigInteger publicaN) {
        this.publicaN = publicaN;
    }

    public BigInteger getPrivadE() {
        return privadE;
    }

    public void setPrivadE(BigInteger privadE) {
        this.privadE = privadE;
    }

    public BigInteger getPublicaE() {
        return publicaE;
    }

    public void setPublicaE(BigInteger publicaE) {
        this.publicaE = publicaE;
    }
}
