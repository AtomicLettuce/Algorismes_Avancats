package P5.Controlador;


import P5.Main;
import P5.Model.Model;

import java.util.*;

public class Controlador extends Thread{

    Main main;
    Model model;


    public Controlador(Main main, Model model){
        this.main = main;
        this.model = model;
    }
    @Override
    public void run() {


    }

    public String reconeixerIdioma(String text){


        String[] paraules = text.split(" ");
        return (String) getMinRes(distanciaTotsIdiomes(model.addDiccionari(paraules))).getKey();
    }
    public HashMap <String, Double> distanciaTotsIdiomes(String principal){
       ArrayList<String> idiomes = model.getIdiomesCompare(principal);

        HashMap <String, Double> distancies = new HashMap<>();

        for (int i = 0; i < idiomes.size(); i++) {
            distancies.put(idiomes.get(i),distanciaEntreDosIdiomes(principal, idiomes.get(i)));
        }

        return distancies;
    }
    public double distanciaEntreDosIdiomes(String principal, String altre){

        //Carregam els fitxers on tenim els idiomes
        model.carregaDiccionari(principal);
        model.carregaDiccionari(altre);

        //Obtenim els diccionaris
        String[] idiomaPrincipalAux = model.getDiccionari(principal);
        String[] idiomaCompararAux = model.getDiccionari(altre);

        String[] idiomaPrincipal = new String[model.getRANDOM_MAX()];
        String[] idiomaComparar = new String[model.getRANDOM_MAX()];

        for (int i = 0; i < model.getRANDOM_MAX(); i++) {
            idiomaPrincipal[i] = idiomaPrincipalAux[new Random().nextInt(model.getDiccionariSize(principal))];
            idiomaComparar[i] = idiomaCompararAux[new Random().nextInt(model.getDiccionariSize(altre))];
        }




        //Recorrem totes les paraules de l'idioma a comparar per cada paraula de l'idioma principal


        double distanceP_C = 0;
        int a = 0;
        int b=0;
        //Distancia del principal al comparar
        for (String i : idiomaPrincipal) {
            int min = Integer.MAX_VALUE;
            b=0;
            for (String j : idiomaComparar){
                int distanciaActual = distanciadeLevenshtein(i,j);
                if (min > distanciaActual){
                    min = distanciaActual;
                }
                b++;
            }
            a++;
            distanceP_C += min;
        }

        //Recorrem totes les paraules de l'idioma a principal per cada paraula de l'idioma comparar

        double distanceC_P = 0; //Distancia del comparar al principal

        for (String i : idiomaComparar) {
            int min = Integer.MAX_VALUE;
            for (String j : idiomaPrincipal){
                int distanciaActual = distanciadeLevenshtein(i,j);
                if (min > distanciaActual){
                    min = distanciaActual;
                }
            }
            distanceC_P += min;
        }


        distanceP_C = distanceP_C/model.getRANDOM_MAX();
        distanceC_P = distanceC_P/model.getRANDOM_MAX();

        return  Math.sqrt((Math.pow(distanceP_C,2) + Math.pow(distanceC_P,2)));
    }


    public Map.Entry getMinRes(HashMap<String,Double> numeros) {
        double minimValue = Double.POSITIVE_INFINITY;
        String minimKey = "";

        for (HashMap.Entry<String, Double> entry : numeros.entrySet()) {
            String clave = entry.getKey();
            Double valor = entry.getValue();
            if (minimValue > valor) {
                minimValue = valor;
                minimKey = clave;
            }
        }



        return new AbstractMap.SimpleEntry<>(minimKey, minimValue);
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

    private class Comparador implements Runnable{
        String word1;
        String word2;
        int res;
        public Comparador (String w1, String w2){
            word1 = w1;
            word2 = w2;
        }
        @Override
        public void run() {
          res = distanciadeLevenshtein(word1,word2);
        }

        public int getRes() {
            return res;
        }
    }

}
