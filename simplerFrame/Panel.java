package CodeRoots.simplerFrame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Panel extends JPanel {
    int width = 160;
    int height = 200;

    Image[] imageAr;

    Thread thread;
    Image image;
    Graphics g;

    public Panel() {
        super();

        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        this.setSize(new Dimension(width, height));
    }

    public void drwGm() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }
}