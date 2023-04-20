package P3.Controlador;

import P3.Main;
import P3.Model.Nuvol;
import P3.Model.Parells;
import P3.Model.Punt;

import static java.lang.Math.sqrt;

public class Controlador {
    private Main main;
    private Nuvol nuvol;
    public Controlador(Main main, Nuvol nuvol){
        this.main = main;
        this.nuvol = nuvol;
    }

    public Parells[] n2(Nuvol nuvol) {
        Punt[] punts = nuvol.getNuvol();
        Parells[] parells = new Parells[3];
        double distancia;
        for (int i = 0; i < punts.length; i++) {
            for (int j = 0; j < punts.length; j++) {
                if(i != j){
                    distancia = sqrt(Math.pow((punts[i].getPunt()[0]- punts[j].getPunt()[0]), 2)+ Math.pow((punts[i].getPunt()[1]- punts[j].getPunt()[1]), 2));
                    for (int y = 0; y < 3; y++) {
                        if(parells[y] == null || parells[y].getDistancia() >= distancia){
                            if(parells[y] != null && distancia == parells[y].getDistancia()){
                                break;
                            }
                            if(y ==2){
                                parells[y] = new Parells(punts[i],punts[j], distancia);
                            }
                        }else{
                            if (y != 0) {
                                parells[y - 1] = new Parells(punts[i], punts[j], distancia);
                            }
                            break;
                        }
                    }
                }
             }


        }
    return parells;
    }

    public Parells[] n(Nuvol nuvol){
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
        return parells;
    }

    private void merge(Nuvol auxnuvol1,Nuvol auxnuvol2,Nuvol nuvol ){
        int aux1 = 0;
        int aux2 = 0;
        Punt[] punts = nuvol.getNuvol();
        Punt[] auxpunts1 = auxnuvol1.getNuvol();
        Punt[] auxpunts2 = auxnuvol2.getNuvol();
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
    }
}
