package P1.controlador;

import P1.Main;
import P1.interfaces.InterficieComunicacio;
import P1.model.Model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Controlador extends Thread implements InterficieComunicacio {
    // Punters necesaris per accedir als altres elements del model.
    private Main main;
    private Model dades;

    private static final int NALGORITMES = 3;
    private static final int[] NITERACIONS = {100,1000,10000,100000};

    public Controlador(Main main, Model dades) {
        // Assignam els punters.
        this.main = main;
        this.dades = dades;
        // Inicialitzam la matriu de temps.
        main.comunicacio("Inicialitzar_Temps " + NALGORITMES + " " + NITERACIONS.length);
    }

    // Métode run que espera la petició de calcular la moda.
    @Override
    public void run() {
        super.run();
    }

    // Mètode que posa en marxa el procés de càlcul de tots els algoritmes i les diferents iteracions.
    private void inici() {
        try {
            // Farem els calculs per a totes les iteracions amb els diferents algoritmes.
            for (int idx = 0; idx < NITERACIONS.length; idx++) {
                main.comunicacio("Generar_vectors " + idx + " " + NALGORITMES);
                int[][] vectors = dades.getVectors();

                //O(N)
                int[] posN = {idx, 0};
                Thread oN = new Thread(new oN(vectors[0], main, posN));
                oN.start();
                oN.join();

                //O(N log N)
                int[] posNlogN = {idx, 1};
                Thread oNlogN = new Thread(new oNlogN(vectors[1], main, posNlogN));
                oNlogN.start();
                oNlogN.join();

                //O(NN)
                int[] posNN = {idx, 2};
                Thread oNN = new Thread(new oNN(vectors[2], main, posNN));
                oNN.start();
                oNN.join();
            }
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    private class oN extends Thread {
        int[] aOrdenar;
        Main main;
        int[] posTemps;
        public oN(int[] aOrdenar, Main main, int[] pos) {
            this.aOrdenar = aOrdenar;
            this.main = main;
            this.posTemps = pos;
        }

        @Override
        public void run() {
            float temps = calcular(this.aOrdenar);
            main.comunicacio("Actualitzar_Temps " + posTemps[0] + " " + posTemps[1] + " " + temps);
        }

        // Mètode amb un cost computacional de O(n), implementat amb un HashMap.
        private float calcular(int[] arr) {
            // Capturam el temps a l'inici del programa.
            float tInicial = System.nanoTime();
            // Calculam la moda.
            int moda = hashModa(arr);

            // Obtenim el que ha tardat a executar-se.
            float tFinal = System.nanoTime();
            float tTotal = tFinal - tInicial;

            //System.out.println(tTotal + "ns");
            return tTotal;
        }

        private int hashModa(int[] arr) {
            // Declaram el hashmap de Key, Value integers, un pel nombre trobat i l'altre pel nombre
            // d'aparicions d'aquesta key.
            HashMap<Integer, Integer> hashMap = new HashMap<>();

            // Recorrem l'array dentrada i actualizam el valor de trobats.
            for(int element: arr) {
                if(hashMap.containsKey(element)) {
                    int nAparicions = hashMap.get(element);
                    hashMap.put(element, nAparicions++);
                } else {
                    hashMap.put(element, 1);
                }
            }

            // Calculam la moda, si trobam un nombre d'aparicions major a l'actual es converteix en moda.
            int nMaxima = arr[0];
            for (Map.Entry<Integer, Integer> entrada: hashMap.entrySet()) {
                if(entrada.getValue() > nMaxima) {
                    nMaxima = entrada.getKey();
                }
            }

            return nMaxima;
        }
    }

    private class oNlogN extends Thread {
        int[] aOrdenar;
        Main main;
        int[] posTemps;
        public oNlogN(int[] aOrdenar, Main main, int[] pos) {
            this.aOrdenar = aOrdenar;
            this.main = main;
        }

        @Override
        public void run() {
            float temps = calcular(this.aOrdenar);
            main.comunicacio("Actualitzar_Temps " + posTemps[0] + " " + posTemps[1] + " " + temps);
        }

        // Mètode amb un cost computacional de O(n * log n), implementat l'algoritme MergeSort.
        private float calcular(int[] arr) {
            float tInicial = System.currentTimeMillis();

            mergeSort(arr,0, arr.length - 1);

            float tFinal = System.currentTimeMillis();
            float tTotal = tInicial - tFinal;

            return tTotal;
        }

        public static void mergeSort(int[] arr, int left, int right) {
            if (left < right) {
                int middle = (left + right) / 2; // encontrar el punto medio del arreglo
                mergeSort(arr, left, middle); // ordenar la primera mitad del arreglo
                mergeSort(arr, middle + 1, right); // ordenar la segunda mitad del arreglo
                merge(arr, left, middle, right); // unir las dos mitades ordenadas
            }
        }

        public static void merge(int[] arr, int left, int middle, int right) {
            int n1 = middle - left + 1;
            int n2 = right - middle;
            int[] L = new int[n1];
            int[] R = new int[n2];
            for (int i = 0; i < n1; ++i)
                L[i] = arr[left + i];
            for (int j = 0; j < n2; ++j)
                R[j] = arr[middle + 1 + j];

            int i = 0, j = 0;
            int k = left;
            while (i < n1 && j < n2) {
                if (L[i] <= R[j]) {
                    arr[k] = L[i];
                    i++;
                } else {
                    arr[k] = R[j];
                    j++;
                }
                k++;
            }

            while (i < n1) {
                arr[k] = L[i];
                i++;
                k++;
            }

            while (j < n2) {
                arr[k] = R[j];
                j++;
                k++;
            }
        }
    }

    private class oNN extends Thread {
        int[] aOrdenar;
        Main main;
        int[] posTemps;

        public oNN(int[] aOrdenar, Main main, int[] pos) {
            this.aOrdenar = aOrdenar;
            this.main = main;
        }

        @Override
        public void run() {
            float temps = calcular(this.aOrdenar);
            main.comunicacio("Actualitzar_Temps " + posTemps[0] + " " + posTemps[1] + " " + temps);
        }

        private float calcular(int[] arr) {
            float tInicial = System.currentTimeMillis();

            int[] pVectorial = producteVectorial(arr);

            float tFinal = System.currentTimeMillis();
            float tTotal = tInicial - tFinal;

            System.out.println(tTotal);
            return tTotal;
        }

        // Mètode amb un cost computacional de O(n^2), calculam el producte vectorial amb el mateix.
        private int[] producteVectorial(int[] arr) {
            // Cream el nou array amb el producte i l'inicialitzam a 0s.
            int[] producte = new int[arr.length];
            Arrays.fill(producte, 0);

            // Algorisme per aplicar el producte vectorial.
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    producte[i] = producte[i] + (producte[i] * producte[j]);
                }
            }

            return producte;
        }
    }

    @Override
    public void comunicacio(String instruccio) {
        if(instruccio.equals("run")) {
            this.inici();
        } else {
            // Amollar error.
            System.err.println("Paràmetre incorrecte per al controlador");
        }
    }
}
