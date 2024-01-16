import javax.swing.*;
import java.awt.*;

public class LabelCryptoList extends JLabel {

    LabelCryptoList(String text, Color color) {
        super(text);
        setForeground(color);
        setPreferredSize(new Dimension(60,40));
        setBorder(BorderFactory.createEmptyBorder(0,0,18,0));
    }
}