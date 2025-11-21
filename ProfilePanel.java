import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProfilePanel extends JPanel {

    private GUI app;

    public ProfilePanel(GUI app) {
        this.app = app;

        setLayout(null);
        setBackground(new Color(245, 245, 245));

        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2;

        // Step 1 of 2 Text
        JLabel stepLabel = new JLabel("Step 1 of 2");
        stepLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        stepLabel.setForeground(new Color(46, 204, 113));
        stepLabel.setHorizontalAlignment(SwingConstants.CENTER);
        stepLabel.setBounds(0, 30, screenSize.width, 20);
        add(stepLabel);

        // HEADER
        JLabel headerText = new JLabel("Create Your Profile");
        headerText.setFont(new Font("SansSerif", Font.BOLD, 40));
        headerText.setForeground(new Color(50, 50, 50));
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBounds(centerX - 300, 50, 600, 50);
        add(headerText);

        // First Name
        JLabel firstName = new JLabel("First Name:");
        firstName.setFont(new Font("SansSerif", Font.BOLD, 16));
        firstName.setBounds(centerX - 400, 170 , 200, 30);
        add(firstName);
       
        JTextField firstNameText = new JTextField();
        firstNameText.setBounds(centerX - 150, 170 , 450, 40);
        firstNameText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(firstNameText);

        // Last Name
        JLabel lastName = new JLabel("Last Name:");
        lastName.setFont(new Font("SansSerif", Font.BOLD, 16));
        lastName.setBounds(centerX - 400, 230, 200, 30);
        add(lastName);

        JTextField lastNameText = new JTextField();
        lastNameText.setBounds(centerX - 150, 230 , 450, 40);
        lastNameText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(lastNameText);

        // Age
        JLabel age = new JLabel("Age:");
        age.setFont(new Font("SansSerif", Font.BOLD, 16));
        age.setBounds(centerX - 400, 290 , 200, 30);
        add(age);

        JTextField ageText = new JTextField();
        ageText.setBounds(centerX - 150,  290, 450, 40);
        ageText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(ageText);

        // Gender
        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("SansSerif", Font.BOLD, 16));
        gender.setBounds(centerX - 400, 350 , 200, 30);
        add(gender);

        String[] genderOptions = { "Select", "Male", "Female" };
        JComboBox<String> genderCombo = new JComboBox<>(genderOptions);
        genderCombo.setBounds(centerX - 150, 350 , 450, 40);
        genderCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(genderCombo);

        // Height
        JLabel height = new JLabel("Height (cm):");
        height.setFont(new Font("SansSerif", Font.BOLD, 16));
        height.setBounds(centerX - 400,   410, 200, 30);
        add(height);

        JTextField heightText = new JTextField();
        heightText.setBounds(centerX - 150, 410 , 450, 40);
        heightText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(heightText);

        // Weight
        JLabel weight = new JLabel("Weight (kg):");
        weight.setFont(new Font("SansSerif", Font.BOLD, 16));
        weight.setBounds(centerX - 400,  470, 200, 30);
        add(weight);

        JTextField weightText = new JTextField();
        weightText.setBounds(centerX - 150, 470 , 450, 40);
        weightText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(weightText);

        // Activity Level
        JLabel activityLevel = new JLabel("Activity Level:");
        activityLevel.setFont(new Font("SansSerif", Font.BOLD, 16));
        activityLevel.setBounds(centerX - 400,   530, 200, 30);
        add(activityLevel);

        String[] activityLevelOptions = { "Select", "Light", "Moderate", "Active", "Very Active" };
        JComboBox<String> activityLevelCombo = new JComboBox<>(activityLevelOptions);
        activityLevelCombo.setBounds(centerX - 150, 530 , 450, 40);
        activityLevelCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(activityLevelCombo);

        // Continue Button
        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(centerX - 140, 600, 280, 60);
        continueButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        continueButton.setBackground(new Color(100, 200, 150));
        continueButton.setForeground(Color.WHITE);
        continueButton.setFocusPainted(false);
        continueButton.setOpaque(true);
        continueButton.setContentAreaFilled(true);
        continueButton.setBorder(BorderFactory.createEmptyBorder());
        continueButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover
        continueButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                continueButton.setBackground(new Color(80, 180, 130));
            }
            public void mouseExited(MouseEvent e) {
                continueButton.setBackground(new Color(100, 200, 150));
            }
        });

        continueButton.addActionListener(e -> {
            String firstNameInput = firstNameText.getText().trim();
            String lastNameInput = lastNameText.getText().trim();
            String ageInput = ageText.getText().trim();
            String genderInput = (String) genderCombo.getSelectedItem();
            String heightInput = heightText.getText().trim();
            String weightInput = weightText.getText().trim();
            String activityInput = (String) activityLevelCombo.getSelectedItem();

            // make sure fields are all full 
            if (firstNameInput.isEmpty() || lastNameInput.isEmpty() || ageInput.isEmpty() || heightInput.isEmpty() || weightInput.isEmpty() ||genderInput.equals("Select") || activityInput.equals("Select")) {
                JOptionPane.showMessageDialog(ProfilePanel.this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int ageValue = Integer.parseInt(ageInput);
                double heightValue = Double.parseDouble(heightInput);
                double weightValue = Double.parseDouble(weightInput);
                Profile userProfile = app.getUserProfile();
                userProfile.setFirstName(firstNameInput);
                userProfile.setLastName(lastNameInput);
                userProfile.setAge(ageValue);
                userProfile.setGender(genderInput);
                userProfile.setHeight(heightValue);
                userProfile.setWeight(weightValue);
                userProfile.setActivityLevel(activityInput);

                JOptionPane.showMessageDialog(ProfilePanel.this,
                        String.format(
                                "Profile Saved!\n\n" +
                                        "Name: %s %s\n" +
                                        "Age: %d\n" +
                                        "Gender: %s\n" +
                                        "Height: %.1f cm\n" +
                                        "Weight: %.1f kg\n" +
                                        "Activity Level: %s",
                                userProfile.getFirstName(),
                                userProfile.getLastName(),
                                userProfile.getAge(),
                                userProfile.getGender(),
                                userProfile.getHeight(),
                                userProfile.getWeight(),
                                userProfile.getActivityLevel()),
                        "Profile Summary",
                        JOptionPane.INFORMATION_MESSAGE);

                app.showGoalPanel();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ProfilePanel.this, "Age, height, and weight must be valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        add(continueButton);

    }
}