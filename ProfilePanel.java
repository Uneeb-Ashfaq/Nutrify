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
        int startY = 50;

        // HEADER
        JLabel headerText = new JLabel("Create Your Profile");
        headerText.setFont(new Font("SansSerif", Font.BOLD, 40));
        headerText.setForeground(new Color(50, 50, 50));
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBounds(centerX - 300, startY, 600, 50);
        add(headerText);

        int labelX = centerX - 400;
        int fieldX = centerX - 150;

        // First Name
        JLabel firstName = new JLabel("First Name:");
        firstName.setFont(new Font("SansSerif", Font.BOLD, 16));
        firstName.setBounds(labelX, startY + 120, 200, 30);
        add(firstName);

        JTextField firstNameText = new JTextField();
        firstNameText.setBounds(fieldX, startY + 120, 450, 40);
        firstNameText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(firstNameText);

        // Last Name
        JLabel lastName = new JLabel("Last Name:");
        lastName.setFont(new Font("SansSerif", Font.BOLD, 16));
        lastName.setBounds(labelX, startY + 180, 200, 30);
        add(lastName);

        JTextField lastNameText = new JTextField();
        lastNameText.setBounds(fieldX, startY + 180, 450, 40);
        lastNameText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(lastNameText);

        // Age
        JLabel age = new JLabel("Age:");
        age.setFont(new Font("SansSerif", Font.BOLD, 16));
        age.setBounds(labelX, startY + 240, 200, 30);
        add(age);

        JTextField ageText = new JTextField();
        ageText.setBounds(fieldX, startY + 240, 450, 40);
        ageText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(ageText);

        // Gender
        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("SansSerif", Font.BOLD, 16));
        gender.setBounds(labelX, startY + 300, 200, 30);
        add(gender);

        String[] genderOptions = { "Select", "Male", "Female" };
        JComboBox<String> genderCombo = new JComboBox<>(genderOptions);
        genderCombo.setBounds(fieldX, startY + 300, 450, 40);
        genderCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(genderCombo);

        // Height
        JLabel height = new JLabel("Height (cm):");
        height.setFont(new Font("SansSerif", Font.BOLD, 16));
        height.setBounds(labelX, startY + 360, 200, 30);
        add(height);

        JTextField heightText = new JTextField();
        heightText.setBounds(fieldX, startY + 360, 450, 40);
        heightText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(heightText);

        // Weight
        JLabel weight = new JLabel("Weight (kg):");
        weight.setFont(new Font("SansSerif", Font.BOLD, 16));
        weight.setBounds(labelX, startY + 420, 200, 30);
        add(weight);

        JTextField weightText = new JTextField();
        weightText.setBounds(fieldX, startY + 420, 450, 40);
        weightText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(weightText);

        // Activity Level
        JLabel activityLevel = new JLabel("Activity Level:");
        activityLevel.setFont(new Font("SansSerif", Font.BOLD, 16));
        activityLevel.setBounds(labelX, startY + 480, 200, 30);
        add(activityLevel);

        String[] activityLevelOptions = { "Select", "Light", "Moderate", "Active", "Very Active" };
        JComboBox<String> activityLevelCombo = new JComboBox<>(activityLevelOptions);
        activityLevelCombo.setBounds(fieldX, startY + 480, 450, 40);
        activityLevelCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(activityLevelCombo);

        // Continue Button
        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(centerX - 140, startY + 560, 280, 60);
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

                if (firstNameInput.isEmpty() || lastNameInput.isEmpty() || ageInput.isEmpty() ||
                        heightInput.isEmpty() || weightInput.isEmpty() ||
                        genderInput.equals("Select") || activityInput.equals("Select")) {
                    JOptionPane.showMessageDialog(ProfilePanel.this, "Please fill in all fields!",
                            "Error", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(ProfilePanel.this,
                            "Age, height, and weight must be valid numbers.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            
        });

        add(continueButton);

    }
}