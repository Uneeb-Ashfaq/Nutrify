import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 

public class AddMealPanel extends JPanel {
    private GUI app; 

    public AddMealPanel(GUI app) {
        this.app = app;


        
        setLayout(null);
        setBackground(new Color(245, 245, 245));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2;
        int startY = 80;

        // HEADER
        JLabel headerText = new JLabel("Log a Meal");
        headerText.setFont(new Font("SansSerif", Font.BOLD, 40));
        headerText.setForeground(new Color(50, 50, 50));
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBounds(centerX - 300, startY, 600, 50);
        add(headerText);

        int labelX = centerX - 400;
        int fieldX = centerX - 150;

        // Goal Type
        JLabel mealName = new JLabel("Meal Name: ");
        mealName.setFont(new Font("SansSerif", Font.BOLD, 16));
        mealName.setBounds(labelX, startY + 120, 200, 30);
        add(mealName);


        JTextField mealNameText = new JTextField();
        mealNameText.setBounds(fieldX, startY + 120, 450, 40);
        mealNameText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(mealNameText);




        JLabel mealType = new JLabel("Meal Type: ");
        mealType.setFont(new Font("SansSerif", Font.BOLD, 16));
        mealType.setBounds(labelX, startY + 190, 200, 30);
        add(mealType);

        String[] mealTypeOptions = { "Select", "Breakfast", "Lunch", "Dinner", "Snack" };
        JComboBox<String> mealTypeCombo = new JComboBox<>(mealTypeOptions);
        mealTypeCombo.setBounds(fieldX, startY + 190, 450, 40);
        mealTypeCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        mealTypeCombo.setBackground(Color.WHITE);
        add(mealTypeCombo);


        JLabel servings = new JLabel("Number of Servings:");
        servings.setFont(new Font("SansSerif", Font.BOLD, 16));
        servings.setBounds(labelX, startY + 260, 200, 30);
        add(servings);

        JTextField servingsText = new JTextField();
        servingsText.setBounds(fieldX, startY + 260, 450, 40);
        servingsText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(servingsText);

        // Time Frame
        JLabel caloriesPerServings = new JLabel("Calories per Serving: ");
        caloriesPerServings.setFont(new Font("SansSerif", Font.BOLD, 16));
        caloriesPerServings.setBounds(labelX, startY + 330, 200, 30);
        add(caloriesPerServings);

        JTextField caloriesPerServingsText = new JTextField();
        caloriesPerServingsText.setBounds(fieldX, startY + 330, 450, 40);
        caloriesPerServingsText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(caloriesPerServingsText);

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


        continueButton.addActionListener(e -> {
            String name = mealNameText.getText().trim();
            String type = (String) mealTypeCombo.getSelectedItem();
            String servingsStr = servingsText.getText().trim();
            String caloriesStr = caloriesPerServingsText.getText().trim();

            if (name.isEmpty() || servingsStr.isEmpty() || caloriesStr.isEmpty() ||
                type.equals("Select")) {
                JOptionPane.showMessageDialog(
                        AddMealPanel.this,
                        "Please fill in all fields and select a meal type.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            try {
                double serving = Double.parseDouble(servingsStr);
                double caloriesPerServing = Double.parseDouble(caloriesStr);

                if (serving <= 0 || caloriesPerServing <= 0) {
                    JOptionPane.showMessageDialog(
                            AddMealPanel.this,
                            "Servings and calories must be positive numbers.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    ); 
                    return;
                }

                Meal meal = new Meal(type, name, serving, caloriesPerServing);
                app.getMeals().add(meal);

                JOptionPane.showMessageDialog(
                        AddMealPanel.this,
                        String.format("Meal saved:\n%s (%.1f servings, %.0f kcal per serving)",
                                      name, servings, caloriesPerServing),
                        "Meal Logged",
                        JOptionPane.INFORMATION_MESSAGE
                );

                app.showDashboard();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        AddMealPanel.this,
                        "Servings and calories must be valid numbers.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
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
        cancelButton.addActionListener(e -> app.showDashboard());
        continueButton.addActionListener(e -> app.showDashboard());

        add(cancelButton);
        add(continueButton);

    }
}