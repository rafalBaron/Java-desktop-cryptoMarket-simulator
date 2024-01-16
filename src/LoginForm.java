import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private DBHandler dbHandler;
    private Account loggedInUser = null;

    public LoginForm() {
        super("CryptoMarket | Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        dbHandler = new DBHandler();

        initUI();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.setPreferredSize(new Dimension(300, 300));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        usernameField.setBorder(null);
        usernameField.setForeground(new Color(255,215,0));
        passwordField.setForeground(new Color(255,215,0));
        passwordField.setFont(new Font("Ubuntu",Font.BOLD,15));
        usernameField.setFont(new Font("Ubuntu",Font.BOLD,15));
        usernameField.setBackground(new Color(31,27,54));
        passwordField.setBorder(null);
        passwordField.setBackground(new Color(31,27,54));
        passwordLabel.setBorder(BorderFactory.createEmptyBorder(0,35,0,0));
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(0,35,0,0));

        passwordField.setBorder(BorderFactory.createEmptyBorder(0,35,0,0));
        usernameField.setBorder(BorderFactory.createEmptyBorder(0,35,0,0));

        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);

        usernameLabel.setFont(new Font("Ubuntu", Font.BOLD, 15));
        passwordLabel.setFont(new Font("Ubuntu", Font.BOLD, 15));

        usernameField.setPreferredSize(new Dimension(100, 10));
        passwordField.setPreferredSize(new Dimension(100, 50));

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");

        loginButton.setForeground(new Color(31,27,54));
        loginButton.setFont(new Font("Ubuntu", Font.BOLD,16));
        loginButton.setBorder(null);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText();
                char[] enteredPassword = passwordField.getPassword();
                String enteredPasswordString = new String(enteredPassword);

                loggedInUser = dbHandler.getUser(enteredUsername, enteredPasswordString);

                if (loggedInUser != null) {
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
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

        panel.add(loginButton);
        panel.add(cancelButton);

        setPreferredSize(new Dimension(300, 300));  // Set the preferred size of the JFrame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().add(panel);
        pack();  // Pack the components
        setLocationRelativeTo(null);
    }


    public Account getLoggedUser() {
        return loggedInUser;
    }
}
