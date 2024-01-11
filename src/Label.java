import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class Label extends JLabel {
    String text;
    Font customFont;

    Label(String text) {
        super(text);
        this.setForeground(new Color(255,255,255));
        this.setFont(new Font("Ubuntu",Font.BOLD,16));
    }
}
