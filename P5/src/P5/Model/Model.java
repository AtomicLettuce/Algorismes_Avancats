package P5.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Model {
    private int custom = 0;
    private HashMap<String, String[]> diccionarisCarregats = new HashMap<>();
     String[] idiomes = {
            "cat",
             "eng",
             "esp"
    };
     final int RANDOM_MAX = 200;
    private int numCPUs = 2;
    public void carregaDiccionari(String diccionariName) {
        //Si ja s'ha carregat el diccionari no fa falta tornar a carregarlo
        if (diccionarisCarregats.containsKey(diccionariName)){
            return;
        }

        //Si no ha estat carregat es carrega
        try {
            BufferedReader bf = new BufferedReader(new FileReader("../P5/src/P5/Model/Diccionaris/" + diccionariName + ".dic"));

            String word = bf.readLine();
            String[] diccionari = new String[Integer.parseInt(word)];
            for (int i = 0; i< diccionari.length; i++) {
                diccionari[i] = bf.readLine();
            }

            diccionarisCarregats.put(diccionariName, diccionari);
            bf.close();

        } catch (Exception e) {
            System.out.println("Error al llegir dicionari! " + e);
        }

    }

    public int getRANDOM_MAX() {
        return RANDOM_MAX;
    }

    public String addDiccionari(String[] diccionari){
        diccionarisCarregats.put("custom"+custom, diccionari);

        String[] aux = new String[idiomes.length+1];
        for (int i = 0; i < idiomes.length; i++) {
            aux[i] = idiomes[i];
        }
        aux[idiomes.length] = "custom"+custom;
        idiomes = aux;
        custom++;
        return "custom"+(custom-1);
    }

    public String[] getDiccionari(String diccionariKey) {
        return diccionarisCarregats.get(diccionariKey);
    }

    public int getDiccionariSize(String diccionariKey) {
        return diccionarisCarregats.get(diccionariKey).length;
    }

    public int getNumCPUs() {
        return numCPUs;
    }

    public ArrayList<String> getIdiomesCompare(String principal){
        ArrayList <String> idiomesCopy= new ArrayList<>();
        idiomesCopy.addAll(Arrays.asList(idiomes));
        idiomesCopy.remove(principal);
        return idiomesCopy;
    }
}
