package P3.Controlador;

import P3.Main;
import P3.Model.Nuvol;
import P3.Model.Parells;
import P3.Model.Punt;

import static java.lang.Math.sqrt;

public class Controlador extends Thread{
    private Main main;
    private Nuvol nuvol;
    private int algorisme;

    public Controlador(Main main, Nuvol nuvol){
        this.main = main;
        this.nuvol = nuvol;
        algorisme=0;
    }

    public void setAlgorisme(int algorisme) {
        this.algorisme = algorisme;
    }
    public void run(){
        long t1 = System.nanoTime();

        System.out.println("Controlodor starts");
        switch (algorisme){
            // Cas en el que s'ha sel·leccionat algorisme n^2
            case 0:
                n2(nuvol);
                break;

            // Cas en el que s'ha sel·leccionat algorisme nlog n
            case 1:
                n(nuvol);
                break;
        }

        Parells [] parells = nuvol.getParells();
        for (int i = 0; i < 3; i++) {
            if(parells[i] != null){
                System.out.println("Parella "+i+" :" +parells[i].getPunt1().toString() + " i " +parells[i].getPunt2().toString());
                System.out.println("Estan a una distància: "+parells[i].getDistancia());
            }
        }

        long t2=System.nanoTime();
        System.out.println("\u001B[40m"+"\u001B[36m" +"Temps d'execució: "+(t2-t1)+" nanosegons"+ "\u001B[0m");

        System.out.println("Controlador acaba");
        main.comunicacio("controladorAcaba");
    }

    public void n2(Nuvol nuvol) {
        Punt[] punts = nuvol.getNuvol();
        Parells[] parells = new Parells[3];
        double distancia;
        for (int i = 0; i < punts.length; i++) {
            // Controlar aturada del programa
            if(!Main.CONTINUAR){
                return;
            }
            for (int j = 0; j < punts.length; j++) {
                if(i != j){
                    distancia = sqrt(Math.pow((punts[i].getPunt()[0]- punts[j].getPunt()[0]), 2)+ Math.pow((punts[i].getPunt()[1]- punts[j].getPunt()[1]), 2));
                    for (int y = 0; y < 3; y++) {
                        if(parells[y] == null || parells[y].getDistancia() >= distancia){
                            if(parells[y] != null && distancia == parells[y].getDistancia()){
                                break;
                            }
                            if(y ==2){
                                for (int k = 0; k < y; k++) {
                                    parells[k] = parells[k+1];
                                }
                                parells[y] = new Parells(punts[i],punts[j], distancia);
                            }
                        }else{
                            if (y != 0) {
                                for (int k = 0; k < y; k++) {
                                    parells[k] = parells[k+1];
                                }
                                parells[y - 1] = new Parells(punts[i], punts[j], distancia);
                            }
                            break;
                        }
                    }
                }
             }


        }
        nuvol.setParells(parells);
        main.comunicacio("Actualitzar");
    //return parells;
    }

    public Parells[] n(Nuvol n){
        //primer ordenam el nuvol segons la x
        Nuvol nuvol = mergeSort(n);

        //una vegada el tenim ordenat tornam a fer "el mateix algorisme"
        //pero ara ens fixam en juntar per dimensió

        return mergeSortDistancia(nuvol);

    }

    public Parells[] mergeSortDistancia(Nuvol nuvol){
        // Controlar aturada del programa
        if(!Main.CONTINUAR){
            return null;
        }

        Punt[] punts = nuvol.getNuvol();
        Parells[] parells = new Parells[3];
        if(punts.length > 1){
            //crear un nou nuvol de punts consistent amb la primera meitat de punts del nuvol original
            Punt[] auxpunts1 = new Punt[punts.length/2 ];
            System.arraycopy(punts, 0, auxpunts1, 0, punts.length / 2);
            Nuvol auxnuvol1 = new Nuvol(punts.length/2, nuvol.getMax());
            auxnuvol1.setNuvol(auxpunts1);
            mergeSortDistancia(auxnuvol1);

            //crear un nou nuvol de punts consistent amb la segona meitat de punts del nuvol original
            Punt[] auxpunts2 = new Punt[punts.length- auxpunts1.length];
            System.arraycopy(punts, punts.length / 2, auxpunts2, 0, auxpunts2.length);
            Nuvol auxnuvol2 = new Nuvol(punts.length- auxpunts1.length, nuvol.getMax());
            auxnuvol2.setNuvol(auxpunts2);
            mergeSortDistancia(auxnuvol2);

            mergeNuvol(auxnuvol1, auxnuvol2, nuvol);
        }
        main.comunicacio("Actualitzar");
        return parells;
    }

