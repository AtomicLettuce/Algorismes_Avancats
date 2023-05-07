package P4.Model;

public class Aresta {
    private Node apunta;
    private double valor;

    public Aresta(Node f, double v) {
        apunta = f;
        valor = v;
    }

    public Aresta(Node f) {
        apunta = f;
        valor = 0.0;
    }

    public Node apunta() {
        return apunta;
    }

    public double getValor() {
        return valor;
    }
}
