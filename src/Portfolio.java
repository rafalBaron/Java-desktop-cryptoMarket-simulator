import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

public class Portfolio extends JPanel {
    Account userAccount;
    JPanel cardpanel;
    RoundedPanel baner, chart, list;

    Portfolio(BorderLayout borderLayout, Account userAccount,JPanel cardPanel) throws IOException {
        super();
        this.userAccount = userAccount;
        this.cardpanel = cardPanel;
        this.setBackground(new Color(31,27,54));

        initializeComponents();
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    initializeComponents();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        timer.setRepeats(true);
        timer.start();


        setBorder(new EmptyBorder(21,0,0,0));
    }

    private void initializeComponents() throws IOException {
        DBHandler db = new DBHandler();
        Account actualAcc = db.getUser(userAccount.getUserName());
        List<CryptoCurrency> listaUsera = actualAcc.getWallet().getCryptoCurrencies();

        removeAll();

        baner = new RoundedPanel(50,new Color(255,215,0),730,120);
        BanerInfo info = new BanerInfo(actualAcc);
        baner.add(info);
        add(baner);

        JPanel vGap2 = new JPanel();
        vGap2.setPreferredSize(new Dimension(735,5));
        vGap2.setBackground(new Color(31,27,54));
        add(vGap2, BorderLayout.NORTH);

        chart = new RoundedPanel(50,new Color(40, 35, 65),496, 300);
        chart.setBorder(new EmptyBorder(10, 0, 0, 0));
        RoundedPanel walletInfo = new RoundedPanel(30,new Color(51, 44, 84),150,260);
        JLabel holdings = new JLabel("Holdings");
        holdings.setFont(new Font("Ubuntu",Font.BOLD,22));
        holdings.setForeground(Color.WHITE);

        JPanel hGap4 = new JPanel();
        hGap4.setPreferredSize(new Dimension(150,15));
        hGap4.setBackground(new Color(51, 44, 84));

        JLabel holdingBoughtFor = new JLabel("You payed");
        holdingBoughtFor.setFont(new Font("Ubuntu",Font.BOLD,15));
        holdingBoughtFor.setForeground(new Color(255,215,0));

        Double payed = 0.0;
        if(listaUsera != null ) {
            for (CryptoCurrency c : listaUsera
            ) {
                payed += c.getQuantity() * c.getPurchasePrice();
            }
        }

        JLabel cost = new JLabel(String.format("%.2f", payed) + " $");
        cost.setFont(new Font("Ubuntu",Font.BOLD,16));
        cost.setForeground(new Color(255, 255, 255));
        cost.setPreferredSize(new Dimension(170,30));
        cost.setHorizontalAlignment(SwingConstants.CENTER);
        cost.setVerticalAlignment(SwingConstants.NORTH);
        cost.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));

        JLabel holdingWorth = new JLabel("Worth now");
        holdingWorth.setFont(new Font("Ubuntu",Font.BOLD,15));
        holdingWorth.setForeground(new Color(255,215,0));

        Double worthVal = 0.0;
        if(listaUsera != null ) {
            for (CryptoCurrency c : listaUsera
            ) {
                worthVal += c.getQuantity() * CryptoActualPrices.getCryptoPrice(c.getSymbol());
            }
        }

        JLabel w = new JLabel(String.format("%.2f", worthVal) + " $");
        w.setFont(new Font("Ubuntu",Font.BOLD,16));
        w.setForeground(new Color(255, 255, 255));
        w.setPreferredSize(new Dimension(170,30));
        w.setHorizontalAlignment(SwingConstants.CENTER);
        w.setVerticalAlignment(SwingConstants.NORTH);
        w.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));

        JLabel profit = new JLabel("Profit");
        profit.setFont(new Font("Ubuntu",Font.BOLD,15));
        profit.setForeground(new Color(255,215,0));

        Double profitVal = 0.0;
        if(listaUsera != null ) {
            for (CryptoCurrency c : listaUsera
            ) {
                profitVal += worthVal - payed;
            }
        }

        String result = null;
        JLabel profitL;
        if (profitVal > 0) {
            result = "+"+String.format("%.2f", profitVal) + " $";
            profitL = new JLabel(result);
            profitL.setForeground(new Color(0, 255, 0));
        }else if (profitVal == 0) {
            result = String.format("%.2f", profitVal) + " $";
            profitL = new JLabel(result);
            profitL.setForeground(Color.GRAY);
        }else {
            result = String.format("%.2f", profitVal) + " $";
            profitL = new JLabel(result);
            profitL.setForeground(Color.RED);
        }
        profitL.setFont(new Font("Ubuntu",Font.BOLD,16));
        profitL.setPreferredSize(new Dimension(170,30));
        profitL.setHorizontalAlignment(SwingConstants.CENTER);
        profitL.setVerticalAlignment(SwingConstants.NORTH);
        profitL.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));

        walletInfo.add(holdings);
        walletInfo.add(hGap4,BorderLayout.NORTH);
        walletInfo.add(holdingBoughtFor);
        walletInfo.add(cost);
        walletInfo.add(holdingWorth);
        walletInfo.add(w);
        walletInfo.add(profit);
        walletInfo.add(profitL);

        chart.add(walletInfo);

        JPanel hGap1 = new JPanel();
        hGap1.setPreferredSize(new Dimension(10,230));
        hGap1.setBackground(new Color(40, 35, 65));
        add(hGap1);

        chart.add(hGap1);

        List<Float> values = new ArrayList<>();
        List<Color> colors = new ArrayList<>();
        if (listaUsera != null) {
            for (CryptoCurrency c : listaUsera) {
                values.add(c.getQuantity() * CryptoActualPrices.getCryptoPrice(c.getSymbol()));
                colors.add(CryptoColorDictionary.getCryptoColor(c.getSymbol()));
            }
            PieChart pieChart = new PieChart(values, colors);
            pieChart.setBorder(new EmptyBorder(0,10,0,0));
            chart.add(pieChart);
        } else {
            PieChart pieChart = new PieChart(values, colors);
            pieChart.setBorder(new EmptyBorder(0,10,0,0));
            chart.add(pieChart);
        }
        add(chart);

        JPanel hGap = new JPanel();
        hGap.setPreferredSize(new Dimension(5,250));
        hGap.setBackground(new Color(31,27,54));
        add(hGap);

        list = new RoundedPanel(50,new Color(40, 35, 65),215, 300);
        list.add(new ListHeader(),BorderLayout.PAGE_START);
        JPanel listContent = new JPanel();
        listContent.setLayout(new BoxLayout(listContent, BoxLayout.Y_AXIS));
        if (listaUsera != null) {
            for (CryptoCurrency crypto : listaUsera) {
                listContent.add(new ListedCrypto(crypto.getSymbol(), crypto.getQuantity(), crypto.getPurchasePrice(),actualAcc,cardpanel));
                JPanel vGap = new JPanel();
                vGap.setPreferredSize(new Dimension(215,5));
                vGap.setBackground(new Color(40, 35, 65));
                listContent.add(vGap);
            }
        }
        list.add(listContent);
        add(list);

        JPanel vGap3 = new JPanel();
        vGap3.setPreferredSize(new Dimension(735,5));
        vGap3.setBackground(new Color(31,27,54));
        add(vGap3, BorderLayout.NORTH);

        RoundedPanel personal = new RoundedPanel(50,new Color(40, 35, 65),725,60);

        ImageIcon git = new ImageIcon(ImageIO.read(new File("src/Img/github.png")));
        JLabel gitImg = new JLabel(git);
        gitImg.setBorder(new EmptyBorder(0,0,5,0));
        personal.add(gitImg, BorderLayout.WEST);
        JLabel gitLink = new JLabel("github.com/rafalBaron");
        gitLink.setBorder(new EmptyBorder(0,13,5,0));
        gitLink.setFont(new Font("Ubuntu",Font.BOLD,15));
        gitLink.setForeground(Color.WHITE);
        personal.add(gitLink, BorderLayout.WEST);

        JPanel vGap5 = new JPanel();
        vGap5.setPreferredSize(new Dimension(120,60));
        vGap5.setBackground(new Color(40, 35, 65));
        personal.add(vGap5, BorderLayout.WEST);

        ImageIcon link = new ImageIcon(ImageIO.read(new File("src/Img/linkedin.png")));
        JLabel linkImg = new JLabel(link);
        linkImg.setBorder(new EmptyBorder(0,0,5,0));
        personal.add(linkImg, BorderLayout.WEST);

        JLabel linkLink = new JLabel("www.linkedin.com/in/rafalBaron");
        linkLink.setBorder(new EmptyBorder(0,13,5,0));
        linkLink.setFont(new Font("Ubuntu",Font.BOLD,15));
        linkLink.setForeground(Color.WHITE);
        personal.add(linkLink, BorderLayout.WEST);

        add(personal,BorderLayout.NORTH);

        revalidate();
        repaint();
    }
}
