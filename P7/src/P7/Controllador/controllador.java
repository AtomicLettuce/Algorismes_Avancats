package P7.Controllador;

import P7.Model.Model;
import P7.Vista.Vista;

import java.math.BigInteger;
import java.util.Random;

public class controllador {
    Primalidad p;
    Model model;
    Vista vista;
    public controllador(Vista vista, Model model){
        this.vista = vista;
        this.model = model;
    }
    public void inici(BigInteger number){
        p = new Primalidad();
        factor(number);
    }

    private void factor(BigInteger numero){
        p = new Primalidad();
        BigInteger factor;
        while(!p.esPrimer(numero)){
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
