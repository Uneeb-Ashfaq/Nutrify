import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditProfilePanel extends JPanel {

    private GUI app;

    public EditProfilePanel(GUI app) {
        this.app = app;

        setLayout(null);
        setBackground(new Color(245, 245, 245));

        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2;

        // HEADER
        JLabel headerText = new JLabel("Edit Your Profile");
        headerText.setFont(new Font("SansSerif", Font.BOLD, 40));
        headerText.setForeground(new Color(50, 50, 50));
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBounds(centerX - 300, 50, 600, 50);
        add(headerText);

        // First Name
        JLabel firstName = new JLabel("First Name:");
        firstName.setFont(new Font("SansSerif", Font.BOLD, 16));
        firstName.setBounds(centerX - 350, 170 , 200, 30);
        add(firstName);
       
        JTextField firstNameText = new JTextField();
        firstNameText.setBounds(centerX - 150, 170 , 450, 40);
        firstNameText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(firstNameText);

        // Last Name
        JLabel lastName = new JLabel("Last Name:");
        lastName.setFont(new Font("SansSerif", Font.BOLD, 16));
        lastName.setBounds(centerX - 350, 230, 200, 30);
        add(lastName);

        JTextField lastNameText = new JTextField();
        lastNameText.setBounds(centerX - 150, 230 , 450, 40);
        lastNameText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(lastNameText);

        // Age
        JLabel age = new JLabel("Age:");
        age.setFont(new Font("SansSerif", Font.BOLD, 16));
        age.setBounds(centerX - 350, 290 , 200, 30);
        add(age);

        JTextField ageText = new JTextField();
        ageText.setBounds(centerX - 150,  290, 450, 40);
        ageText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(ageText);

        // Gender
        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("SansSerif", Font.BOLD, 16));
        gender.setBounds(centerX - 350, 350 , 200, 30);
        add(gender);

        String[] genderOptions = { "Select", "Male", "Female" };
        JComboBox<String> genderCombo = new JComboBox<>(genderOptions);
        genderCombo.setBounds(centerX - 150, 350 , 450, 40);
        genderCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(genderCombo);

        // Height
        JLabel height = new JLabel("Height (cm):");
        height.setFont(new Font("SansSerif", Font.BOLD, 16));
        height.setBounds(centerX - 350, 410, 200, 30);
        add(height);

        JTextField heightText = new JTextField();
        heightText.setBounds(centerX - 150, 410 , 450, 40);
        heightText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(heightText);

        // Weight
        JLabel weight = new JLabel("Weight (kg):");
        weight.setFont(new Font("SansSerif", Font.BOLD, 16));
        weight.setBounds(centerX - 350,  470, 200, 30);
        add(weight);

        JTextField weightText = new JTextField();
        weightText.setBounds(centerX - 150, 470 , 450, 40);
        weightText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(weightText);

        // Activity Level
        JLabel activityLevel = new JLabel("Activity Level:");
        activityLevel.setFont(new Font("SansSerif", Font.BOLD, 16));
        activityLevel.setBounds(centerX - 350, 530, 200, 30);
        add(activityLevel);

        String[] activityLevelOptions = { "Select", "Light", "Moderate", "Active", "Very Active" };
        JComboBox<String> activityLevelCombo = new JComboBox<>(activityLevelOptions);
        activityLevelCombo.setBounds(centerX - 150, 530 , 450, 40);
        activityLevelCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(activityLevelCombo);

        // Pre-fill with existing profile data
        Profile userProfile = app.getUserProfile();
        firstNameText.setText(userProfile.getFirstName());
        lastNameText.setText(userProfile.getLastName());
        ageText.setText(String.valueOf(userProfile.getAge()));
        heightText.setText(String.valueOf(userProfile.getHeight()));
        weightText.setText(String.valueOf(userProfile.getWeight()));
        
        // Set gender combo box
        String userGender = userProfile.getGender();
        for (int i = 0; i < genderOptions.length; i++) {
            if (genderOptions[i].equals(userGender)) {
                genderCombo.setSelectedIndex(i);
                break;
            }
        }
        
        // Set activity level combo box
        String userActivity = userProfile.getActivityLevel();
        for (int i = 0; i < activityLevelOptions.length; i++) {
            if (activityLevelOptions[i].equals(userActivity)) {
                activityLevelCombo.setSelectedIndex(i);
                break;
            }
        }

        // Save Changes Button
        JButton saveButton = new JButton("Save Changes");
        saveButton.setBounds(centerX - 140, 600, 280, 60);
        saveButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        saveButton.setBackground(new Color(100, 200, 150));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setOpaque(true);
        saveButton.setContentAreaFilled(true);
        saveButton.setBorder(BorderFactory.createEmptyBorder());
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover
        saveButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                saveButton.setBackground(new Color(80, 180, 130));
            }
            public void mouseExited(MouseEvent e) {
                saveButton.setBackground(new Color(100, 200, 150));
            }
        });

        saveButton.addActionListener(e -> {
            String firstNameInput = firstNameText.getText().trim();
            String lastNameInput = lastNameText.getText().trim();
            String ageInput = ageText.getText().trim();
            String genderInput = (String) genderCombo.getSelectedItem();
            String heightInput = heightText.getText().trim();
            String weightInput = weightText.getText().trim();
            String activityInput = (String) activityLevelCombo.getSelectedItem();

            if (firstNameInput.isEmpty() || lastNameInput.isEmpty() || ageInput.isEmpty() || 
                heightInput.isEmpty() || weightInput.isEmpty() || genderInput.equals("Select") || 
                activityInput.equals("Select")) {
                JOptionPane.showMessageDialog(EditProfilePanel.this, 
                    "Please fill in all fields!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int ageValue = Integer.parseInt(ageInput);
                double heightValue = Double.parseDouble(heightInput);
                double weightValue = Double.parseDouble(weightInput);
                
                userProfile.setFirstName(firstNameInput);
                userProfile.setLastName(lastNameInput);
                userProfile.setAge(ageValue);
                userProfile.setGender(genderInput);
                userProfile.setHeight(heightValue);
                userProfile.setWeight(weightValue);
                userProfile.setActivityLevel(activityInput);

                // Recalculate daily calories with updated profile
                app.getUserGoal().calculateDailyCalories(userProfile);

                JOptionPane.showMessageDialog(EditProfilePanel.this,
                    String.format(
                        "Profile Updated!\n\n" +
                        "Name: %s %s\n" +
                        "Age: %d\n" +
                        "Gender: %s\n" +
                        "Height: %.1f cm\n" +
                        "Weight: %.1f kg\n" +
                        "Activity Level: %s\n\n" +
                        "Daily Calorie Goal: %.0f kcal",
                        userProfile.getFirstName(),
                        userProfile.getLastName(),
                        userProfile.getAge(),
                        userProfile.getGender(),
                        userProfile.getHeight(),
                        userProfile.getWeight(),
                        userProfile.getActivityLevel(),
                        app.getUserGoal().getDailyCalorieGoal()),
                    "Profile Updated",
                    JOptionPane.INFORMATION_MESSAGE);

                app.showDashboard();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(EditProfilePanel.this, 
                    "Age, height, and weight must be valid numbers.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        add(saveButton);

        // Cancel Button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(centerX - 140, 680, 280, 50);
        cancelButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        cancelButton.setBackground(new Color(200, 80, 80));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setOpaque(true);
        cancelButton.setContentAreaFilled(true);
        cancelButton.setBorder(BorderFactory.createEmptyBorder());
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cancelButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                cancelButton.setBackground(new Color(180, 60, 60));
            }
            public void mouseExited(MouseEvent e) {
                cancelButton.setBackground(new Color(200, 80, 80));
            }
        });

        cancelButton.addActionListener(e -> app.showDashboard());
        add(cancelButton);
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