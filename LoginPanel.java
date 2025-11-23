
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel {

    private GUI app;

    public LoginPanel(GUI app) {
        this.app = app;

        setLayout(null);
        setBackground(new Color(245, 245, 245)); // Light gray background
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2;
        int centerY = screenSize.height / 2;

        JLabel headerText = new JLabel("Log In");
        headerText.setFont(new Font("SansSerif", Font.BOLD, 40));
        headerText.setForeground(new Color(50, 50, 50));
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBounds(centerX - 300, 50, 600, 50);
        add(headerText);

        // Username
        JLabel email = new JLabel("Email:");
        email.setFont(new Font("SansSerif", Font.BOLD, 16));
        email.setBounds(centerX - 350, 170, 200, 30);
        add(email);

        JTextField emailText = new JTextField();
        emailText.setBounds(centerX - 150, 170, 450, 40);
        emailText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(emailText);

        // Last Name
        JLabel password = new JLabel("Password:");
        password.setFont(new Font("SansSerif", Font.BOLD, 16));
        password.setBounds(centerX - 350, 230, 200, 30);
        add(password);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(centerX - 150, 230, 450, 40);
        passwordText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(passwordText);

        // Continue Button
        JButton continueButton = new JButton("Login");
        continueButton.setBounds(centerX - 140, centerY - 50, 280, 50);
        continueButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        continueButton.setBackground(new Color(100, 200, 150));
        continueButton.setForeground(Color.WHITE);
        continueButton.setFocusPainted(false);
        continueButton.setOpaque(true);
        continueButton.setContentAreaFilled(true);
        continueButton.setBorder(BorderFactory.createEmptyBorder());
        continueButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        continueButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                continueButton.setBackground(new Color(80, 180, 130));
            }

            public void mouseExited(MouseEvent e) {
                continueButton.setBackground(new Color(100, 200, 150));
            }
        });

        continueButton.addActionListener(e -> {
            String emailValue = emailText.getText().trim();
            String passwordValue = new String(passwordText.getPassword()).trim();

            if (emailValue.isEmpty() || passwordValue.isEmpty()) {
                JOptionPane.showMessageDialog(LoginPanel.this, "Please fill in both username and password!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            String savedEmail = app.getRegisteredEmail();
            String savedPass = app.getRegisteredPassword();

            if (savedEmail == null || savedPass == null) {
                JOptionPane.showMessageDialog(LoginPanel.this, "No account found. Please sign up first.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Compare text using .equals()
            if (!emailValue.equals(savedEmail) || !passwordValue.equals(savedPass)) {
                JOptionPane.showMessageDialog(LoginPanel.this, "Incorrect username or password!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            app.showDashboard();
        });

        add(continueButton);

        // Cancel Button
        JButton backButton = new JButton("Back");
        backButton.setBounds(centerX - 140, 500, 280, 50);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Load the background image
        Image bgImage = new ImageIcon("bg.png").getImage();

        // Draw it to fill the entire panel
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }
}