import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

public class Settings extends JPanel {

    Settings() throws IOException {
        super();
        this.setBackground(new Color(31,27,54));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.drawString("SETTINGS",300,300);
    }
}
