package P2.Controlador;

import P2.Model.Peca;
import P2.Model.Tablero;

import java.util.ArrayList;

public class Controlador extends Thread{
    Tablero tablero;
    static int colocades;
    ArrayList<Peca> peces ;

    public Controlador(Tablero tablero, ArrayList<Peca> peces){
        this.tablero = tablero;
        this.peces = peces;
        colocades = 0;
    }
    @Override
    public void run() {
        for (int i = 0; i < peces.size(); i++) {
            colocades++;
            tablero.activarCasella(peces.get(i).getX(),peces.get(i).getY(), colocades);
        }

        colocarPieza(0);
    }

    private boolean colocarPieza(int torn){
        
        Peca aColocar = peces.get(torn);
        if (colocades < (tablero.getDim()*tablero.getDim())){

            for (int i = 0; i < aColocar.getMovx().length; i++) {
                //Feim es moviment
                aColocar.setX(aColocar.getX()+aColocar.getMovx()[i]);
                aColocar.setY(aColocar.getY()+aColocar.getMovy()[i]);

                if (tablero.estaLliure(aColocar.getX(),aColocar.getY())){
                    colocades++;

                    tablero.activarCasella(aColocar.getX(),aColocar.getY(), colocades);


                    if (colocarPieza(((torn+1)%peces.size()))){ // alomillor fa falta restar 1
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
            System.out.println(tablero.toString());
            return true;
        }
        return false;
    }
}
