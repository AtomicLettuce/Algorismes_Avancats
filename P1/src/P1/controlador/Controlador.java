package P1.controlador;

import P1.Main;
import P1.interfaces.InterficieComunicacio;
import P1.model.Model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Controlador extends Thread {
    // Punters necesaris per accedir als altres elements del model.
    private Main main;
    private Model dades;

    private static final int NALGORITMES = 3;

    public Controlador(Main main, Model dades) {
        // Assignam els punters.
        this.main = main;
        this.dades = dades;
        // Inicialitzam la matriu de temps.
        main.comunicacio("Inicialitzar_Temps " + NALGORITMES+ " " + dades.NITS.size());
    }

    // Métode run que espera la petició de calcular la moda.
    // Mètode que posa en marxa el procés de càlcul de tots els algoritmes i les diferents iteracions.
    @Override
    public void run() {
        try {
            // Farem els calculs per a totes les iteracions amb els diferents algoritmes.
            for (int idx = 0; idx < dades.NITS.size() && main.CONTINUAR; idx++) {
                sleep(1000);
                main.comunicacio("Actualitzar");
                System.out.println("comenća la ronda "+idx);

                main.comunicacio("Generar_vectors " + dades.NITS.get(idx) + " " + NALGORITMES);
                int[][] vectors = dades.getVectors();

                //O(N)
                int[] posN = {0, idx};
                Thread oN = new Thread(new oN(vectors[0], main, posN));
                oN.start();



                //O(N log N)
                int[] posNlogN = {1, idx};
                Thread oNlogN = new Thread(new oNlogN(vectors[1], main, posNlogN));
                oNlogN.start();

                // Per tal de que no monopolitzi el dibuix
                if(idx<3){
                    //O(NN)
                    int[] posNN = {2,idx};
                    Thread oNN = new Thread(new oNN(vectors[2], main, posNN));
                    oNN.start();
                    oNN.join();
                }

                // Esperam que aquests fils acabin per tornar a llençar la següent ronda
                oN.join();
                oNlogN.join();

            }
            System.out.println("Ja han acabat totes les rondes");
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
            System.out.println("acaba oN "+ posTemps[1]+ " temps "+temps);
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
            for(int i=0;i<arr.length;i++) {
                if(hashMap.containsKey(arr[i])) {
                    int nAparicions = hashMap.get(arr[i]);
                    hashMap.put(arr[i], nAparicions++);
                } else {
                    hashMap.put(arr[i], 1);
                }
                if(i%1000==0){
                    if(!main.CONTINUAR){
                        return 0;
                    }
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
            this.posTemps = pos;
        }


        @Override
        public void run() {
            float temps = calcular(this.aOrdenar);
            main.comunicacio("Actualitzar_Temps " + posTemps[0] + " " + posTemps[1] + " " + temps);
            System.out.println("acaba oNLog "+ posTemps[1]+ " temps "+temps);
        }

        // Mètode amb un cost computacional de O(n * log n), implementat l'algoritme MergeSort.
        private float calcular(int[] arr) {
            float tInicial = System.nanoTime();

            mergeSort(arr,0, arr.length - 1);

            float tFinal = System.nanoTime();
            float tTotal = tFinal  - tInicial ;


            return tTotal;
        }

        public  void mergeSort(int[] arr, int left, int right) {
            if (left < right) {
                int middle = (left + right) / 2; // encontrar el punto medio del arreglo
                mergeSort(arr, left, middle); // ordenar la primera mitad del arreglo
                mergeSort(arr, middle + 1, right); // ordenar la segunda mitad del arreglo
                merge(arr, left, middle, right); // unir las dos mitades ordenadas
            }
        }

        public  void merge(int[] arr, int left, int middle, int right) {
            if(!main.CONTINUAR){
                return;
            }
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
            this.posTemps = pos;
        }

        @Override
        public void run() {
            float temps = calcular(this.aOrdenar);
            main.comunicacio("Actualitzar_Temps " + posTemps[0] + " " + posTemps[1] + " " + temps);
            System.out.println("acaba oNN "+ posTemps[1]+ " temps "+temps);
        }

        private float calcular(int[] arr) {
            float tInicial = System.nanoTime();

            int[] pVectorial = producte(arr);

            float tFinal = System.nanoTime();
            float tTotal = tFinal- tInicial  ;

            System.out.println(tTotal);
            return tTotal;
        }

        // Mètode amb un cost computacional de O(n^2), calculam el producte vectorial amb el mateix.
        private int[] producte(int[] arr) {

            // Cream el nou array amb el producte i l'inicialitzam a 0s.
            int[] prod = new int[arr.length];


            // Algorisme per aplicar el producte vectorial.
            for (int i = 0; i < arr.length; i++) {
                int valor = 0;
                for (int j = 0; j < arr.length; j++) {
                    if(j%1000==0){
                        if(!main.CONTINUAR){
                            return null;
                        }
                    }
                    valor = prod[i] + (prod[i] * prod[j]);
                }
                prod[i] = valor;
            }

            return prod;
        }
    }

}
