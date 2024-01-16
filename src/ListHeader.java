import javax.swing.*;
import java.awt.*;

public class ListHeader extends JPanel {
    ListHeader() {
        super();
        setPreferredSize(new Dimension(215,50));
        setBackground(new Color(40, 35, 65));
        add(new LabelHere("Symbol",Color.WHITE));
        add(new LabelHere("Quantity",Color.WHITE));
        add(new LabelHere("Worth",Color.WHITE));
    }

    public static class LabelHere extends JLabel {

        LabelHere(String text, Color color) {
            super(text);
            setForeground(color);
            setBackground(new Color(40, 35, 65));
            setPreferredSize(new Dimension(60,30));
            setFont(new Font("Ubuntu",Font.BOLD,12));
            setBorder(BorderFactory.createEmptyBorder(5,10,0,0));
            setOpaque(true);
        }
    }
}
