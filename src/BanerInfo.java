import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BanerInfo extends JPanel {
    Double balance;
    String userName;

    BanerInfo(Account userAccount) {
        super();
        this.balance = userAccount.getWallet().getBalance();
        this.userName = userAccount.getUserName();
        setPreferredSize(new Dimension(630,110));
        setLayout(new BorderLayout());
        setBackground(new Color(255,215,0));
        add(new LabelInfoBar("       "+String.format("%.3f",this.balance)+"$"),BorderLayout.WEST);

        ImageIcon imageIcon = new ImageIcon("src/Img/icon.png");
        JLabel label = new JLabel(imageIcon);
        label.setPreferredSize(new Dimension(110,110));
        add(label);
        add(new LabelInfoBar("@"+this.userName),BorderLayout.EAST);
    }

    public class LabelInfoBar extends JLabel{

        LabelInfoBar(String text) {
            super(text);
            setForeground(new Color(31,27,54));
            setFont(new Font("Ubuntu",Font.BOLD,24));
        }

    }

}
