import javax.swing.*;
import java.awt.*;

public class BuyButton extends JButton {
    private String symbol;

    BuyButton(String text, String symbol) {
        super(text);
        this.symbol = symbol;
        setPreferredSize(new Dimension(80,20));
        setForeground(new Color(40, 35, 65));
        setBackground(new Color(255,215,0));
        //setBorder(null);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
    }
}
