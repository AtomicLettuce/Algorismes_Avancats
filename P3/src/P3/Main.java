package P3;

import P3.Controlador.Controlador;
import P3.Model.Nuvol;
import P3.Model.Parells;

public class Main {
    private Nuvol model;

    public static void main(String[] args) {
        new Main().inici();
    }

    public void inici(){
        Nuvol nuvol = new Nuvol(10,10);
        nuvol.generarNuvol();
        double[][] aux = nuvol.getNuvol();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(aux[i][j]+" ");
            }
            System.out.println();
        }

        Controlador controlador = new Controlador(this, nuvol);
        Parells parells[] = controlador.n2();

        for (int i = 0; i < 3; i++) {
            System.out.println(parells[i].getPunt1()[0] + " " +parells[i].getPunt1()[1]);
            System.out.println(parells[i].getPunt2()[0] + " " +parells[i].getPunt2()[1]);
            System.out.println(parells[i].getDistancia());
        }
    }
}
