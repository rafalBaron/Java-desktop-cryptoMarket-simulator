import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Market extends  JPanel {

    public Market() throws IOException {
        super();
        this.setBackground(new Color(31,27,54));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.drawString("MARKET",300,300);
    }
}
