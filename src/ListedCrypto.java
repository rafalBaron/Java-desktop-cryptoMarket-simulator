import javax.swing.*;
import javax.xml.stream.FactoryConfigurationError;
import java.awt.*;
import java.io.File;
import java.util.List;

public class ListedCrypto extends JPanel {
    private final double buyPrice;
    ListedCrypto(String symbol,double quantity,double buyPrice) {
        super();
        this.buyPrice = buyPrice;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(215,35));
        setBackground(new Color(44, 39, 73));
        /*ImageIcon icon = new ImageIcon("src/Img/"+symbol.toLowerCase()+".png");
        add(new JLabel(icon));*/
        setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
        add(new LabelCryptoList(symbol,CryptoColorDictionary.getCryptoColor(symbol)));
        add(new LabelCryptoList(String.valueOf(quantity),Color.WHITE));
        add(new LabelCryptoList("",Color.WHITE));

    }

}
