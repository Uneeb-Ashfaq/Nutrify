
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupPanel extends JPanel {

    private GUI app;

    public SignupPanel(GUI app) {
        this.app = app;

        setLayout(null);
        setBackground(new Color(245, 245, 245)); // Light gray background
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2;
        int centerY = screenSize.height / 2;

        JLabel headerText = new JLabel("Sign Up");
        headerText.setFont(new Font("SansSerif", Font.BOLD, 40));
        headerText.setForeground(new Color(50, 50, 50));
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBounds(centerX - 300, 50, 600, 50);
        add(headerText);

        JLabel subText = new JLabel("Create your account to start tracking your daily calories and goals.");
        subText.setFont(new Font("SansSerif", Font.PLAIN, 18));
        subText.setForeground(new Color(90, 90, 90));
        subText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setBounds(centerX - 350, 110, 700, 30);
        add(subText);
        
        // email
        JLabel email = new JLabel("Email:");
        email.setFont(new Font("SansSerif", Font.BOLD, 16));
        email.setBounds(centerX - 350, 230, 200, 30);
        add(email);

        JTextField emailText = new JTextField();
        emailText.setBounds(centerX - 150, 230, 450, 40);
        emailText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(emailText);

        // Last Name
        JLabel password = new JLabel("Password:");
        password.setFont(new Font("SansSerif", Font.BOLD, 16));
        password.setBounds(centerX - 350, 290, 200, 30);
        add(password);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(centerX - 150, 290, 450, 40);
        passwordText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(passwordText);

        // signup Button
        JButton signupButton = new JButton("Create Account");
        signupButton.setBounds(centerX - 140, centerY - 50, 280, 50);
        signupButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        signupButton.setBackground(new Color(100, 200, 150));
        signupButton.setForeground(Color.WHITE);
        signupButton.setFocusPainted(false);
        signupButton.setOpaque(true);
        signupButton.setContentAreaFilled(true);
        signupButton.setBorder(BorderFactory.createEmptyBorder());
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        signupButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                signupButton.setBackground(new Color(80, 180, 130));
            }

            public void mouseExited(MouseEvent e) {
                signupButton.setBackground(new Color(100, 200, 150));
            }
        });

        signupButton.addActionListener(e -> {
            String emailVal = emailText.getText().trim();
            String passwordVal = new String(passwordText.getPassword()).trim();

            if (emailVal.isEmpty() || passwordVal.isEmpty()) {
                JOptionPane.showMessageDialog(SignupPanel.this, "Please fill in all fields!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (app.getRegisteredEmail() != null && emailVal.equals(app.getRegisteredEmail())) {
                JOptionPane.showMessageDialog(SignupPanel.this, "Email already in use, Please Login", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!emailVal.contains("@") || !emailVal.contains(".")) {
                JOptionPane.showMessageDialog(SignupPanel.this, "Please enter a valid email address.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            app.setRegisteredCredentials(emailVal, passwordVal);

            JOptionPane.showMessageDialog(SignupPanel.this, "Account created successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            app.showProfilePanel();
        });

        add(signupButton);

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