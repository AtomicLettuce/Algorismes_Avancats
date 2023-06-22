package P7.Model;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Model {
    HashMap<BigInteger, Integer> recuento = new HashMap<>();
    public File fitxer;

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

    public void printNumeros() {
        for (Map.Entry<BigInteger, Integer> entry : recuento.entrySet()) {
            System.out.println("\tfactor: --------> " + entry.getKey() + "  (x" + entry.getValue() + " )");
        }
    }

    public void vaciarHashMap() {
        recuento.clear();
    }
}
