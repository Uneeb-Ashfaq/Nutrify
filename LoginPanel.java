
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * LoginPanel
 * Handles user login by checking saved email and password.
 */
public class LoginPanel extends JPanel {

    private GUI app; // reference to the main app

    public LoginPanel(GUI app) {
        this.app = app;

        setLayout(null);
        setBackground(new Color(245, 245, 245)); // Light gray background
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        // Used for centering UI components
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2;
        int centerY = screenSize.height / 2;

        // ========= Header =========
        JLabel headerText = new JLabel("Log In");
        headerText.setFont(new Font("SansSerif", Font.BOLD, 40));
        headerText.setForeground(new Color(50, 50, 50));
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBounds(centerX - 300, 50, 600, 50);
        add(headerText);

        // ========= Subtext =========
        JLabel subText = new JLabel("Welcome Back! Please enter your details below.");
        subText.setFont(new Font("SansSerif", Font.PLAIN, 18));
        subText.setForeground(new Color(90, 90, 90));
        subText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setBounds(centerX - 350, 110, 700, 30);
        add(subText);

        // ========= Email field =========
        JLabel email = new JLabel("Email:");
        email.setFont(new Font("SansSerif", Font.BOLD, 16));
        email.setBounds(centerX - 350, 230, 200, 30);
        add(email);

        JTextField emailText = new JTextField();
        emailText.setBounds(centerX - 150, 230, 450, 40);
        emailText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(emailText);

        // ========= Password field =========
        JLabel password = new JLabel("Password:");
        password.setFont(new Font("SansSerif", Font.BOLD, 16));
        password.setBounds(centerX - 350, 290, 200, 30);
        add(password);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(centerX - 150, 290, 450, 40);
        passwordText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(passwordText);

        // ========= Forgot password link =========
        JLabel resetPassword = new JLabel("Forgot Password?");
        resetPassword.setFont(new Font("SansSerif", Font.PLAIN, 12));
        resetPassword.setBounds(centerX + 200, 335, 125, 30);
        resetPassword.setCursor(new Cursor(Cursor.HAND_CURSOR)); // hand cursor
        add(resetPassword);
        // hover
        resetPassword.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                resetPassword.setForeground(new Color(41, 128, 185));
            }

            public void mouseExited(MouseEvent e) {
                resetPassword.setForeground(new Color(52, 152, 219));
            }

            public void mouseClicked(MouseEvent e) {
                app.showresetPassword();
            }
        });

        // ========= Login Button =========
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(centerX - 140, centerY - 50, 280, 50);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginButton.setBackground(new Color(100, 200, 150));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setOpaque(true);
        loginButton.setContentAreaFilled(true);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(new Color(80, 180, 130));
            }

            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(new Color(100, 200, 150));
            }
        });
        // Handle login logic
        loginButton.addActionListener(e -> {
            String emailValue = emailText.getText().trim();
            String passwordValue = new String(passwordText.getPassword()).trim();
            // Ensure both fields are filled
            if (emailValue.isEmpty() || passwordValue.isEmpty()) {
                JOptionPane.showMessageDialog(LoginPanel.this, "Please fill in both username and password!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Get saved login credentials
            String savedEmail = app.getRegisteredEmail();
            String savedPass = app.getRegisteredPassword();
            // Make sure an account exists first
            if (savedEmail == null || savedPass == null) {
                JOptionPane.showMessageDialog(LoginPanel.this, "No account found. Please sign up first.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check credentials
            if (!emailValue.equals(savedEmail) || !passwordValue.equals(savedPass)) {
                JOptionPane.showMessageDialog(LoginPanel.this, "Incorrect username or password!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Login successful takes to dashboard
            app.showDashboard();
        });

        add(loginButton);

        // ========= Back button =========
        JButton backButton = new JButton("Back");
        backButton.setBounds(centerX - 140, 500 , 280, 50);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        backButton.setBackground(new Color(200, 80, 80));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setOpaque(true);
        backButton.setContentAreaFilled(true);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(new Color(180, 60, 60));
            }

            public void mouseExited(MouseEvent e) {
                backButton.setBackground(new Color(200, 80, 80));
            }
        });

        backButton.addActionListener(e -> app.showIntroPanel());

        add(backButton);

    }

    /**
     * Paints background image on the panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Load the background image
        Image bgImage = new ImageIcon("bg.png").getImage();

        // Draw it to fill the entire panel
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }
}