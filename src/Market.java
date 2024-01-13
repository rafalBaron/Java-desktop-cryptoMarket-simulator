import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Market extends JPanel {

    ArrayList<Crypto> features = new ArrayList<>();

    public Market() throws IOException {
        super();
        this.setBackground(new Color(31,27,54));
        this.setBorder(new EmptyBorder(21,0,0,0));
        features.add(new Crypto("BTC | USDT"));
        features.add(new Crypto("ETH | USDT"));
        features.add(new Crypto("BNB | USDT"));
        features.add(new Crypto("SOL | USDT"));
        features.add(new Crypto("XRP | USDT"));
        features.add(new Crypto("LTC | USDT"));

        JPanel kursy = new JPanel(new GridLayout(3,2,30,30));
        kursy.setBackground(new Color(31,27,54));
        for (Crypto c : features
             ) {
            kursy.add(c);
        }
        this.add(kursy, BorderLayout.CENTER);
    }

}
