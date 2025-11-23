import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResetPasswordPanel extends JPanel {

    private GUI app;

    public ResetPasswordPanel(GUI app) {
        this.app = app;
        setLayout(null);
        setBackground(new Color(245, 245, 245));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2;
        int centerY = screenSize.height / 2;

        JLabel header = new JLabel("Reset Your Password");
        header.setFont(new Font("SansSerif", Font.BOLD, 36));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setForeground(new Color(50, 50, 50));
        header.setBounds(centerX - 300, 80, 600, 40);
        add(header);



        JLabel subText = new JLabel("Enter your registered email and choose a new password.");
        subText.setFont(new Font("SansSerif", Font.PLAIN, 18));
        subText.setForeground(new Color(90, 90, 90));
        subText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setBounds(centerX - 350, 130, 700, 30);
        add(subText);

        JLabel email = new JLabel("Registered Email:");
        email.setFont(new Font("SansSerif", Font.BOLD, 16));
        email.setBounds(centerX - 350, 230, 200, 30);
        add(email);

        JTextField emailText = new JTextField();
        emailText.setBounds(centerX - 150, 230, 450, 40);
        emailText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(emailText);

        // New Password
        JLabel password = new JLabel("New Password:");
        password.setFont(new Font("SansSerif", Font.BOLD, 16));
        password.setBounds(centerX - 350, 290, 200, 30);
        add(password);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(centerX - 150, 290, 450, 40);
        passwordText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(passwordText);

        // Reset Button
        JButton resetButton = new JButton("Reset Password");
        resetButton.setBounds(centerX - 140, centerY - 50, 280, 50);
        resetButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        resetButton.setBackground(new Color(100, 200, 150));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        resetButton.setOpaque(true);
        resetButton.setContentAreaFilled(true);
        resetButton.setBorder(BorderFactory.createEmptyBorder());
        resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        
        // Hover effect
        resetButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                resetButton.setBackground(new Color(80, 180, 130));
            }

            public void mouseExited(MouseEvent e) {
                resetButton.setBackground(new Color(100, 200, 150));
            }
        });

        resetButton.addActionListener(e -> {
            String emailVal = emailText.getText().trim();
            String newPassVal = new String(passwordText.getPassword()).trim();

            if (emailVal.isEmpty() || newPassVal.isEmpty()) {
                JOptionPane.showMessageDialog(ResetPasswordPanel.this,"Please enter your email and a new password.","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (app.getRegisteredEmail() == null || !emailVal.equals(app.getRegisteredEmail())) {
                JOptionPane.showMessageDialog(ResetPasswordPanel.this,"Email not found.","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }

            app.setRegisteredCredentials(emailVal, newPassVal);

            JOptionPane.showMessageDialog(ResetPasswordPanel.this,"Password has been reset. Please log in with your new password.", "Success",JOptionPane.INFORMATION_MESSAGE);

            app.showLoginPanel();
        });

        add(resetButton);

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

        backButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(new Color(180, 60, 60));
            }

            public void mouseExited(MouseEvent e) {
                backButton.setBackground(new Color(200, 80, 80));
            }
        });

        backButton.addActionListener(e -> app.showLoginPanel());
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
