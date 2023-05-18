package P5.Vista;

import P5.Model.Model;

import javax.swing.*;
import java.awt.*;

public class PanellDibuix extends JPanel {

    private Model model;
    public PanellDibuix(int width, int height, Model model) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.model = model;
    }





}
