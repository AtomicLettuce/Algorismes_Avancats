package P5.Controlador;

import P5.Main;
import P5.Model.Model;

import java.util.*;

public class Controlador extends Thread {

    Main main;
    Model model;

    public Controlador(Main main, Model model) {
        this.main = main;
        this.model = model;
    }

    @Override
    public void run() {
        // Método que se ejecuta cuando se inicia el hilo del controlador
        // Puedes implementar aquí la lógica que se ejecutará en segundo plano
    }

    public HashMap totsAmbTots() {
        HashMap<String, HashMap<String, Double>> resultats = new HashMap();

        for (int i = 0; i < model.idiomes.length; i++) {
            resultats.put(model.idiomes[i], distanciaTotsIdiomes(model.idiomes[i]));
        }
        model.setResultatsTotsAmbTots(resultats);
        return resultats;
    }

    public String reconeixerIdioma(String text) {
        // Método para reconocer el idioma de un texto
        // Divide el texto en palabras y devuelve el idioma con la distancia mínima
        String[] paraules = text.split(" ");
        Map.Entry res = getMinRes(distanciaTotsIdiomes(model.addDiccionari(paraules)));
        return (String) "Idioma: " + res.getKey() + " Distància: " + res.getValue();
    }

    public HashMap<String, Double> distanciaTotsIdiomes(String principal) {
        // Método para calcular las distancias entre el idioma principal y otros idiomas
        // Recibe el idioma principal y devuelve un mapa con las distancias para cada idioma
        ArrayList<String> idiomes = model.getIdiomesCompare(principal);
        HashMap<String, Double> distancies = new HashMap<>();

        for (String idioma : idiomes) {
            distancies.put(idioma, distanciaEntreDosIdiomes(principal, idioma));
        }

        model.setResultats(distancies);

        return distancies;
    }

    public double distanciaEntreDosIdiomes(String principal, String altre) {
        // Método para calcular la distancia entre dos idiomas
        // Carga los diccionarios de los idiomas, genera palabras aleatorias y calcula la distancia de Levenshtein

        // Carga los diccionarios de los idiomas
        model.carregaDiccionari(principal);
        model.carregaDiccionari(altre);

        // Obtiene los diccionarios
        String[] idiomaPrincipalAux = model.getDiccionari(principal);
        String[] idiomaCompararAux = model.getDiccionari(altre);

        // Genera palabras aleatorias para comparar
        /* Amb Random
        String[] idiomaPrincipal = new String[model.getRANDOM_MAX()];
        String[] idiomaComparar = new String[model.getRANDOM_MAX()];

        for (int i = 0; i < model.getRANDOM_MAX(); i++) {
            idiomaPrincipal[i] = idiomaPrincipalAux[new Random().nextInt(model.getDiccionariSize(principal))];
            idiomaComparar[i] = idiomaCompararAux[new Random().nextInt(model.getDiccionariSize(altre))];
        }
        */


        //Sense Random
        String[] idiomaPrincipal = model.getDiccionari(principal);
        String[] idiomaComparar = model.getDiccionari(altre);

        double distanceP_C = 0; // Distancia del principal al comparar

        // Calcula la distancia para cada palabra del idioma principal
        for (String i : idiomaPrincipal) {
            int min = Integer.MAX_VALUE;
            int idxComparar = 0;
            for (int j = 0; j < idiomaComparar.length / model.getNumCPUs(); j++) {
                Comparador[] comparadors = new Comparador[model.getNumCPUs()];
                Thread[] threads = new Thread[model.getNumCPUs()];
                for (int k = 0; k < threads.length; k++) {
                    comparadors[k] = new Comparador(i, idiomaComparar[idxComparar]);
                    threads[k] = new Thread(comparadors[k]);
                    idxComparar++;

                }
                for (Thread thread : threads) {
                    thread.start();
                }

                for (Thread thread : threads) {
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                for (Comparador comparador : comparadors) {
                    if (comparador.getRes() < min) {
                        min = comparador.getRes();
                    }
                }


            }
            int paraulesRestants = idiomaComparar.length % model.getNumCPUs();


            Comparador[] comparadors = new Comparador[paraulesRestants];
            Thread[] threads = new Thread[paraulesRestants];
            for (int k = 0; k < threads.length; k++) {
                comparadors[k] = new Comparador(i, idiomaComparar[idxComparar]);
                threads[k] = new Thread(comparadors[k]);
                idxComparar++;

            }
            for (Thread thread : threads) {
                thread.start();
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            for (Comparador comparador : comparadors) {
                if (comparador.getRes() < min) {
                    min = comparador.getRes();
                }
            }

            distanceP_C += min;
        }

        //Recorrem totes les paraules de l'idioma a principal per cada paraula de l'idioma comparar

        double distanceC_P = 0; //Distancia del comparar al principal

        for (String i : idiomaComparar) {
            int min = Integer.MAX_VALUE;
            int idxPrincipal = 0;
            for (int j = 0; j < idiomaPrincipal.length / model.getNumCPUs(); j++) {
                Comparador[] comparadors = new Comparador[model.getNumCPUs()];
                Thread[] threads = new Thread[model.getNumCPUs()];
                for (int k = 0; k < threads.length; k++) {
                    comparadors[k] = new Comparador(i, idiomaPrincipal[idxPrincipal]);
                    threads[k] = new Thread(comparadors[k]);
                    idxPrincipal++;

                }
                for (Thread thread : threads) {
                    thread.start();
                }

                for (Thread thread : threads) {
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                for (Comparador comparador : comparadors) {
                    if (comparador.getRes() < min) {
                        min = comparador.getRes();
                    }
                }


            }
            int paraulesRestants = idiomaPrincipal.length % model.getNumCPUs();

            Comparador[] comparadors = new Comparador[paraulesRestants];
            Thread[] threads = new Thread[paraulesRestants];
            for (int k = 0; k < threads.length; k++) {
                comparadors[k] = new Comparador(i, idiomaPrincipal[idxPrincipal]);
                threads[k] = new Thread(comparadors[k]);
                idxPrincipal++;

            }
            for (Thread thread : threads) {
                thread.start();
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            for (Comparador comparador : comparadors) {
                if (comparador.getRes() < min) {
                    min = comparador.getRes();
                }
            }

            distanceC_P += min;
        }


        distanceP_C = distanceP_C / idiomaPrincipal.length;
        distanceC_P = distanceC_P / idiomaComparar.length;
        double distantcia = Math.sqrt((Math.pow(distanceP_C, 2) + Math.pow(distanceC_P, 2)));
        model.distancia = distantcia;
        return distantcia;
    }


    public Map.Entry getMinRes(HashMap<String, Double> numeros) {
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

    private class Comparador implements Runnable {
        String word1;
        String word2;
        int res;

        public Comparador(String w1, String w2) {
            word1 = w1;
            word2 = w2;
        }

        @Override
        public void run() {
            res = distanciadeLevenshtein(word1, word2);
        }

        public int getRes() {
            return res;
        }
    }

}
