/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package P7.Controllador;

import java.math.BigInteger;
import java.util.Random;

public class Primalidad {
    private static final int iteracions = 100; // Number of iterations for accuracy

    public static boolean esPrimer(BigInteger n) {
        if (n.compareTo(BigInteger.ONE) <= 0) {
            return false; // Numbers less than or equal to 1 are not prime
        }

        if (n.compareTo(BigInteger.valueOf(3)) <= 0) {
            return true; // 2 and 3 are prime
        }

        // Write n - 1 as 2^r * d, where d is an odd number
        int r = 0;
        BigInteger d = n.subtract(BigInteger.ONE);
        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            d = d.divide(BigInteger.TWO);
            r++;
        }

        for (int i = 0; i < iteracions; i++) {
            BigInteger a = baseRandom(n);
            BigInteger x = a.modPow(d, n);

            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) {
                continue;
            }

            boolean isProbablePrime = false;
            for (int j = 0; j < r - 1; j++) {
                x = x.modPow(BigInteger.TWO, n);
                if (x.equals(BigInteger.ONE)) {
                    return false; // Definitely composite
                }
                if (x.equals(n.subtract(BigInteger.ONE))) {
                    isProbablePrime = true; // Probably prime
                    break;
                }
            }

            if (!isProbablePrime) {
                return false; // Definitely composite
            }
        }

        return true; // Probably prime
    }

    private static BigInteger baseRandom(BigInteger n) {
        Random random = new Random();
        BigInteger result;
        do {
            result = new BigInteger(n.bitLength(), random);
        } while (result.compareTo(n) >= 0 || result.compareTo(BigInteger.ONE) <= 0);
        return result;
    }


}