    private void mergeNuvol(Nuvol auxnuvol1,Nuvol auxnuvol2,Nuvol nuvol ){
        int aux1 = 0;
        int aux2 = 0;
        Punt[] punts = nuvol.getNuvol();
        Punt[] auxpunts1 = auxnuvol1.getNuvol();
        Punt[] auxpunts2 = auxnuvol2.getNuvol();
        //si estam juntant dos nuvols amb només un punt a cada un aleshores
        //la parella formada per aquest dos punts serà la mes propera
        if(auxpunts1.length == auxpunts2.length && auxpunts1.length == 1){
            double distancia = sqrt(Math.pow((auxpunts1[0].getPunt()[0]- auxpunts2[0].getPunt()[0]), 2)+ Math.pow((auxpunts1[0].getPunt()[1]- auxpunts2[0].getPunt()[1]), 2));
            Parells[] parells = new Parells[3];
            parells[2] = new Parells(auxpunts1[0], auxpunts2[0], distancia);
            nuvol.setParells(parells);
            return;
        }
        Parells[] parells = nuvol.getParells();
        Parells[] auxparells1 = auxnuvol1.getParells();
        Parells[] auxparells2 = auxnuvol2.getParells();
        aux1 = 2;
        aux2 = 2;

        //aqui juntam les dues llistes de distancies minimes com si fos un merge normal
        for (int i = 2; i >= 0; i--) {
            if(auxparells2[aux2] == null){
                parells[i] = auxparells1[aux1];
                aux1--;
            }else if(auxparells1[aux1] == null || auxparells1[aux1].getDistancia() > auxparells2[aux2].getDistancia()){
                parells[i] = auxparells2[aux2];
                aux2--;
            }else{
                parells[i] = auxparells1[aux1];
                aux1--;
            }
        }
        nuvol.setParells(parells);
        //posam el primer index a la part de més a la dreta de l'array de l'esquerra
        //i el segon index a la part de més a l'esquerra de l'array de la dreta
        int i = auxpunts1.length-1;
        int j = 0;
        double distancia;
        double puntmig = (auxpunts1[i].getPunt()[0] + auxpunts2[j].getPunt()[0]) / 2;
        while(parells[0] == null || parells[0].getDistancia()>= puntmig-auxpunts1[i].getPunt()[0] ){
            while(parells[0] == null ||parells[0].getDistancia()>= auxpunts2[j].getPunt()[0] - puntmig ){

                distancia = sqrt(Math.pow((auxpunts1[i].getPunt()[0]- auxpunts2[j].getPunt()[0]), 2)+ Math.pow((auxpunts1[i].getPunt()[1]- auxpunts2[j].getPunt()[1]), 2));
                for (int y = 0; y < 3; y++) {
                    if(parells[y] == null || parells[y].getDistancia() >= distancia){
                        if(parells[y] != null && distancia == parells[y].getDistancia()){
                            break;
                        }
                        if(y ==2){
                            for (int k = 0; k < y; k++) {
                                parells[k] = parells[k+1];
                            }
                            parells[y] = new Parells(auxpunts1[i],auxpunts2[j], distancia);
                        }
                    }else{
                        if (y != 0) {
                            for (int k = 0; k < y; k++) {
                                parells[k] = parells[k+1];
                            }
                            parells[y - 1] = new Parells(auxpunts1[i], auxpunts2[j], distancia);
                        }
                        break;
                    }
                }
                j++;
                if(j== auxpunts2.length){
                    j = 0;
                    break;

                }
            }

            i--;
            if(i== -1){
                break;
            }
        }
        nuvol.setParells(parells);
    }
    public Nuvol mergeSort(Nuvol nuvol){
        Punt[] punts = nuvol.getNuvol();
        Parells[] parells = new Parells[3];
        if(punts.length > 1){
            //crear un nou nuvol de punts consistent amb la primera meitat de punts del nuvol original
            Punt[] auxpunts1 = new Punt[punts.length/2 ];
            System.arraycopy(punts, 0, auxpunts1, 0, punts.length / 2);
            Nuvol auxnuvol1 = new Nuvol(punts.length/2, nuvol.getMax());
            auxnuvol1.setNuvol(auxpunts1);
            n(auxnuvol1);

            //crear un nou nuvol de punts consistent amb la segona meitat de punts del nuvol original
            Punt[] auxpunts2 = new Punt[punts.length- auxpunts1.length];
            System.arraycopy(punts, punts.length / 2, auxpunts2, 0, auxpunts2.length);
            Nuvol auxnuvol2 = new Nuvol(punts.length- auxpunts1.length, nuvol.getMax());
            auxnuvol2.setNuvol(auxpunts2);
            n(auxnuvol2);

            merge(auxnuvol1, auxnuvol2, nuvol);
        }
        return nuvol;
    }

    private void merge(Nuvol auxnuvol1,Nuvol auxnuvol2,Nuvol nuvol ){
        int aux1 = 0;
        int aux2 = 0;
        Punt[] punts = nuvol.getNuvol();
        Punt[] auxpunts1 = auxnuvol1.getNuvol();
        Punt[] auxpunts2 = auxnuvol2.getNuvol();

        //junta les dues llistes de punts per una coordenada seguint el MergeSort
        for (int i = 0; i < punts.length; i++) {
            if(auxpunts1.length == aux1){
                punts[i] = auxpunts2[aux2];
                aux2++;
            } else if (auxpunts2.length == aux2) {
                punts[i] = auxpunts1[aux1];
                aux1++;
            }else if(auxpunts1[aux1].getPunt()[0] > auxpunts2[aux2].getPunt()[0]){
                punts[i] = auxpunts2[aux2];
                aux2++;
            }else{
                punts[i] = auxpunts1[aux1];
                aux1++;
            }
        }



    }
}
