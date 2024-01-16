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
        setPreferredSize(new Dimension(400,400));

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

        usernameField.setBackground(new Color(31,27,54));
        passwordField.setBackground(new Color(31,27,54));
        confirmPasswordField.setBackground(new Color(31,27,54));
        balanceField.setBackground(new Color(31,27,54));

        usernameField.setBorder(BorderFactory.createEmptyBorder(0,35,0,0));
        passwordField.setBorder(BorderFactory.createEmptyBorder(0,35,0,0));
        confirmPasswordField.setBorder(BorderFactory.createEmptyBorder(0,35,0,0));
        balanceField.setBorder(BorderFactory.createEmptyBorder(0,35,0,0));

        usernameField.setFont(new Font("Ubuntu",Font.BOLD,15));
        usernameField.setForeground(new Color(255,215,0));

        passwordField.setFont(new Font("Ubuntu",Font.BOLD,15));
        passwordField.setForeground(new Color(255,215,0));

        confirmPasswordField.setFont(new Font("Ubuntu",Font.BOLD,15));
        confirmPasswordField.setForeground(new Color(255,215,0));

        balanceField.setFont(new Font("Ubuntu",Font.BOLD,15));
        balanceField.setForeground(new Color(255,215,0));

        usernameLabel.setFont(new Font("Ubuntu",Font.BOLD,15));
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(0,35,0,0));

        passwordLabel.setFont(new Font("Ubuntu",Font.BOLD,15));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBorder(BorderFactory.createEmptyBorder(0,35,0,0));

        confirmPasswordLabel.setFont(new Font("Ubuntu",Font.BOLD,15));
        confirmPasswordLabel.setForeground(Color.WHITE);
        confirmPasswordLabel.setBorder(BorderFactory.createEmptyBorder(0,35,0,0));

        balanceLabel.setFont(new Font("Ubuntu",Font.BOLD,15));
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setBorder(BorderFactory.createEmptyBorder(0,35,0,0));

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(balanceLabel);
        panel.add(balanceField);

        JButton registerButton = new JButton("Register");
        registerButton.setForeground(new Color(31,27,54));
        registerButton.setFont(new Font("Ubuntu", Font.BOLD,16));
        registerButton.setBorder(null);
        registerButton.setFocusPainted(false);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = usernameField.getText();
                char[] password = passwordField.getPassword();
                char[] confirmPassword = confirmPasswordField.getPassword();
                String balanceText = balanceField.getText();
                String passwordStr = new String(password);

                if (!Arrays.equals(password, confirmPassword)) {
                    JOptionPane.showMessageDialog(RegisterForm.this, "Passwords do not match", "Registration Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

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
        cancelButton.setForeground(new Color(31,27,54));
        cancelButton.setFont(new Font("Ubuntu", Font.BOLD,16));
        cancelButton.setBorder(null);
        cancelButton.setFocusPainted(false);
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
