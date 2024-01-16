import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SellButton extends JButton {
    private String symbol;

    SellButton(String text, String symbol) {
        super(text);
        this.symbol = symbol;
        setPreferredSize(new Dimension(50,20));
        setFont(new Font("Ubuntu",Font.BOLD,11));
        setForeground(new Color(40, 35, 65));
        setBackground(new Color(255,215,0));
        //setBorder(null);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
    }
}