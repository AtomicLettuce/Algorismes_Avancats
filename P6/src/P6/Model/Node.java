package P6.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

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

    // Heurística com el nombre de peces mal colocades + els moviments que hem fet per arribar a aquesta distribució.
    private void calcularHeuristicaV1() {
        int heuristica = 0;
        int dimensioPuzzle = disposicio.getDimensioPuzzle();

        // Recorrem la matriu.
        for (int i = 0; i < dimensioPuzzle; i++) {
            for (int j = 0; j < dimensioPuzzle; j++) {
                // Si és el cantó inferior esquerra miram que sigui un 0
                if (i == dimensioPuzzle - 1 && j == dimensioPuzzle - 1) {
                    if (disposicio.getPosicio(i, j) != 0) {
                        heuristica++;
                    }
                // Si no és el 0 calculem el seu nombre.
                } else if (disposicio.getPosicio(i, j) != (i * dimensioPuzzle) + (j + 1)) {
                    heuristica++;
                }
            }
        }

        // Calculem el cost final de l'heurística.
        cost = heuristica + nMoviements;
        nMoviements++;
    }

    // Heurística distància manhattan per cada posició + número de moviments.
    private void calcularHeuristicaV2() {
        int heuristica = 0;
        int dimensioPuzzle = disposicio.getDimensioPuzzle();

        // Recorrem la matriu.
        for (int i = 0; i < dimensioPuzzle; i++) {
            for (int j = 0; j < dimensioPuzzle; j++) {
                // Obtenim el numero de la posició actual.
                int valorActual = disposicio.getPosicio(i, j);
                // Decalarem les posicions on s'hauria de trobar el numero.
                int iObjetiu;
                int jObjetiu;
                // Si no és el 0 calculem el seu nombre.
                if (valorActual != 0) {
                    iObjetiu = (valorActual - 1) / dimensioPuzzle;
                    jObjetiu = (valorActual - 1) % dimensioPuzzle;
                // Si és 0 el seu lloc és el cantó inferior esquerra.
                } else {
                    iObjetiu = dimensioPuzzle - 1;
                    jObjetiu = dimensioPuzzle - 1;
                }
                // Calculem la distància de Manhattan com la diferència de longituds.
                heuristica += Math.abs(i - iObjetiu) + Math.abs(j - jObjetiu);
            }
        }

        // Calculem el cost final de l'heurística,
        this.cost = heuristica*this.disposicio.getDimensioPuzzle() + nMoviements;
        nMoviements++;
    }

    private void calcularHeuristicaV3() {
        int heuristica = 0;
        int dimensioPuzzle = disposicio.getDimensioPuzzle();

        // Recorrem la matriu.
        for (int i = 0; i < dimensioPuzzle; i++) {
            for (int j = 0; j < dimensioPuzzle; j++) {
                // Obtenim el numero de la posició actual.
                int valorActual = disposicio.getPosicio(i, j);
                // Decalarem les posicions on s'hauria de trobar el numero.
                int iObjetiu;
                int jObjetiu;
                // Si no és el 0 calculem el seu nombre.
                if (valorActual != 0) {
                    iObjetiu = (valorActual - 1) / dimensioPuzzle;
                    jObjetiu = (valorActual - 1) % dimensioPuzzle;
                    // Si és 0 el seu lloc és el cantó inferior esquerra.
                } else {
                    iObjetiu = dimensioPuzzle - 1;
                    jObjetiu = dimensioPuzzle - 1;
                }
                // Calculem la distància de Manhattan com la diferència de longituds.
                heuristica += Math.abs(i - iObjetiu) + Math.abs(j - jObjetiu);
            }
        }

        // Calculem el cost final de l'heurística,
        this.cost = heuristica;
    }
    public void generarFills() {
        // Localitzem la posició del 0 per intercanviar.
        int[] posicioZero = this.localitzarZero();
        // Possibles moviments que pot fer el 0.
        int[][] offsets = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
        // Obtenim el puzle i la dimensió.
        int n = disposicio.getDimensioPuzzle();
        int[][] puzzle = disposicio.getPuzzle();

        // Provem les offsets per canviar.
        for (int[] offset : offsets) {
            // Obtenim les noves coordenades x i y.
            int filaVeina = posicioZero[0] + offset[0];
            int columnaVeina = posicioZero[1] + offset[1];

            // Si és un moviment vàlid intercanviar.
            if ((filaVeina >= 0 && filaVeina < n) && (columnaVeina >= 0 && columnaVeina < n)) {
                int[][] matriuIntercanviada = intercanviarPosicions(puzzle, posicioZero[0], posicioZero[1], filaVeina, columnaVeina);
                Estat nouEstat = new Estat(matriuIntercanviada);
                // Afegim els nous fills.
                fills.add(new Node(this, this.nMoviements, nouEstat, this.cost));
            }
        }
    }

    private int[][] intercanviarPosicions(int[][] matriu, int fila1, int columna1, int fila2, int columna2) {
        // Creem la nova matriu on farem l'intercanvi
        int[][] novaMatriu = new int[matriu.length][matriu[0].length];

        // Fem l'intercanvi de posicions
        int temp = matriu[fila1][columna1];
        novaMatriu[fila1][columna1] = matriu[fila2][columna2];
        novaMatriu[fila2][columna2] = temp;

        // Copiem la resta de valors de la matriu original a la nova matriu
        for (int i = 0; i < matriu[0].length; i++) {
            for (int j = 0; j < matriu[0].length; j++) {
                // Si no estem a la posició intercanviada, copiem el valor de la matriu original
                if (i != fila1 || j != columna1) {
                    if (i != fila2 || j != columna2) {
                        novaMatriu[i][j] = matriu[i][j];
                    }
                }
            }
        }

        return novaMatriu;
    }


    private int[] localitzarZero() {
        int[] posicioZero = new int[2];
        for(int i = 0; i < disposicio.getDimensioPuzzle(); i++) {
            for(int j = 0; j < disposicio.getDimensioPuzzle(); j++) {
                if(disposicio.getPosicio(i, j) == 0) {
                    posicioZero[0] = i;
                    posicioZero[1] = j;
                    return posicioZero;
                }
            }
        }

        return null;
    }

    // Opció per transformar en estat final i desordenar-ho N vegades.
    public void desordenarEstatFinal(int n) {
        // Carregam l'estat final
        this.disposicio = generarSolucio();
        // No repetim camins.
        HashSet<Node> nodesVisitats = new HashSet<>();
        nodesVisitats.add(this);
        // Necesari per agafar un fill aleatori.
        Random random = new Random();

        for(int it = 0; it < n; it++) {
            // Generam els fills i agafam un aleatori.
            generarFills();
            Node possibleFill = this.fills.get(random.nextInt(fills.size()));
            // Agafam un pel qual encara no hem passat.
            while(!nodesVisitats.add(possibleFill)) {
                possibleFill = this.fills.get(random.nextInt(fills.size()));
            }
            // Esborram els fills creats, sinó queden en memòria i no feim un camí.
            this.fills.clear();
            // Asisgnam la nova disposició i repetim.
            this.disposicio = possibleFill.disposicio;
        }
        // Quan hem acabat resetetjam les variables emprades per deixar-ho com un node inici.
        this.cost = 0;
        this.nMoviements = 0;
        this.fills.clear();
    }

    private Estat generarSolucio() {

        int dimensioPuzzle = disposicio.getDimensioPuzzle();
        int[][] Puzzle = new int[dimensioPuzzle][dimensioPuzzle];

        for (int i = 0; i < dimensioPuzzle; i++) {
            for (int j = 0; j < dimensioPuzzle; j++) {
                if (i == dimensioPuzzle - 1 && j == dimensioPuzzle - 1) {
                    Puzzle[i][j] = 0;
                } else {
                    Puzzle[i][j] = (i * dimensioPuzzle) + (j + 1);
                }
            }
        }

        System.out.println(Puzzle);

        return new Estat(Puzzle);
    }

    // Getters
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

    // ToString
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

    // Overrides necessaris per emprar Nodes amb un hashSet i el priorityQueue.
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
        return Arrays.deepHashCode(disposicio.getPuzzle());
    }

    @Override
    public int compareTo(Node other) {
        return this.getHeuristica() - other.getHeuristica();
    }
}
