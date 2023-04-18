package P3.Vista;

import P3.Model.Nuvol;

import javax.swing.*;
import java.awt.*;

public class PanellNuvol extends JPanel {
    private Nuvol nuvol;

    // Mida del tauler
    public PanellNuvol(int width, int height, Nuvol nuvol) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.nuvol = nuvol;
    }


}
