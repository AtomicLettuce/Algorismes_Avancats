package Controlador;

import P1.interfaces.InterficieComunicacio;
import P1.model.Model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Controlador extends Thread implements InterficieComunicacio {
    // Necessitarem accedir a les dades per a calcular la moda.
    private Model dades;
    public Controlador(Model dades) {
        this.dades = dades;
    }

    // Mètode amb un cost computacional de O(n), implementat amb un HashMap.
    private float oN(int[] arr) {
        // Capturam el temps a l'inici del programa.
        float tInicial = System.nanoTime();

        // Calculam la moda.
        int moda = hashModa(arr);

        // Obtenim el que ha tardat a executar-se.
        float tFinal = System.nanoTime();
        float tTotal = tFinal - tInicial;

        System.out.println(tTotal + "ns");
        return tTotal;
    }

    private int hashModa(int[] arr) {
        // Declaram el hashmap de Key, Value integers, un per el nombre trobat i l'altre per el número
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

    // Mètode amb un cost computacional de O(n * log n), implementat l'algoritme MergeSort.
    private float oNlogN(int[] arr) {
        float tInicial = System.currentTimeMillis();

        mergeSort(arr,0, arr.length - 1);

        float tFinal = System.currentTimeMillis();
        float tTotal = tInicial - tFinal;

        System.out.println(tTotal);
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

    // Mètode amb un cost computacional de O(n^2), calculam el producte vectorial amb el mateix.
    private float oNN(int[] arr) {
        float tInicial = System.currentTimeMillis();

        int[] pVectorial = producteVectorial(arr);

        float tFinal = System.currentTimeMillis();
        float tTotal = tInicial - tFinal;

        System.out.println(tTotal);
        return tTotal;
    }

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

    @Override
    public void comunicacio(String instruccio) {
        switch (instruccio) {
            case "oN" -> oN(dades.getVectors()) ;
            case "oNlogN" -> oNlogN(dades.getVectors());
            case "oNN" -> oNN(dades.getVectors());
            default -> {
                //ERROR!
            }
        }
    }

}
