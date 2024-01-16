import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class CryptoBaner extends JPanel implements BuyWindowListener{

    String exchange;
    int x,y;
    Account userAcc = null;

    JPanel cardPanel;

    CryptoBaner(String exchange, Account userAcc, JPanel cardPanel){
        super();
        this.userAcc = userAcc;
        this.exchange = exchange;
        this.cardPanel = cardPanel;
        this.setPreferredSize(new Dimension(350,150));
        this.add(new Label(this.exchange));

        JPanel liveData = new JPanel(new FlowLayout(1,40,5));
        liveData.setPreferredSize(new Dimension(348,50));
        this.add(liveData);
        JPanel liveData1 = new JPanel(new FlowLayout(1,20,15));
        liveData1.setPreferredSize(new Dimension(348,30));
        this.add(liveData1);

        ApiStrike api = new ApiStrike(liveData,liveData1,this.exchange.substring(0,3),userAcc);
        api.execute();

        if (this.userAcc != null) {
            BuyButton buyButton = new BuyButton("BUY",this.exchange.substring(0,3));
            BuyWindow listener = new BuyWindow(this.exchange.substring(0,3),userAcc,cardPanel);
            buyButton.addActionListener(listener);
            this.add(buyButton);
        }
    }


    protected void paintComponent(Graphics g) {

        BufferedImage bi = new BufferedImage(350, 150, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gb = bi.createGraphics();
        gb.setPaint(new Color(40, 35, 65));
        gb.fillRect(0, 0, 350, 150);
        gb.dispose();

        RoundRectangle2D r = new RoundRectangle2D.Float(0, 0, 350, 150, 25, 25);
        g.setClip(r);

        g.drawImage(bi, 0, 0, null);
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

    private static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x,y,width-1,height-1,radius,radius);
        }
    }
}