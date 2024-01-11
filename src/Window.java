import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame implements ActionListener {

    CardLayout cardLayout = new CardLayout();
    JPanel cardPanel = new JPanel();

    Market market = new Market();
    Portfolio portfolio = new Portfolio();
    User user = new User();
    Settings settings = new Settings();

    BufferedImage iconMarket = ImageIO.read(new File("src/Img/button_market_clicked.png"));
    JButton marketButton = new JButton(new ImageIcon(iconMarket));


    BufferedImage iconPort = ImageIO.read(new File("src/Img/button_portfolio.png"));
    JButton portfolioButton = new JButton(new ImageIcon(iconPort));

    BufferedImage iconUser = ImageIO.read(new File("src/Img/button_user.png"));
    JButton userButton = new JButton(new ImageIcon(iconUser));

    BufferedImage iconSettings = ImageIO.read(new File("src/Img/button_settings.png"));
    JButton settingsButton = new JButton(new ImageIcon(iconSettings));

    Color selfColor;

    boolean marketClicked=true,portfolioClicked=false,userClicked=false,settingsClicked=false;

    Window() throws IOException {
        super();
        this.setTitle("Crypto Market");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(1010, 600);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);
        Image icon = null;
        try {
            icon = ImageIO.read(new File("src/Img/icon.png"));
            this.setIconImage(icon);
        } catch (IOException ignored) {
        }

        cardPanel.setLayout(cardLayout);

        marketButton.addActionListener(this);
        marketButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BufferedImage icon = null;
                if(!marketClicked) {
                    try {
                        icon = ImageIO.read(new File("src/Img/button_market_hover.png"));
                        marketButton.setIcon(new ImageIcon(icon));
                    } catch (IOException ignored) {
                    }
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!marketClicked) {
                    try {
                        BufferedImage icon = ImageIO.read(new File("src/Img/button_market.png"));
                        marketButton.setIcon(new ImageIcon(icon));
                    } catch (IOException ignored) {
                    }
                }
            }

            public void mouseClicked(MouseEvent e) {
                try {
                    marketClicked = true;
                    portfolioClicked = false;
                    userClicked = false;
                    settingsClicked = false;
                    BufferedImage iconMarketClicked = ImageIO.read(new File("src/Img/button_market_clicked.png"));
                    marketButton.setIcon(new ImageIcon(iconMarketClicked));
                    BufferedImage icon = ImageIO.read(new File("src/Img/button_portfolio.png"));
                    portfolioButton.setIcon(new ImageIcon(icon));
                } catch (IOException ignored) {
                }
            }
        });
        marketButton.setBorder(BorderFactory.createEmptyBorder());
        marketButton.setContentAreaFilled(false);

        portfolioButton.addActionListener(this);
        portfolioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BufferedImage icon = null;
                if(!portfolioClicked) {
                    try {
                        icon = ImageIO.read(new File("src/Img/button_portfolio_hover.png"));
                        portfolioButton.setIcon(new ImageIcon(icon));
                    } catch (IOException ignored) {
                    }
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(!portfolioClicked) {
                    try {
                        BufferedImage icon = ImageIO.read(new File("src/Img/button_portfolio.png"));
                        portfolioButton.setIcon(new ImageIcon(icon));
                    } catch (IOException ignored) {
                    }
                }
            }
            public void mouseClicked(MouseEvent e) {
                try {
                    marketClicked = false;
                    portfolioClicked = true;
                    userClicked = false;
                    settingsClicked = false;
                    BufferedImage icon = ImageIO.read(new File("src/Img/button_market.png"));
                    marketButton.setIcon(new ImageIcon(icon));
                    BufferedImage iconPortfolioClicked = ImageIO.read(new File("src/Img/button_portfolio_clicked.png"));
                    portfolioButton.setIcon(new ImageIcon(iconPortfolioClicked));
                } catch (IOException ignored) {
                }
            }
        });
        portfolioButton.setBorder(BorderFactory.createEmptyBorder());
        portfolioButton.setContentAreaFilled(false);

        userButton.addActionListener(this);
        userButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BufferedImage icon = null;
                try {
                    icon = ImageIO.read(new File("src/Img/button_user_hover.png"));
                    userButton.setIcon(new ImageIcon(icon));
                } catch (IOException ignored) {
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                try {
                    BufferedImage icon = ImageIO.read(new File("src/Img/button_user.png"));
                    userButton.setIcon(new ImageIcon(icon));
                } catch (IOException ignored) {
                }
            }
        });
        userButton.setBorder(BorderFactory.createEmptyBorder());
        userButton.setContentAreaFilled(false);

        settingsButton.addActionListener(this);
        settingsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BufferedImage icon = null;
                try {
                    icon = ImageIO.read(new File("src/Img/button_settings_hover.png"));
                    settingsButton.setIcon(new ImageIcon(icon));
                } catch (IOException ignored) {
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                try {
                    BufferedImage icon = ImageIO.read(new File("src/Img/button_settings.png"));
                    settingsButton.setIcon(new ImageIcon(icon));
                } catch (IOException ignored) {
                }
            }
        });
        settingsButton.setBorder(BorderFactory.createEmptyBorder());
        settingsButton.setContentAreaFilled(false);

        JPanel buttonPanel = new JPanel();
        BoxLayout buttonsLayout = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
        buttonPanel.setLayout(buttonsLayout);
        buttonPanel.add(marketButton);
        buttonPanel.add(portfolioButton);
        buttonPanel.add(userButton);
        buttonPanel.add(settingsButton);

        add(cardPanel, BorderLayout.CENTER);

        buttonPanel.setBackground(new Color(31,27,54));
        add(buttonPanel, BorderLayout.WEST);

        System.out.println(buttonPanel.getSize());

        cardPanel.add(market, "Market");
        cardPanel.add(portfolio, "Portfolio");
        cardPanel.add(user,"User");
        cardPanel.add(settings,"Settings");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == marketButton) {
            cardLayout.show(cardPanel,"Market");
        } else if (source == portfolioButton) {
            cardLayout.show(cardPanel,"Portfolio");
        } else if (source == userButton) {
            cardLayout.show(cardPanel,"User");
        } else if (source == settingsButton) {
            cardLayout.show(cardPanel,"Settings");
        }

    }


}
