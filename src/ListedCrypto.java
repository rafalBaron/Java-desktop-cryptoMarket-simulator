import javax.swing.*;
import javax.xml.stream.FactoryConfigurationError;
import java.awt.*;
import java.io.File;
import java.util.List;

public class ListedCrypto extends JPanel implements SellWindowListener{
    private final double buyPrice;
    private final JPanel cardPanel;

    ListedCrypto(String symbol,double quantity,double buyPrice,Account userAcc,JPanel cardPanel) {
        super();
        this.buyPrice = buyPrice;
        this.cardPanel = cardPanel;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(215,35));
        setBackground(new Color(44, 39, 73));
        /*ImageIcon icon = new ImageIcon("src/Img/"+symbol.toLowerCase()+".png");
        add(new JLabel(icon));*/
        setBorder(BorderFactory.createEmptyBorder(0,20,5,10));
        add(new LabelCryptoList(symbol,CryptoColorDictionary.getCryptoColor(symbol)));
        add(new LabelCryptoList(String.valueOf(quantity),Color.WHITE));

        SellButton sellButton = new SellButton("SELL",symbol);
        SellWindow listener = new SellWindow(symbol,userAcc,cardPanel);
        sellButton.addActionListener(listener);
        add(sellButton);
    }

    @Override
    public void onBuyComplete() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                cardPanel.revalidate();
                cardPanel.repaint();
                cardPanel.validate();
                cardPanel.doLayout();
            }
        });
    }
}
