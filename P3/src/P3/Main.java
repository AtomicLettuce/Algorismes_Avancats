package P3;

import P3.Controlador.Controlador;
import P3.Model.Nuvol;
import P3.Model.Parells;
import P3.Model.Punt;

public class Main {
    private Nuvol model;

    public static void main(String[] args) {
        new Main().inici();
    }

    public void inici(){
        Nuvol nuvol = new Nuvol(100,10);
        nuvol.generarNuvol();
        Punt[] aux = nuvol.getNuvol();

        for (int i = 0; i < 100; i++) {
            System.out.print(aux[i].toString()+" ");

            System.out.println();
        }

        Controlador controlador = new Controlador(this, nuvol);
        Parells parells[] = controlador.n2();

        for (int i = 0; i < 3; i++) {
            System.out.println(parells[i].getPunt1().toString() + " i " +parells[i].getPunt2().toString());
            System.out.println(parells[i].getDistancia());
        }
    }
}
