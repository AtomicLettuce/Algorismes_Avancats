package P2.Model;

public class Cavaller extends Peca{
    public Cavaller(int x, int y) {
        super(x, y); // Posar moviments i mandangón
        movx = new int[16];
        movy = new int[16];
        int pos = 0;
        movx[pos] = 1;
        movy[pos++] = 2;
        movx[pos] = 2;
        movy[pos++] = 1;
        movx[pos] = 1;
        movy[pos++] = -2;
        movx[pos] = 2;
        movy[pos++] = -1;
        movx[pos] = -1;
        movy[pos++] = 2;
        movx[pos] = -2;
        movy[pos++] = 1;
        movx[pos] = -1;
        movy[pos++] = -2;
        movx[pos] = -2;
        movy[pos++] = -1;
        movx[pos] = 1;
        movy[pos++] = 0;
        movx[pos] = 1;
        movy[pos++] = -1;
        movx[pos] = 0;
        movy[pos++] = -1;
        movx[pos] = -1;
        movy[pos++] = -1;
        movx[pos] = -1;
        movy[pos++] = 0;
        movx[pos] = -1;
        movy[pos++] = 1;
        movx[pos] = 0;
        movy[pos++] = 1;
        movx[pos] = 1;
        movy[pos++] = 1;

    }
}
