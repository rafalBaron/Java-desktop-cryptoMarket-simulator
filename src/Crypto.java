import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class Crypto extends JPanel {

    String exchange;
    int x,y;

    Crypto(String exchange){
        super();
        this.exchange = exchange;
        this.setPreferredSize(new Dimension(350,150));
        this.add(new Label(this.exchange));
    }

    protected void paintComponent(Graphics g) {

        BufferedImage bi = new BufferedImage(350, 150, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gb = bi.createGraphics();
        gb.setPaint(new Color(59, 53, 97));
        gb.fillRect(0, 0, 350, 150);
        gb.dispose();

        RoundRectangle2D r = new RoundRectangle2D.Float(0, 0, 350, 150, 40, 40);
        g.setClip(r);

        g.drawImage(bi, 0, 0, null);
    }

    private static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x,y,width-1,height-1,radius,radius);
        }
    }
}


