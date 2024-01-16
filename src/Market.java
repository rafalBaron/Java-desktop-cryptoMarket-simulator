import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Market extends JPanel {

    ArrayList<CryptoBaner> features = new ArrayList<>();
    Account userAcc = null;
    JPanel cardPanel;

    public Market(Account userAcc, JPanel cardPanel) throws IOException {
        super();
        this.userAcc = userAcc;
        this.cardPanel = cardPanel;
        this.setBackground(new Color(31,27,54));
        this.setBorder(new EmptyBorder(21,0,0,0));
        features.add(new CryptoBaner("BTC | USDT", userAcc, cardPanel));
        features.add(new CryptoBaner("ETH | USDT", userAcc, cardPanel));
        features.add(new CryptoBaner("BNB | USDT", userAcc, cardPanel));
        features.add(new CryptoBaner("SOL | USDT", userAcc, cardPanel));
        features.add(new CryptoBaner("XRP | USDT", userAcc, cardPanel));
        features.add(new CryptoBaner("LTC | USDT", userAcc, cardPanel));

        JPanel kursy = new JPanel(new GridLayout(3,2,30,30));
        kursy.setBackground(new Color(31,27,54));
        for (CryptoBaner c : features
             ) {
            kursy.add(c);
        }
        this.add(kursy, BorderLayout.CENTER);
    }

}
