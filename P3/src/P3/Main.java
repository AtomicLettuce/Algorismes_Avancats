package P3;

import P3.Controlador.Controlador;
import P3.Interficies.InterficieComunicacio;
import P3.Model.Nuvol;
import P3.Model.Parells;

import P3.Model.Punt;

import P3.Vista.Vista;


public class Main implements InterficieComunicacio {
    private Vista vista;
    private Nuvol nuvol;
    Controlador controlador;
    public static boolean CONTINUAR = true;
    public static void main(String[] args) {
        new Main().inici();
    }

    public void inici(){
        vista=new Vista("Mondongo",this,nuvol);
        nuvol = new Nuvol(10000,10);
        controlador = new Controlador(this, nuvol);

        long start1 = System.nanoTime();

        //Parells parells[] = controlador.n2(nuvol);


        /*controlador.n(nuvol);
        Parells parells[] = nuvol.getParells();


        for (int i = 0; i < 3; i++) {
            if(parells[i] != null){
                System.out.println(parells[i].getPunt1().toString() + " i " +parells[i].getPunt2().toString());
                System.out.println(parells[i].getDistancia());
            }
        }
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: "+ (end1-start1));*/


    }

    @Override
    public void comunicacio(String instruccio) {
        switch (instruccio){
            case "stop":
                System.out.println("Aturant...");
                // Aturar tots els fils i aturar el programa
                CONTINUAR=false;
                vista.actualitzar();
                break;
            case "Actualitzar":
                // Tornar a pintar la GUI
                vista.actualitzar();
                break;
            case "play":
                // Envia l'ordre de començar

                // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]

                break;
            case "reset":
                // Envia l'ordre de reinici

                // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]
                break;
        }


        // Per canviar quantitat de punts que generam i algorisme que empram per resoldre-ho
        // Fromat:
        // opcions: 'n' 'a' 'd'
        // sent n: numero de punts
        // a: algorisme a emprar (n^2, nlogn...)
        // d: distrubució aleatòria (gausiana, equiprobable, chi^2, etc.)
        if (instruccio.startsWith("opcions:")){
            String aux=instruccio.split(":")[1];
            String [] aux2=aux.split(" ");
            System.out.println(aux2.length);

            // Canviam valor de 'n'
            nuvol.setDimensio(Integer.parseInt(aux2[1]));
            // Canviam algorisme sel·leccionat
            controlador.setAlgorisme(Integer.parseInt(aux2[2]));
            // Canviam distribucio aleatòria
            nuvol.setDistribucio(Integer.parseInt(aux2[3]));

            nuvol.generarNuvol();
            comunicacio("Actualitzar");
        }
    }
}
