import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GoalPanel extends JPanel {
    private GUI app;

    public GoalPanel(GUI app) {
        this.app = app;
        setLayout(null);
        setBackground(new Color(245, 245, 245));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2;

        JLabel stepLabel = new JLabel("Step 2 of 2");
        stepLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        stepLabel.setForeground(new Color(46, 204, 113));
        stepLabel.setHorizontalAlignment(SwingConstants.CENTER);
        stepLabel.setBounds(0, 60, screenSize.width, 20);
        add(stepLabel);

        // HEADER
        JLabel headerText = new JLabel("Set Your Goal");
        headerText.setFont(new Font("SansSerif", Font.BOLD, 40));
        headerText.setForeground(new Color(50, 50, 50));
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBounds(0, 80, screenSize.width, 50);
        add(headerText);

        // Goal Type
        JLabel goalTypeLabel = new JLabel("Goal Type:");
        goalTypeLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        goalTypeLabel.setBounds(centerX - 350, 200, 200, 30);
        add(goalTypeLabel);

        String[] goalOptions = { "Select", "Lose", "Maintain", "Gain" };
        JComboBox<String> goalCombo = new JComboBox<>(goalOptions);
        goalCombo.setBounds(centerX - 150, 200, 450, 40);
        goalCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        goalCombo.setBackground(Color.WHITE);
        add(goalCombo);

        // Target Weight
        JLabel targetWeight = new JLabel("Target Weight (kg):");
        targetWeight.setFont(new Font("SansSerif", Font.BOLD, 16));
        targetWeight.setBounds(centerX - 350, 270, 200, 30);
        add(targetWeight);

        JTextField targetWeightText = new JTextField();
        targetWeightText.setBounds(centerX - 150, 270, 450, 40);
        targetWeightText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(targetWeightText);

        // Time Frame
        JLabel timeFrame = new JLabel("Time Frame (months):");
        timeFrame.setFont(new Font("SansSerif", Font.BOLD, 16));
        timeFrame.setBounds(centerX - 350, 340, 200, 30);
        add(timeFrame);

        JTextField timeFrameText = new JTextField();
        timeFrameText.setBounds(centerX - 150, 340, 450, 40);
        timeFrameText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(timeFrameText);

        // Continue Button
        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(centerX - 140, 430, 280, 60);
        continueButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        continueButton.setBackground(new Color(46, 204, 113));
        continueButton.setForeground(Color.WHITE);
        continueButton.setFocusPainted(false);
        continueButton.setOpaque(true);
        continueButton.setContentAreaFilled(true);
        continueButton.setBorder(BorderFactory.createEmptyBorder());
        continueButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        continueButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                continueButton.setBackground(new Color(36, 184, 93));
            }

            public void mouseExited(MouseEvent e) {
                continueButton.setBackground(new Color(46, 204, 113));
            }
        });

        continueButton.addActionListener(e -> {
            String goalType = (String) goalCombo.getSelectedItem();
            String targetWeightInput = targetWeightText.getText().trim();
            String timeFrameInput = timeFrameText.getText().trim();

            if (targetWeightInput.isEmpty() || timeFrameInput.isEmpty() || goalType.equals("Select")) {
                JOptionPane.showMessageDialog(GoalPanel.this, "Please fill in all fields!", "Error",JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int months = Integer.parseInt(timeFrameInput);
                double targetWeightValue = Double.parseDouble(targetWeightInput);

                if (targetWeightValue <= 0 || months <= 0) {
                    JOptionPane.showMessageDialog(GoalPanel.this, "Target weight and time frame must be positive!","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Goal userGoal = app.getUserGoal();
                Profile userProfile = app.getUserProfile();
                userGoal.setGoalType(goalType);
                userGoal.setTargetWeight(targetWeightValue);
                userGoal.setMonths(months);
                userGoal.calculateDailyCalories(userProfile);

                JOptionPane.showMessageDialog(GoalPanel.this,
                        String.format(
                                "Goal set!\n\n" +
                                "Goal Type: %s\n" +
                                "Target Weight: %.1f kg in %d months\n\n" +
                                "Daily Calorie Target: %.0f kcal",
                                goalType,
                                targetWeightValue,
                                months,
                                userGoal.getDailyCalorieGoal()),
                        "Goal Summary", JOptionPane.INFORMATION_MESSAGE);

            app.showDashboard();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(GoalPanel.this, "Target weight and time frame must be valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        add(continueButton);

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
