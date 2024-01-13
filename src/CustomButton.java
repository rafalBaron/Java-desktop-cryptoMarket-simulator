import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {
    CardLayout cardLayout;
    JPanel cardPanel;
    String cardName;

    public CustomButton(String text, CardLayout cardLayout, JPanel cardPanel, String cardName) {
        super(text);
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.cardName = cardName;
        this.setForeground(new Color(31, 27, 54));
        this.setBackground(new Color(21, 215, 152));
        this.setPreferredSize(new Dimension(125,125));
        this.setSize(new Dimension(125,125));
        this.setFocusPainted(false);
        Font buttonFont = this.getFont();
        this.setFont(new Font(buttonFont.getName(), buttonFont.getStyle(), 14));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(Color.WHITE);
                setBackground(new Color(23, 225, 158));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(new Color(31, 27, 54));
                setBackground(new Color(21, 215, 152));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }
        });
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, cardName);
            }
        });

        cardPanel.addPropertyChangeListener(evt -> {
            if ("visibleCard".equals(evt.getPropertyName())) {
                String visibleCard = (String) evt.getNewValue();
                setForeground(visibleCard.equals(cardName) ? Color.RED : UIManager.getColor("Button.foreground"));
            }
        });

        setUI(new BasicButtonUI());
    }
}

