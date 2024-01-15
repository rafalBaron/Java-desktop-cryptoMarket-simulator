import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PieChart extends JPanel {
    private List<Float> values;
    private List<Color> colors;

    public PieChart(List<Float> values, List<Color> colors) {
        this.values = values;
        this.colors = colors;
        setPreferredSize(new Dimension(360,360));
        setBackground(new Color(40, 35, 65));
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

        float total = values.stream().reduce(Float::sum).orElse(0.0f);

        if (total == 0) {
            g.setColor(Color.GRAY);
            g.fillArc(x, y, diameter, diameter, 0, 360);
        } else {
            float startAngle = 0.0f;
            for (int i = 0; i < values.size(); i++) {
                float value = values.get(i);
                float angle = (value / total) * 360.0f;
                g.setColor(colors.get(i));
                g.fillArc(x, y, diameter, diameter, (int) startAngle, (int) angle);

                startAngle += angle;
            }
        }
    }
}
