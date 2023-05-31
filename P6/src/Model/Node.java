package Model;

import java.util.ArrayList;

public class Node {
    private int cost;
    private int nMoviements;
    private Tauler disposicio;
    private Node nodePare;
    private ArrayList<Node> fills;

    public Node(Node pare, int movimentsAnteriors, Tauler disp, int cost) {
        this.nodePare = pare;
        this.disposicio = disp;
        this.cost = cost;
        this.nMoviements = movimentsAnteriors;
        this.fills = new ArrayList<>();
        this.calcularHeuristica();
    }

    // La nostra heurística es defineix com el nombre de peces mal colocades + els moviments que hem fet
    // per arribar a aquesta distribució.
    private void calcularHeuristica() {
        int heuristica = 0;

        for (int i = 0; i < disposicio.getDimensioPuzzle(); i++) {
            for (int j = 0; j < disposicio.getDimensioPuzzle(); j++) {
                if (i == disposicio.getDimensioPuzzle() - 1 && j == disposicio.getDimensioPuzzle() - 1) {
                    if (disposicio.getPosicio(i, j) != 0) {
                        heuristica++;
                    }
                } else if (disposicio.getPosicio(i, j) != (i * disposicio.getDimensioPuzzle()) + (j + 1)) {
                    heuristica++;
                }
            }
        }
        cost = heuristica + nMoviements;
        nMoviements++;
    }

    public void generarFills() {
        int[] posicioZero = this.localitzarZero();
        int[][] offsets = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
        int n = disposicio.getDimensioPuzzle();

        // Intercambia con las posiciones adyacentes válidas
        for (int[] offset : offsets) {
            int filaVecina = posicioZero[0] + offset[0];
            int columnaVecina = posicioZero[1] + offset[1];

            if (filaVecina >= 0 && filaVecina < n && columnaVecina >= 0 && columnaVecina < n) {
                int[][] matrizIntercambiada = intercambiarPosiciones(disposicio.getPuzzle(), posicioZero[0], posicioZero[1], filaVecina, columnaVecina);
                Tauler nouTauler = new Tauler(matrizIntercambiada);
                fills.add(new Node(this, this.nMoviements, nouTauler, this.cost));
            }
        }
    }

    private int[][] intercambiarPosiciones(int[][] matriz, int fila1, int columna1, int fila2, int columna2) {
        int[][] matrizIntercambiada = new int[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (i == fila1 && j == columna1) {
                    matrizIntercambiada[i][j] = matriz[fila2][columna2];
                } else if (i == fila2 && j == columna2) {
                    matrizIntercambiada[i][j] = matriz[fila1][columna1];
                } else {
                    matrizIntercambiada[i][j] = matriz[i][j];
                }
            }
        }
        return matrizIntercambiada;
    }

    private int[] localitzarZero() {
        for(int i = 0; i < disposicio.getDimensioPuzzle(); i++) {
            for(int j = 0; j < disposicio.getDimensioPuzzle(); j++) {
                if(disposicio.getPosicio(i, j) == 0) {
                    return new int[] {i,j};
                }
            }
        }

        // Error hem eliminat el 0 de qualque manera.
        return null;
    }

    public Node getPare() {
        return this.nodePare;
    }

    public int getHeuristica() {
        return this.cost;
    }

    public ArrayList<Node> getFills() {
        return this.fills;
    }

    public boolean esSolucio() {
        return this.disposicio.esSolucio();
    }

    public Tauler getDisposicio() {
        return this.disposicio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 0; i < disposicio.getDimensioPuzzle(); i++) {
            sb.append("[ ");
            for (int j = 0; j < disposicio.getDimensioPuzzle(); j++) {
                sb.append(disposicio.getPosicio(i, j));
                if (j < disposicio.getDimensioPuzzle() - 1) {
                    sb.append(" ");
                }
            }
            sb.append(" ]\n");
        }
        return sb.toString();
    }

}
