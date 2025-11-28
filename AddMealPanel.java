import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 


/**
 * AddMealPanel
 * Lets the user log a meal (name, type, servings, calories).
 * Validates input, creates a LoggedMeal, and sends back to Dashboard.
 */
public class AddMealPanel extends JPanel {
    private GUI app; 

    public AddMealPanel(GUI app) {
        this.app = app;
        setLayout(null);
        setBackground(new Color(245, 245, 245));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2;

        // ===== Header =====
        JLabel headerText = new JLabel("Log a Meal");
        headerText.setFont(new Font("SansSerif", Font.BOLD, 40));
        headerText.setForeground(new Color(50, 50, 50));
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBounds(centerX - 300, 80, 600, 50);
        add(headerText);

        // ===== Meal Name =====
        JLabel mealName = new JLabel("Meal Name: ");
        mealName.setFont(new Font("SansSerif", Font.BOLD, 16));
        mealName.setBounds(centerX - 350, 200 , 200, 30);
        add(mealName);

        JTextField mealNameText = new JTextField();
        mealNameText.setBounds(centerX - 150, 200 , 450, 40);
        mealNameText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(mealNameText);

        // ===== Meal Type =====
        JLabel mealType = new JLabel("Meal Type: ");
        mealType.setFont(new Font("SansSerif", Font.BOLD, 16));
        mealType.setBounds(centerX - 350, 270, 200, 30);
        add(mealType);

        String[] mealTypeOptions = { "Select", "Breakfast", "Lunch", "Dinner", "Snack" };
        JComboBox<String> mealTypeCombo = new JComboBox<>(mealTypeOptions);
        mealTypeCombo.setBounds(centerX - 150, 270, 450, 40);
        mealTypeCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        mealTypeCombo.setBackground(Color.WHITE);
        add(mealTypeCombo);

        // ===== Servings =====
        JLabel servings = new JLabel("Number of Servings:");
        servings.setFont(new Font("SansSerif", Font.BOLD, 16));
        servings.setBounds(centerX - 350, 340, 200, 30);
        add(servings);

        JTextField servingsText = new JTextField();
        servingsText.setBounds(centerX - 150, 340, 450, 40);
        servingsText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(servingsText);

        // ===== Calories per Serving =====
        JLabel caloriesPerServings = new JLabel("Calories per Serving: ");
        caloriesPerServings.setFont(new Font("SansSerif", Font.BOLD, 16));
        caloriesPerServings.setBounds(centerX - 350, 410 , 200, 30);
        add(caloriesPerServings);

        JTextField caloriesPerServingsText = new JTextField();
        caloriesPerServingsText.setBounds(centerX - 150, 410 , 450, 40);
        caloriesPerServingsText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(caloriesPerServingsText);

        // ===== Continue Button =====
        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(centerX - 140, 500 , 280, 60);
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

        // ===== When Continue button is clicked =====
        continueButton.addActionListener(e -> {
            String name = mealNameText.getText().trim();
            String type = (String) mealTypeCombo.getSelectedItem();
            String servingsStr = servingsText.getText().trim();
            String caloriesStr = caloriesPerServingsText.getText().trim();

            // Basic validation to see all fields have something in them
            if (name.isEmpty() || servingsStr.isEmpty() || caloriesStr.isEmpty() ||type.equals("Select")) {
                JOptionPane.showMessageDialog( AddMealPanel.this, "Please fill in all fields and select a meal type.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Convert text to numbers

                double serving = Double.parseDouble(servingsStr);
                double caloriesPerServing = Double.parseDouble(caloriesStr);

                if (serving <= 0 || caloriesPerServing <= 0) {
                    JOptionPane.showMessageDialog(AddMealPanel.this,"Servings and calories must be positive numbers.","Error",JOptionPane.ERROR_MESSAGE); 
                    return;
                }
                // Create meal (polymorphism)
                Meal meal = new Meal(type, name, serving, caloriesPerServing);
                app.getMeals().add(meal);  // Add to list
                app.showDashboard();    // Go back home

                

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(AddMealPanel.this,"Servings and calories must be valid numbers.","Error",JOptionPane.ERROR_MESSAGE);
            }
        });

        add(continueButton);

        // ===== Cancel Button =====
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(centerX - 140, 580 , 280, 50);
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

        // Go back to dashboard without saving
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