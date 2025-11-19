import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// ---------------------------------------------------------------------

public class GUI {
    // Variables to store user data
    private JFrame frame;
    private Profile userProfile;
    private Goal userGoal;
    private ArrayList<Meal> meals;

    // ---------------------------------------------------------------------

    public GUI() {
        userProfile = new Profile(); // Create empty profile
        frame = new JFrame();
        userGoal = new Goal(); // ADD THIS

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // window fills the screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("CalorieAppTracker");
        frame.setLocationRelativeTo(null); // Center the window
        showIntroScreen();

        frame.setVisible(true);
    }
    // ---------------------------------------------------------------------

    private void showIntroScreen() {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 245)); // Light gray background

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2;
        int centerY = screenSize.height / 2;
        frame.add(panel);

        JLabel titleLabel = new JLabel("CalorieAppTracker");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 64));
        titleLabel.setForeground(new Color(30, 100, 60));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(centerX - 450, 80, 900, 80);
        panel.add(titleLabel);

        JLabel taglineLabel = new JLabel("Your Personal Health & Fitness Companion");
        taglineLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        taglineLabel.setForeground(new Color(100, 100, 100));
        taglineLabel.setHorizontalAlignment(SwingConstants.CENTER);
        taglineLabel.setBounds(centerX - 450, 170, 900, 30);
        panel.add(taglineLabel);

        JTextArea features = new JTextArea(
                "• Track Daily Calories & Meals\n\n" +
                        "• Set & Achieve Personal Goals\n\n" +
                        "• Monitor Your Progress Over Time");
        features.setFont(new Font("SansSerif", Font.PLAIN, 18));
        features.setForeground(new Color(80, 80, 80));
        features.setBackground(new Color(245, 245, 245));
        features.setEditable(false);
        features.setFocusable(false);
        features.setBorder(null);
        features.setBounds(centerX - 125, 250, 700, 150);
        panel.add(features);

        // Continue Button
        JButton continueButton = new JButton("Get Started");
        continueButton.setBounds(centerX - 125, centerY + 50, 250, 55);
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
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProfileScreen();
            }
        });
        panel.add(continueButton);

    }

    // ---------------------------------------------------------------------
    private void showProfileScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 245));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2;
        int startY = 50;

        // HEADER
        JLabel headerText = new JLabel("Create Your Profile");
        headerText.setFont(new Font("SansSerif", Font.BOLD, 40));
        headerText.setForeground(new Color(50, 50, 50));
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBounds(centerX - 300, startY, 600, 50);
        panel.add(headerText);

        int labelX = centerX - 400;
        int fieldX = centerX - 150;

        // First Name
        JLabel firstName = new JLabel("First Name:");
        firstName.setFont(new Font("SansSerif", Font.BOLD, 16));
        firstName.setBounds(labelX, startY + 120, 200, 30);
        panel.add(firstName);

        JTextField firstNameText = new JTextField();
        firstNameText.setBounds(fieldX, startY + 120, 450, 40);
        firstNameText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        panel.add(firstNameText);

        // Last Name
        JLabel lastName = new JLabel("Last Name:");
        lastName.setFont(new Font("SansSerif", Font.BOLD, 16));
        lastName.setBounds(labelX, startY + 180, 200, 30);
        panel.add(lastName);

        JTextField lastNameText = new JTextField();
        lastNameText.setBounds(fieldX, startY + 180, 450, 40);
        lastNameText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        panel.add(lastNameText);

        // Age
        JLabel age = new JLabel("Age:");
        age.setFont(new Font("SansSerif", Font.BOLD, 16));
        age.setBounds(labelX, startY + 240, 200, 30);
        panel.add(age);

        JTextField ageText = new JTextField();
        ageText.setBounds(fieldX, startY + 240, 450, 40);
        ageText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        panel.add(ageText);

        // Gender
        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("SansSerif", Font.BOLD, 16));
        gender.setBounds(labelX, startY + 300, 200, 30);
        panel.add(gender);

        String[] genderOptions = { "Select", "Male", "Female" };
        JComboBox<String> genderCombo = new JComboBox<>(genderOptions);
        genderCombo.setBounds(fieldX, startY + 300, 450, 40);
        genderCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        panel.add(genderCombo);

        // Height
        JLabel height = new JLabel("Height (cm):");
        height.setFont(new Font("SansSerif", Font.BOLD, 16));
        height.setBounds(labelX, startY + 360, 200, 30);
        panel.add(height);

        JTextField heightText = new JTextField();
        heightText.setBounds(fieldX, startY + 360, 450, 40);
        heightText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        panel.add(heightText);

        // Weight
        JLabel weight = new JLabel("Weight (kg):");
        weight.setFont(new Font("SansSerif", Font.BOLD, 16));
        weight.setBounds(labelX, startY + 420, 200, 30);
        panel.add(weight);

        JTextField weightText = new JTextField();
        weightText.setBounds(fieldX, startY + 420, 450, 40);
        weightText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        panel.add(weightText);

        // Activity Level
        JLabel activityLevel = new JLabel("Activity Level:");
        activityLevel.setFont(new Font("SansSerif", Font.BOLD, 16));
        activityLevel.setBounds(labelX, startY + 480, 200, 30);
        panel.add(activityLevel);

        String[] activityLevelOptions = { "Select", "Light", "Moderate", "Active", "Very Active" };
        JComboBox<String> activityLevelCombo = new JComboBox<>(activityLevelOptions);
        activityLevelCombo.setBounds(fieldX, startY + 480, 450, 40);
        activityLevelCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        panel.add(activityLevelCombo);

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

        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields!",
                            "Error", JOptionPane.ERROR_MESSAGE);
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

                    JOptionPane.showMessageDialog(frame,
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

                    showGoalScreen();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Age, height, and weight must be valid numbers.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(continueButton);
        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }
    // ---------------------------------------------------------------------

    private void showGoalScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 245));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2;
        int startY = 80;

        // HEADER
        JLabel headerText = new JLabel("Set Your Goal");
        headerText.setFont(new Font("SansSerif", Font.BOLD, 40));
        headerText.setForeground(new Color(50, 50, 50));
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBounds(centerX - 300, startY, 600, 50);
        panel.add(headerText);

        int labelX = centerX - 400;
        int fieldX = centerX - 150;

        // Goal Type
        JLabel goalType = new JLabel("Goal Type:");
        goalType.setFont(new Font("SansSerif", Font.BOLD, 16));
        goalType.setBounds(labelX, startY + 120, 200, 30);
        panel.add(goalType);

        String[] goalOptions = { "Select", "Lose", "Maintain", "Gain" };
        JComboBox<String> goalCombo = new JComboBox<>(goalOptions);
        goalCombo.setBounds(fieldX, startY + 120, 450, 40);
        goalCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        goalCombo.setBackground(Color.WHITE);
        panel.add(goalCombo);

        // Target Weight
        JLabel targetWeight = new JLabel("Target Weight (kg):");
        targetWeight.setFont(new Font("SansSerif", Font.BOLD, 16));
        targetWeight.setBounds(labelX, startY + 190, 200, 30);
        panel.add(targetWeight);

        JTextField targetWeightText = new JTextField();
        targetWeightText.setBounds(fieldX, startY + 190, 450, 40);
        targetWeightText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        panel.add(targetWeightText);

        // Time Frame
        JLabel timeFrame = new JLabel("Time Frame (months):");
        timeFrame.setFont(new Font("SansSerif", Font.BOLD, 16));
        timeFrame.setBounds(labelX, startY + 260, 200, 30);
        panel.add(timeFrame);

        JTextField timeFrameText = new JTextField();
        timeFrameText.setBounds(fieldX, startY + 260, 450, 40);
        timeFrameText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        panel.add(timeFrameText);

        // Continue Button
        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(centerX - 140, startY + 350, 280, 60);
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

        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String goalType = (String) goalCombo.getSelectedItem();
                String targetWeightInput = targetWeightText.getText().trim();
                String timeFrameInput = timeFrameText.getText().trim();

                if (targetWeightInput.isEmpty() || timeFrameInput.isEmpty() || goalType.equals("Select")) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int months = Integer.parseInt(timeFrameInput);
                    double targetWeightValue = Double.parseDouble(targetWeightInput);

                    if (targetWeightValue <= 0 || months <= 0) {
                        JOptionPane.showMessageDialog(frame,
                                "Target weight and time frame must be positive!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    userGoal.setGoalType(goalType);
                    userGoal.setTargetWeight(targetWeightValue);
                    userGoal.setMonths(months);
                    userGoal.calculateDailyCalories(userProfile);

                    JOptionPane.showMessageDialog(frame,
                            String.format(
                                    "Goal set!\n\nType: %s weight\nTarget: %.1f kg in %d months\n\nDaily Calorie Target: %.0f kcal",
                                    goalType, targetWeightValue, months, userGoal.getDailyCalorieGoal()),
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    showAddMealPage();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Target weight and time frame must be valid numbers.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(continueButton);
        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    // ---------------------------------------------------------------------
    private void showAddMealPage() {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 245));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2;
        int startY = 80;

        // HEADER
        JLabel headerText = new JLabel("Log a Meal");
        headerText.setFont(new Font("SansSerif", Font.BOLD, 40));
        headerText.setForeground(new Color(50, 50, 50));
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBounds(centerX - 300, startY, 600, 50);
        panel.add(headerText);

        int labelX = centerX - 400;
        int fieldX = centerX - 150;

        // Goal Type
        JLabel mealName = new JLabel("Meal Name: ");
        mealName.setFont(new Font("SansSerif", Font.BOLD, 16));
        mealName.setBounds(labelX, startY + 120, 200, 30);
        panel.add(mealName);


        JTextField mealNameText = new JTextField();
        mealNameText.setBounds(fieldX, startY + 120, 450, 40);
        mealNameText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        panel.add(mealNameText);




        JLabel mealType = new JLabel("Meal Type: ");
        mealType.setFont(new Font("SansSerif", Font.BOLD, 16));
        mealType.setBounds(labelX, startY + 190, 200, 30);
        panel.add(mealType);

        String[] mealTypeOptions = { "Select", "Breakfast", "Lunch", "Dinner", "Snack" };
        JComboBox<String> mealTypeCombo = new JComboBox<>(mealTypeOptions);
        mealTypeCombo.setBounds(fieldX, startY + 190, 450, 40);
        mealTypeCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        mealTypeCombo.setBackground(Color.WHITE);
        panel.add(mealTypeCombo);


        JLabel servings = new JLabel("Time Frame (months):");
        servings.setFont(new Font("SansSerif", Font.BOLD, 16));
        servings.setBounds(labelX, startY + 260, 200, 30);
        panel.add(servings);

        JTextField servingsText = new JTextField();
        servingsText.setBounds(fieldX, startY + 260, 450, 40);
        servingsText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        panel.add(servingsText);

        // Time Frame
        JLabel caloriesPerServings = new JLabel("Calories per Serving: ");
        caloriesPerServings.setFont(new Font("SansSerif", Font.BOLD, 16));
        caloriesPerServings.setBounds(labelX, startY + 330, 200, 30);
        panel.add(caloriesPerServings);

        JTextField caloriesPerServingsText = new JTextField();
        caloriesPerServingsText.setBounds(fieldX, startY + 330, 450, 40);
        caloriesPerServingsText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        panel.add(caloriesPerServingsText);

        // Continue Button
        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(centerX - 140, startY + 420, 280, 60);
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


        // Cancel Button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(centerX - 140, startY + 500, 280, 50);
        cancelButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        cancelButton.setBackground(new Color(200, 80, 80));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setOpaque(true);
        cancelButton.setContentAreaFilled(true);
        cancelButton.setBorder(BorderFactory.createEmptyBorder());
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        cancelButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                cancelButton.setBackground(new Color(180, 60, 60));
            }
            public void mouseExited(MouseEvent e) {
                cancelButton.setBackground(new Color(200, 80, 80));
            }
        });

        // Action → go back to home page
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //showHomePage();
            }
        });

        panel.add(cancelButton);

    
        panel.add(continueButton);
        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }







    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}