import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

class RoundedPanel extends JPanel {
    private int cornerRadius;
    private int width,height;
    private Color color;

    public RoundedPanel(int cornerRadius, Color color, int width, int height) {
        super();
        this.cornerRadius = cornerRadius;
        this.width = width;
        this.height = height;
        this.color = color;
        setPreferredSize(new Dimension(width,height));
        setOpaque(true);
    }

    public RoundedPanel(int cornerRadius, Color color, int width, int height,int layout) {
        super();
        this.cornerRadius = cornerRadius;
        this.width = width;
        this.height = height;
        this.color = color;
        this.setLayout(new BoxLayout(this,layout));
        setPreferredSize(new Dimension(width,height));
        setOpaque(true);
    }

    protected void paintComponent(Graphics g) {

        BufferedImage bi = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gb = bi.createGraphics();
        gb.setPaint(color);
        gb.fillRect(0, 0, this.width, this.height);
        gb.dispose();

        RoundRectangle2D r = new RoundRectangle2D.Float(0, 0, this.width, this.height, 25, 25);
        g.setClip(r);

        g.drawImage(bi, 0, 0, null);
    }
}