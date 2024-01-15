import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RegisterForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField balanceField;
    private Account newUser = null;
    private DBHandler dbHandler;

    public RegisterForm() {
        super("CryptoMarket | Register");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500,500));

        dbHandler = new DBHandler();

        initUI();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(5, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JLabel balanceLabel = new JLabel("Starting Balance:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();
        balanceField = new JTextField();

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(balanceLabel);
        panel.add(balanceField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tutaj umieść kod rejestracji
                String userName = usernameField.getText();
                char[] password = passwordField.getPassword();
                char[] confirmPassword = confirmPasswordField.getPassword();
                String balanceText = balanceField.getText();
                String passwordStr = new String(password);

                // Sprawdź czy hasła się zgadzają
                if (!Arrays.equals(password, confirmPassword)) {
                    JOptionPane.showMessageDialog(RegisterForm.this, "Passwords do not match", "Registration Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Sprawdź czy Starting Balance jest poprawne
                try {
                    double balance = Double.parseDouble(balanceText);
                    if (balance < 100 || balance > 100000) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RegisterForm.this, "Invalid Starting Balance. It should be between $100 and $100000.", "Registration Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                dbHandler.registerUser(userName,passwordStr,Double.parseDouble(balanceText));
                dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(registerButton);
        panel.add(cancelButton);

        add(panel);
    }

    public Account getNewUser() {
        return this.newUser;
    }
}
