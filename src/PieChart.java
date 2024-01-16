import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PieChart extends JPanel {
    private List<Float> values;
    private List<Color> colors;

    public PieChart(List<Float> values, List<Color> colors) {
        this.values = values;
        this.colors = colors;
        setPreferredSize(new Dimension(265,265));
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

                g.setColor(Color.BLACK);
                g.setFont(new Font("Ubuntu",Font.BOLD, 12));
                double rad = Math.toRadians(startAngle + angle / 2);
                int textX = (int) (x + radius + radius / 2 * Math.cos(rad));
                int textY = (int) (y + radius - radius / 2 * Math.sin(rad));
                String wartosc = String.format("%.2f",value) + "$";
                g.drawString(wartosc, textX, textY);

                startAngle += angle;
            }
        }
    }
}
