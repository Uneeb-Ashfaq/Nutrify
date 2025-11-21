import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IntroPanel extends JPanel {

    private GUI app;

    public IntroPanel(GUI app) {
        this.app = app;

        setLayout(null);
        setBackground(new Color(245, 245, 245)); // Light gray background
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2;
        int centerY = screenSize.height / 2;

        JLabel titleLabel = new JLabel("CalorieAppTracker");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 64));
        titleLabel.setForeground(new Color(30, 100, 60));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(centerX - 450, 80, 900, 80);
        add(titleLabel);

        JLabel taglineLabel = new JLabel("Your Personal Health & Fitness Companion");
        taglineLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        taglineLabel.setForeground(new Color(100, 100, 100));
        taglineLabel.setHorizontalAlignment(SwingConstants.CENTER);
        taglineLabel.setBounds(centerX - 450, 170, 900, 30);
        add(taglineLabel);

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

        // Continue Button
        JButton continueButton = new JButton("Get Started");
        continueButton.setBounds(centerX - 125, centerY - 50, 250, 55);
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
        // Next Screen
        continueButton.addActionListener(e -> app.showProfilePanel());
        add(continueButton);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Load the background image
        Image bgImage = new ImageIcon("background.png").getImage();

        // Draw it to fill the entire panel
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }

}
