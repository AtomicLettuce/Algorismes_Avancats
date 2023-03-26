package P2.Controlador;

import P2.Main;
import P2.Model.Peca;
import P2.Model.Tauler;


public class Controlador extends Thread{
    private Tauler tablero;
    static int colocades;
    private Main main;


    public Controlador(Main main,Tauler tablero){
        this.tablero = tablero;
        colocades = 0;
        this.main = main;
    }
    @Override
    public void run() {

        System.out.println("Controlador Starts");

        //Colocam les peces a les posicions inicials
        for (int i = 0; i < tablero.getPeces().size(); i++) {
            colocades++;
            tablero.activarCasella(tablero.getPeces().get(i).getX(),tablero.getPeces().get(i).getY(), colocades,i);
        }

        if(!colocarPieza(0)){//Si no hi ha solució
            main.comunicacio("noSolution");
        }

        //Quan acaba mostra el resultat
        main.comunicacio("Actualitzar");
        System.out.println("Controlador Stops");
    }

    //Funció recursiva per realitzar el backtracking
    private boolean colocarPieza(int torn){

        //Per si l'usuari vol aturar l'intent
        if (!Main.CONTINUAR){
            return false;
        }

        Peca aColocar = tablero.getPeces().get(torn); // Agafam la peća a colocar a aquest torn
        if (colocades < (tablero.getDim()*tablero.getDim())){ //Si no estan totes les caselles ocupades

            for (int i = 0; i < aColocar.getMovx().length; i++) { //Provam tots els moviments possibles d'aquesta peća a aquest torn
                //Feim es moviment
                aColocar.setX(aColocar.getX()+aColocar.getMovx()[i]);
                aColocar.setY(aColocar.getY()+aColocar.getMovy()[i]);

                if (tablero.estaLliure(aColocar.getX(),aColocar.getY())){ //Si és un moviment vàlid
                    colocades++;

                    tablero.activarCasella(aColocar.getX(),aColocar.getY(), colocades, torn);
                    if (tablero.getIntents() % 100000 == 0){ //Pintam el resultat
                        main.comunicacio("Actualitzar");
                    }

                    if (colocarPieza(((torn+1)%tablero.getPeces().size()))){  //Pasam al torn de la següent peća
                        return true;
                    }
                    //El moviment no és bo i tornam enrera
                    tablero.desactivarCasella(aColocar.getX(),aColocar.getY());
                    colocades--;
                }

                //Desfeim es moviment
                aColocar.setX(aColocar.getX()-aColocar.getMovx()[i]);
                aColocar.setY(aColocar.getY()-aColocar.getMovy()[i]);
            }
        }else{
            return true;
        }
        return false;
    }
}
