import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class Label extends JLabel {
    String text;
    Font customFont;

    Label(String text) {
        super(text);
        setForeground(new Color(255, 215, 0));
        setFont(new Font("Ubuntu",Font.BOLD,16));
        setBackground(new Color(40, 35, 65));
        setOpaque(true);
        setPreferredSize(new Dimension(120,30));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }

}
