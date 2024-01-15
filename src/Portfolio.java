import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

public class Portfolio extends JPanel{
    Account userAccount;

    Portfolio(BorderLayout borderLayout,Account userAccount) throws IOException {
        super();
        this.userAccount = userAccount;
        this.setBackground(new Color(31,27,54));
        List<CryptoCurrency> listaUsera = userAccount.getWallet().getCryptoCurrencies();

        RoundedPanel baner = new RoundedPanel(50,new Color(255,215,0),730,120);

        RoundedPanel chart = new RoundedPanel(50,new Color(40, 35, 65),496, 370);
        List<Float> values = new ArrayList<>();
        List<Color> colors = new ArrayList<>();
        if (listaUsera != null) {
            for (CryptoCurrency c : listaUsera) {
                values.add(c.getQuantity());
                colors.add(CryptoColorDictionary.getCryptoColor(c.getSymbol()));
            }
            PieChart pieChart = new PieChart(values, colors);
            chart.add(pieChart);
        }else {
            PieChart pieChart = new PieChart(values, colors);
            chart.add(pieChart);
        }

        RoundedPanel list = new RoundedPanel(50,new Color(40, 35, 65),215, 370);
        list.add(new ListHeader(),BorderLayout.PAGE_START);
        JPanel listContent = new JPanel();
        listContent.setLayout(new BoxLayout(listContent,BoxLayout.Y_AXIS));
        if (listaUsera != null) {
            for (CryptoCurrency crypto : listaUsera
            ) {
                listContent.add(new ListedCrypto(crypto.getSymbol(), crypto.getQuantity(), crypto.getPurchasePrice()));
                JPanel vGap = new JPanel();
                vGap.setPreferredSize(new Dimension(215,10));
                vGap.setBackground(new Color(40, 35, 65));
                listContent.add(vGap);
            }
        }
        list.add(listContent);

        BanerInfo info = new BanerInfo(userAccount);
        baner.add(info);
        add(baner, BorderLayout.NORTH);

        JPanel vGap = new JPanel();
        vGap.setPreferredSize(new Dimension(735,7));
        vGap.setBackground(new Color(31,27,54));
        add(vGap, BorderLayout.NORTH);

        add(chart, BorderLayout.WEST);

        JPanel hGap = new JPanel();
        hGap.setPreferredSize(new Dimension(10,375));
        hGap.setBackground(new Color(31,27,54));
        add(hGap);

        add(list, BorderLayout.EAST);

        setBorder(new EmptyBorder(21,0,0,0));

    }

}