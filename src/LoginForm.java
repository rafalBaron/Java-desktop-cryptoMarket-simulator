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
        setPreferredSize(new Dimension(500,500));

        dbHandler = new DBHandler();

        initUI();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        usernameLabel.setFont(new Font("Ubuntu",Font.BOLD,15));
        passwordLabel.setFont(new Font("Ubuntu",Font.BOLD,15));

        usernameField.setPreferredSize(new Dimension(100,50));
        passwordField.setPreferredSize(new Dimension(100,50));


        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText();
                char[] enteredPassword = passwordField.getPassword();
                String enteredPasswordString = new String(enteredPassword);

                loggedInUser = dbHandler.getUser(enteredUsername, enteredPasswordString);

                if (loggedInUser != null) {
                    System.out.println("Login successful for user: " + enteredUsername);
                    dispose();
                } else {
                    System.out.println("Invalid username or password");
                    JOptionPane.showMessageDialog(LoginForm.this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(loginButton);
        panel.add(cancelButton);

        add(panel);
    }

    public Account getLoggedUser() {
        return loggedInUser;
    }
}
