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
    public static void main(String[] args) {
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

    private static int showLoginDialog() {
        Object[] options = {"Login", "Register", "Guest"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Login, register or enter as a quest!",
                "Welcome!",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);

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