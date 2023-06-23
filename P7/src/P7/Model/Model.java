package P7.Model;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Model {
    HashMap<BigInteger, Integer> recuento = new HashMap<>();
    public File fitxer;

    private Boolean esPrimer;

    public Model() {
    }

    public Model(BigInteger[] numeros) {
        recuento = new HashMap<>();

        for (BigInteger numero : numeros) {
            if (recuento.containsKey(numero)) {
                int count = recuento.get(numero);
                recuento.put(numero, count + 1);
            } else {
                recuento.put(numero, 1);
            }
        }
    }

    public void addNumero(BigInteger numero) {
        if (recuento.containsKey(numero)) {
            int count = recuento.get(numero);
            recuento.put(numero, count + 1);
        } else {
            recuento.put(numero, 1);
        }
    }

    public String printNumeros() {
        String str="";
        for (Map.Entry<BigInteger, Integer> entry : recuento.entrySet()) {
            str=str+"\tfactor: --------> " + entry.getKey() + "  (x" + entry.getValue() + " )\n";
        }
        return str;
    }

    public void vaciarHashMap() {
        recuento.clear();
    }
}
