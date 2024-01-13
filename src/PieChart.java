import javax.swing.*;
import java.awt.*;

public class PieChart extends JPanel {
    private float[] values;
    private Color[] colors;

    public PieChart(float[] values, Color[] colors) {
        this.values = values;
        this.colors = colors;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int diameter = Math.min(width, height) - 10;
        int radius = diameter / 2;
        int x = (width - diameter) / 2;
        int y = (height - diameter) / 2;

        float total = 0.0f;
        for (float value : values) {
            total += value;
        }

        if (total == 0) {
            g.setColor(Color.GRAY);
            g.fillArc(x, y, diameter, diameter, 0, 360);
        } else {
            float startAngle = 0.0f;
            for (int i = 0; i < values.length; i++) {
                float value = values[i];
                float angle = (value / total) * 360.0f;
                g.setColor(colors[i]);
                g.fillArc(x, y, diameter, diameter, (int) startAngle, (int) angle);

                startAngle += angle;
            }
        }
    }
}