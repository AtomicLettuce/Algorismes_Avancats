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

    public Parells[] n2() {
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
                                System.out.println("Iguals"+ parells[y].getPunt1().toString() +parells[y].getPunt2().toString() );
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
}
