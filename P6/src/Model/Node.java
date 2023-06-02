package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Node implements Comparable<Node> {
    private int cost;
    private int nMoviements;
    private Estat disposicio;
    private Node nodePare;
    private ArrayList<Node> fills;

    public Node(Node pare, int movimentsAnteriors, Estat disp, int cost) {
        this.nodePare = pare;
        this.disposicio = disp;
        this.cost = cost;
        this.nMoviements = movimentsAnteriors;
        this.fills = new ArrayList<>();
        this.calcularHeuristicaV2();
    }

    // La nostra heurística es defineix com el nombre de peces mal colocades + els moviments que hem fet
    // per arribar a aquesta distribució.
    private void calcularHeuristicaV1() {
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

    private void calcularHeuristicaV2() {
        int heuristica = 0;

        for (int i = 0; i < disposicio.getDimensioPuzzle(); i++) {
            for (int j = 0; j < disposicio.getDimensioPuzzle(); j++) {
                int valorActual = disposicio.getPosicio(i, j);
                if (valorActual != 0) {
                    int iObjetivo = (valorActual - 1) / disposicio.getDimensioPuzzle();
                    int jObjetivo = (valorActual - 1) % disposicio.getDimensioPuzzle();
                    int distancia = Math.abs(i - iObjetivo) + Math.abs(j - jObjetivo);
                    heuristica += distancia;
                } else {
                    int iObjetivo = disposicio.getDimensioPuzzle() - 1;
                    int jObjetivo = 0;
                    int distancia = Math.abs(i - iObjetivo) + Math.abs(j - jObjetivo);
                    heuristica += distancia;
                }
            }
        }

        cost = heuristica + nMoviements * 2;
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
                Estat nouEstat = new Estat(matrizIntercambiada);
                if(nouEstat.esResoluble(nouEstat.getPuzzle())) {
                    fills.add(new Node(this, this.nMoviements, nouEstat, this.cost));
                }
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

    public boolean esResoluble() {
        return this.disposicio.esResoluble(this.disposicio.getPuzzle());
    }
    public Estat getDisposicio() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node aComparar = (Node) o;
        int n = this.disposicio.getDimensioPuzzle();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(this.disposicio.getPosicio(i,j) != aComparar.disposicio.getPosicio(i,j)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(disposicio.getPuzzle());
        return result;
    }

    @Override
    public int compareTo(Node other) {
        return this.getHeuristica() - other.getHeuristica();
    }
}
