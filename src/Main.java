import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame login = new JFrame();
        login.setVisible(false);
        int logType = showLoginDialog();

        if (logType != 3) {
            SwingUtilities.invokeLater(() -> {
                try {
                    if (logType == 0) {
                        LoginForm loginForm = new LoginForm();
                        loginForm.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                Account loggedInUser = loginForm.getLoggedUser();
                                try {
                                    new Window(loggedInUser);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        });
                    } else if (logType == 1){
                        RegisterForm regForm = new RegisterForm();
                        regForm.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                LoginForm loginForm = new LoginForm();
                                loginForm.addWindowListener(new WindowAdapter() {
                                    @Override
                                    public void windowClosed(WindowEvent e) {
                                        Account loggedInUser = loginForm.getLoggedUser();
                                        try {
                                            new Window(loggedInUser);
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                });
                            }
                        });
                    } else {
                        new Window(null);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private static int showLoginDialog() throws IOException {
        String[] optionButtons = {
                "<html><font size='5'>Login</font></center</html",
                "<html><font size='5'>Register</font></center</html",
                "<html><font size='5'>Guest</font></center</html"};

        UIManager.put("OptionPane.background", new Color(31,27,54));
        UIManager.put("Panel.background", new Color(31,27,54));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.BOLD, 16));
        UIManager.put("Button.foreground", new Color(31,27,54));
        UIManager.put("Button.background", new Color(23, 225, 158));
        UIManager.put("OptionPane.messageFont", new Font("Ubuntu", Font.BOLD, 16));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.minimumSize",new Dimension(270,100));

        ImageIcon icon = new ImageIcon(ImageIO.read(new File("src/Img/icon_small.png")));

        int choice = JOptionPane.showOptionDialog(
                null,
                "Mode",
                "CryptoMarket | Welcome",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icon,
                optionButtons,
                optionButtons[2]);

        switch (choice) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return 3;
        }
    }

}