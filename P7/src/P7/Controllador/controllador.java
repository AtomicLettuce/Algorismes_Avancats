package P7.Controllador;

import P7.Main;
import P7.Model.Model;
import P7.Vista.Vista;

import java.math.BigInteger;
import java.util.Random;

public class controllador extends Thread{
    Primalidad p;
    Model model;
    Vista vista;
    Main main;
BigInteger number;
    public controllador(Main main   , Vista vista, Model model) {
        this.vista = vista;
        this.model = model;
        this.main=main;
    }

    // Funció que retorna quant de temps aproxima el controlador que tardarà en factoritzar un número de llargària N
    // Aquesta funció ha estat emprant interpolació de Newton
    public static final double temps_aproximat(int n) {
        double i = 0.000368357066 * Math.pow(n, 6) - 0.0198795751 * Math.pow(n, 5) + 0.43510339 * Math.pow(n, 4) - 4.92310776 * Math.pow(n, 3) + 30.2531138 * Math.pow(n, 2) - 95.3486723 * n + 119.892874;
        return i;
    }

    public void run() {
        p = new Primalidad();
        long t1=System.nanoTime();
        factor(number);
        long t2=System.nanoTime();
        main.comunicacio("actualitzar");
        main.comunicacio("Temps: "+(t2-t1)+" nanosegons");
    }

    public void setNumber(BigInteger number) {
        this.number = number;
    }

    private void factor(BigInteger numero) {
        p = new Primalidad();
        BigInteger factor;
        while (!p.esPrimer(numero)) {
            factor = factoritzar(numero);
            model.addNumero(factor);
            numero = numero.divide(factor);

        }
        model.addNumero(numero);
    }

    private BigInteger factoritzar(BigInteger n) {
        if (n.compareTo(BigInteger.ONE) <= 0) {
            return n; // Caso base: el número es 0 o 1
        }

        if (n.isProbablePrime(50)) {
            return n; // Caso base: el número es primo
        }

        BigInteger a = getRandomBase(n);
        BigInteger x = new BigInteger(a.toString());

        BigInteger p = BigInteger.ONE;
        BigInteger q = BigInteger.ONE;

        do {
            x = x.multiply(x).add(a).mod(n);
            BigInteger d = n.gcd(x.subtract(p));
            if (d.compareTo(BigInteger.ONE) > 0 && d.compareTo(n) < 0) {
                return d; // Se encontró un factor no trivial
            }

            if (x.equals(p)) {
                return n; // Se ha encontrado un ciclo, no se puede factorizar
            }

            if (q.compareTo(BigInteger.ONE) == 0) {
                q = x;
            }

            x = x.multiply(x).add(a).mod(n);
            d = n.gcd(x.subtract(p));
            if (d.compareTo(BigInteger.ONE) > 0 && d.compareTo(n) < 0) {
                return d; // Se encontró un factor no trivial
            }

            p = p.multiply(x.subtract(q)).mod(n);
            q = q.multiply(x.subtract(q)).mod(n);
            q = q.multiply(x.subtract(q)).mod(n);

        } while (true);
    }

    private BigInteger getRandomBase(BigInteger n) {
        BigInteger base;
        do {
            Random rand = new Random();
            base = new BigInteger(n.bitLength(), rand);
        } while (base.compareTo(BigInteger.ONE) <= 0 || base.compareTo(n) >= 0 || base.isProbablePrime(50));
        return base;
    }
}
