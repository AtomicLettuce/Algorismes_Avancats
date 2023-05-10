package P5.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Model {
    int custom = 0;
    HashMap<String, HashSet> diccionarisCarregats = new HashMap<>();
     String[] idiomes = {
            "alemany",
            "angles_EEUU",
            "angles_GB",
            "catala",
            "espanyol",
            "euskera",
            "frances",
            "holandes",
            "italia",
            "portugues"
    };
    private int numCPUs = Runtime.getRuntime().availableProcessors();
    public void carregaDiccionari(String diccionariName) {
        //Si ja s'ha carregat el diccionari no fa falta tornar a carregarlo
        if (diccionarisCarregats.containsKey(diccionariName)){
            return;
        }

        //Si no ha estat carregat es carrega
        HashSet diccionari = new HashSet();
        try {

            BufferedReader bf = new BufferedReader(new FileReader("../P5/src/P5/Model/Diccionaris/" + diccionariName + ".dic"));

            String word = bf.readLine();
            while (word != null) {
                diccionari.add(word);
                word = bf.readLine();
            }
            diccionarisCarregats.put(diccionariName, diccionari);
            bf.close();

        } catch (Exception e) {
            System.out.println("Error al llegir dicionari! " + e);
        }

    }

    public String addDiccionari(HashSet<String> diccionari){
        diccionarisCarregats.put("custom"+custom, diccionari);

        String[] aux = new String[idiomes.length+1];
        for (int i = 0; i < idiomes.length; i++) {
            aux[i] = idiomes[i];
        }
        aux[idiomes.length] = "custom"+custom;
        idiomes = aux;
        custom++;
        return "cusotm"+(custom-1);
    }

    public HashSet getDiccionari(String diccionariKey) {
        return diccionarisCarregats.get(diccionariKey);
    }

    public int getDiccionariSize(String diccionariKey) {
        return diccionarisCarregats.get(diccionariKey).size();
    }

    public int getNumCPUs() {
        return numCPUs;
    }

    public ArrayList getIdiomesCompare(String principal){
        ArrayList idiomesCopy= new ArrayList<>();
        idiomesCopy.addAll(Arrays.asList(idiomes));
        idiomesCopy.remove(principal);
        return idiomesCopy;
    }
}
