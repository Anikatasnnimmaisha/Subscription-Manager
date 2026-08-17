import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Login {

    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    // In-memory account storage (Username -> Password)
    private static final Map<String, String> userDatabase = new HashMap<>();

    static {
        // Default admin account
        userDatabase.put("admin", "1234");
    }

    public Login() {

        frame = new JFrame("Subscription Manager - Authentication");
        frame.setSize(420, 280);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel title = new JLabel("SUBSCRIPTION MANAGER");
        title.setFont(title.getFont().deriveFont(18f));

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Create Account");

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;

        // Username
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        // Action Listeners
        loginButton.addActionListener(e -> checkLogin());
        signUpButton.addActionListener(e -> createAccount());

        frame.add(panel);
        frame.setVisible(true);
    }

    private void checkLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            frame.dispose();
            new SubscriptionManagerGUI();
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createAccount() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Enter a username and password to create an account.", "Registration Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (userDatabase.containsKey(username)) {
            JOptionPane.showMessageDialog(frame, "Username already exists. Please pick another one.", "Registration Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        userDatabase.put(username, password);
        JOptionPane.showMessageDialog(frame, "Account created successfully! Click Login to proceed.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}