package P2.Controlador;

import P2.Main;
import P2.Model.Peca;
import P2.Model.Tauler;

import java.util.ArrayList;

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
        for (int i = 0; i < tablero.getPeces().size(); i++) {
            colocades++;
            tablero.activarCasella(tablero.getPeces().get(i).getX(),tablero.getPeces().get(i).getY(), colocades,i);
        }

        colocarPieza(0);
        main.comunicacio("Actualitzar");
        System.out.println("Controlador Stops");
        System.out.println("X = "+tablero.getPeces().get(0).getX()+" Y= "+tablero.getPeces().get(0).getY());
        System.out.println(tablero);
    }

    private boolean colocarPieza(int torn){

        Peca aColocar = tablero.getPeces().get(torn);
        if (colocades < (tablero.getDim()*tablero.getDim())){

            for (int i = 0; i < aColocar.getMovx().length; i++) {
                //Feim es moviment
                aColocar.setX(aColocar.getX()+aColocar.getMovx()[i]);
                aColocar.setY(aColocar.getY()+aColocar.getMovy()[i]);

                if (tablero.estaLliure(aColocar.getX(),aColocar.getY())){
                    colocades++;

                    tablero.activarCasella(aColocar.getX(),aColocar.getY(), colocades, torn);
                    if (tablero.getIntents() % 100 == 0){
                        main.comunicacio("Actualitzar");
                    }

                    if (colocarPieza(((torn+1)%tablero.getPeces().size()))){ // alomillor fa falta restar 1
                        return true;
                    }
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
