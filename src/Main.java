import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
                    new Window(logType);
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
                "Choose an option:",
                "Login or Register",
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
                System.out.println("Canceled");
                return 3;
        }
    }
}