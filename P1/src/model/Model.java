package model;

import interfaces.InterficieComunicacio;

import java.util.Random;

public class Model implements InterficieComunicacio {

    // Random per a crear l'array per a calcular la moda, inicialitzat amb una llavor
    // per a que cada vegada que s'executi posi els mateixos números.
    private final Random rand = new Random(42);
    // Nombre màxim que generà el random.
    private final int numeroMaxim = 100000;
    // Array de dades.
    private int[] array;

    // Generam amb una longitud fixada pel paràmetre d'entrada.
    private void generarArray(int n) {
        // Nou array temporal.
        int[] arrayGenerat = new int[n];

        // Omplim l'array.
        for (int i = 0; i < n; i++) {
            arrayGenerat[i] = rand.nextInt(numeroMaxim);
        }

        // Seteam el nou array.
        array = arrayGenerat;
    }

    // Getter necesari per la classe 'Controlador.Controlador'.
    public int[] getArray() {
        return array;
    }

    @Override
    public void comunicacio(String instruccio) {
        // COMPROVAR SI LA COMUNICACIÓ FUNCIONA AIXÍ.
        generarArray(Integer.parseInt(instruccio));
    }
}