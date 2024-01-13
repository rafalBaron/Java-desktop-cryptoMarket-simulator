import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

public class Portfolio extends JPanel {
    Account userAccount;

    Portfolio(BorderLayout borderLayout) throws IOException {
        super();
        this.setBackground(new Color(31,27,54));

        RoundedPanel baner = new RoundedPanel(50,new Color(255,215,0),730,120);

        RoundedPanel chart = new RoundedPanel(50,new Color(40, 35, 65),496, 370);

        RoundedPanel list = new RoundedPanel(50,new Color(40, 35, 65),215, 370);

        BanerInfo info = new BanerInfo(userAccount);
        baner.add(info);
        add(baner, BorderLayout.NORTH);

        JPanel vGap = new JPanel();
        vGap.setPreferredSize(new Dimension(735,7));
        vGap.setBackground(new Color(31,27,54));
        add(vGap, BorderLayout.NORTH);

        Chart pieChart = new Chart();
        add(chart, BorderLayout.WEST);

        JPanel hGap = new JPanel();
        hGap.setPreferredSize(new Dimension(10,375));
        hGap.setBackground(new Color(31,27,54));
        add(hGap);

        add(list, BorderLayout.EAST);

        setBorder(new EmptyBorder(21,0,0,0));

    }
}