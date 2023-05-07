package P4.Vista;



import javax.swing.*;
import java.awt.*;

public class PanellMapa extends JPanel {

    public PanellMapa(int width, int height) {
        super();
        this.setPreferredSize(new Dimension(width, height));
    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2= (Graphics2D)g;
        g2.setStroke(new BasicStroke(2));

    }


}

