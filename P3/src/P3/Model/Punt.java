package P3.Model;

import java.util.Arrays;

public class Punt {
    private double[] punt;

    public Punt(double[] punt) {
        this.punt = punt;
    }

    public double[] getPunt() {
        return punt;
    }

    public void setPunt(double[] punt) {
        this.punt = punt;
    }

    @Override
    public String toString() {
        return "(" + punt[0] + " " + punt[1] + ")";
    }
}
