package P5.Controlador;


import P5.Main;
import P5.Model.Model;

import java.util.ArrayList;
import java.util.HashSet;

public class Controlador extends Thread{

    Main main;
    Model model;

    HashSet <String> idiomaPrincipal;
    HashSet <String> idiomaComparar;

    public Controlador(Main main, Model model){
        this.main = main;
        this.model = model;
    }
    @Override
    public void run() {


    }

    public double[] distanciaTotsIdiomes(String principal){
       ArrayList<String> idiomes = model.getIdiomesCompare(principal);

        double[] distancies = new double[idiomes.size()];

        for (int i = 0; i < idiomes.size(); i++) {
            distancies[i] = distanciaEntreDosIdiomes(principal, idiomes.get(i));
        }

        return distancies;
    }
    public double distanciaEntreDosIdiomes(String principal, String altre){

        //Carregam els fitxers on tenim els idiomes
        model.carregaDiccionari(principal);
        model.carregaDiccionari(altre);

        //Obtenim els diccionaris
        idiomaPrincipal = model.getDiccionari(principal);
        idiomaComparar = model.getDiccionari(altre);

        int idiomaPrincipalSize = model.getDiccionariSize(principal);
        int idiomaComapararSize = model.getDiccionariSize(altre);

        //Recorrem totes les paraules de l'idioma a comparar per cada paraula de l'idioma principal

        int [] distancesPerWord = new int[idiomaComapararSize];
        double distanceP_C = 0; //Distancia del principal al comparar
        for (String i : idiomaPrincipal) {
            int idx = 0;
            for (String j : idiomaComparar){

                distancesPerWord[idx]= distanciadeLevenshtein(i,j);
                idx++;
            }
            distanceP_C += getMin(distancesPerWord);
            System.out.println(distanceP_C);
        }

        //Recorrem totes les paraules de l'idioma a principal per cada paraula de l'idioma comparar

        double distanceC_P = 0; //Distancia del comparar al principal
        distancesPerWord = new int[idiomaPrincipalSize];
        for (String i : idiomaComparar) {
            int idx = 0;
            for (String j : idiomaPrincipal){
                distancesPerWord[idx]= distanciadeLevenshtein(i,j);
                idx++;
            }
            distanceC_P += getMin(distancesPerWord);
            System.out.println(distanceC_P);
        }


        distanceP_C = distanceP_C/idiomaPrincipalSize;
        distanceC_P = distanceC_P/idiomaComapararSize;

        return  Math.sqrt((Math.pow(distanceP_C,2) + Math.pow(distanceC_P,2)));
    }

    public int getMin(int[] numeros) {
        int minim = numeros[0];
        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i] < minim) {
                minim = numeros[i];
            }
        }
        return minim;
    }

    public int distanciadeLevenshtein(String word1, String word2) {
        int longitudWord1 = word1.length();
        int longitudWord2 = word2.length();
        int cost;

        int[][] matriuDistancies = new int[longitudWord1 + 1][longitudWord2 + 1];


        //Inicialitzam la primer fila i columna
        for (int i = 0; i <= longitudWord1; i++) {
            matriuDistancies[i][0] = i;
        }

        for (int j = 0; j <= longitudWord2; j++) {
            matriuDistancies[0][j] = j;
        }

        for (int i = 1; i <= longitudWord1; i++) {
            for (int j = 1; j <= longitudWord2; j++) {

                //si word1 == word2 entonces coste ← 0 si no coste ← 1
                cost = (word1.charAt(i - 1) == word2.charAt(j - 1)) ? 0 : 1;

                matriuDistancies[i][j] = Math.min(
                        Math.min(matriuDistancies[i - 1][j] + 1, // delete
                                matriuDistancies[i][j - 1] + 1), // insert
                        matriuDistancies[i - 1][j - 1] + cost); // replace
            }
        }
        return matriuDistancies[longitudWord1][longitudWord2];
    }

}
