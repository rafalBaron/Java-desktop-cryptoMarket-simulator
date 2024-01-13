import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {

    CardLayout cardLayout = new CardLayout();
    JPanel cardPanel = new JPanel();
    Market market = new Market();

    Portfolio portfolio = new Portfolio(new BorderLayout());
    User user = new User();

    CustomButton marketButton = new CustomButton("MARKET",cardLayout,cardPanel,"Market");
    CustomButton portfolioButton = new CustomButton("PORTFOLIO",cardLayout,cardPanel,"Portfolio");
    CustomButton userButton = new CustomButton("USER",cardLayout,cardPanel,"User");

    Color selfColor;

    Window(int logType) throws IOException {
        super();
        this.setTitle("Crypto Market");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(950, 598);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);
        Image icon = null;
        try {
            icon = ImageIO.read(new File("src/Img/icon.png"));
            this.setIconImage(icon);
        } catch (IOException ignored) {
        }

        cardPanel.setLayout(cardLayout);

        marketButton.setBorder(BorderFactory.createEmptyBorder());

        portfolioButton.setBorder(BorderFactory.createEmptyBorder());

        userButton.setBorder(BorderFactory.createEmptyBorder());


        JPanel buttonPanel = new JPanel();
        GridLayout buttonsLayout = new GridLayout(0,1);
        buttonPanel.setLayout(buttonsLayout);
        buttonPanel.add(marketButton);
        buttonPanel.add(portfolioButton);
        buttonPanel.add(userButton);

        add(cardPanel, BorderLayout.CENTER);

        buttonPanel.setBackground(new Color(21, 215, 152));
        add(buttonPanel, BorderLayout.WEST);

        cardPanel.add(market, "Market");
        if(logType != 2) {
            cardPanel.add(portfolio, "Portfolio");
            cardPanel.add(user, "User");
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
