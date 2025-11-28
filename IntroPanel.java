import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * IntroPanel
 * First screen the user sees. Lets them choose Sign Up or Log In.
 */
public class IntroPanel extends JPanel {

    private GUI app; // reference to main app

    public IntroPanel(GUI app) {
        this.app = app;

        setLayout(null);
        setBackground(new Color(245, 245, 245)); // Light gray background
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Screen size for centering components
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2;
        int centerY = screenSize.height / 2;

        // ========= Title =========
        JLabel titleLabel = new JLabel("Nutrify");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 64));
        titleLabel.setForeground(new Color(30, 100, 60));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(centerX - 450, 80, 900, 80);
        add(titleLabel);


        // ========= Tagline =========
        JLabel taglineLabel = new JLabel("Your Personal Health & Fitness Companion");
        taglineLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        taglineLabel.setForeground(new Color(100, 100, 100));
        taglineLabel.setHorizontalAlignment(SwingConstants.CENTER);
        taglineLabel.setBounds(centerX - 450, 170, 900, 30);
        add(taglineLabel);

        // ========= Features text =========
        JTextArea features = new JTextArea(
                "• Track Daily Calories & Meals\n\n" +
                    "• Set & Achieve Personal Goals\n\n" +
                    "• Monitor Your Progress Over Time");
        features.setFont(new Font("SansSerif", Font.PLAIN, 18));
        features.setForeground(new Color(80, 80, 80));
        features.setBackground(new Color(245, 245, 245));
        features.setEditable(false);
        features.setFocusable(false);
        features.setOpaque(false);
        features.setBorder(null);
        features.setBounds(centerX - 125, 250, 700, 150);
        add(features);

        // ========= Sign Up button =========
        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(centerX - 235, centerY - 40, 220, 60);
        signupButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        signupButton.setBackground(new Color(46, 204, 113));
        signupButton.setForeground(Color.WHITE);
        signupButton.setFocusPainted(false);
        signupButton.setOpaque(true); 
        signupButton.setContentAreaFilled(true);
        signupButton.setBorder(BorderFactory.createEmptyBorder());
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        signupButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                signupButton.setBackground(new Color(39, 174, 96)); // Darker green
            }

            public void mouseExited(MouseEvent e) {
                signupButton.setBackground(new Color(46, 204, 113));
            }
        });

        // Go to signup screen
        signupButton.addActionListener(e -> app.showSignupPanel());
        add(signupButton);

        // ========= Login button =========
        JButton loginButton = new JButton("Log In");
        loginButton.setBounds(centerX + 15, centerY - 40, 220, 60);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginButton.setBackground(new Color(52, 152, 219));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setOpaque(true);
        loginButton.setContentAreaFilled(true);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(new Color(41, 128, 185)); // Darker blue
            }

            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(new Color(52, 152, 219));
            }
        });
        // Go to login screen
        loginButton.addActionListener(e -> app.showLoginPanel());
        add(loginButton);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Load the background image
        Image bgImage = new ImageIcon("introPanelBG.png").getImage();

        // Draw it to fill the entire panel
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }

}
