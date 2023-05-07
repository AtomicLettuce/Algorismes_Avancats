package P5.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Model {
    HashMap<String, HashSet> diccionarisCarregats = new HashMap<>();
    final String[] idiomes = {
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

    public HashSet getDiccionari(String diccionariKey) {
        return diccionarisCarregats.get(diccionariKey);
    }

    public int getDiccionariSize(String diccionariKey) {
        return diccionarisCarregats.get(diccionariKey).size();
    }

    public ArrayList getIdiomesCompare(String principal){
        ArrayList idiomesCopy= new ArrayList<>();
        idiomesCopy.addAll(Arrays.asList(idiomes));
        idiomesCopy.remove(principal);
        return idiomesCopy;
    }
}
