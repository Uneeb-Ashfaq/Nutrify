import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class DashboardPanel extends JPanel {
    private GUI app;
    private double todayCalories = 0;

    public DashboardPanel(GUI app) {
        this.app = app;
        setLayout(null);
        setBackground(new Color(245, 245, 245));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenW = screenSize.width;

        // Header 
        JLabel welcomeLabel = new JLabel("Welcome back, " + app.getUserProfile().getFirstName() + "! 👋");
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        welcomeLabel.setForeground(new Color(40, 40, 40));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBounds(0, 40, screenW, 50);
        add(welcomeLabel);

        JLabel dateLabel = new JLabel(LocalDate.now().toString());
        dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        dateLabel.setForeground(new Color(120, 120, 120));
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dateLabel.setBounds(0, 90, screenW, 25);
        add(dateLabel);


        todayCalories = 0;
        for (Meal meal : app.getMeals()) {
            todayCalories += meal.getCalories() * meal.getServingAmount();
        }

 
        int cardY = 140;
        int cardWidth = 340;
        int cardSpacing = 30;
        int totalCardsWidth = cardWidth * 3 + cardSpacing * 2;
        int cardsStartX = (screenW - totalCardsWidth) / 2;

        // Card 1: Today’s Calories
        JPanel calorieCard = createStatCard(
                "Today's Calories",
                String.format("%.0f / %.0f kcal", todayCalories, app.getUserGoal().getDailyCalorieGoal()),
                String.format("%.0f remaining",
                        Math.max(0, app.getUserGoal().getDailyCalorieGoal() - todayCalories)),
                new Color(255, 243, 224),
                new Color(255, 183, 77)
        );
        calorieCard.setBounds(cardsStartX, cardY, cardWidth, 160);
        add(calorieCard);

        // Card 2: Goal Progress
        double remaining = Math.abs(app.getUserGoal().getTargetWeight() - app.getUserProfile().getWeight());
        String goalText =
                app.getUserGoal().getGoalType().equals("Lose") ? "to lose" :
                app.getUserGoal().getGoalType().equals("Gain") ? "to gain" : "to maintain";

        JPanel goalCard = createStatCard(
                "Goal Progress",
                String.format("%.1f kg %s", remaining, goalText),
                app.getUserGoal().getMonths() + " months remaining",
                new Color(232, 245, 253),
                new Color(66, 165, 245)
        );
        goalCard.setBounds(cardsStartX + cardWidth + cardSpacing, cardY, cardWidth, 160);
        add(goalCard);

        // Card 3: Activity Level
        JPanel activityCard = createStatCard(
                "Activity Level",
                app.getUserProfile().getActivityLevel(),
                "Current level",
                new Color(232, 245, 233),
                new Color(102, 187, 106)
        );
        activityCard.setBounds(cardsStartX + (cardWidth + cardSpacing) * 2, cardY, cardWidth, 160);
        add(activityCard);

        // =======================
        // Profile + Meals columns (centered as block)
        // =======================
        int contentY = cardY + 190;
        int profileWidth = 520;
        int mealsWidth = 480;
        int columnsSpacing = 40;
        int totalContentWidth = profileWidth + mealsWidth + columnsSpacing;
        int contentStartX = (screenW - totalContentWidth) / 2;

        // Profile header + box
        JLabel profileHeader = new JLabel("Your Profile");
        profileHeader.setFont(new Font("SansSerif", Font.BOLD, 22));
        profileHeader.setForeground(new Color(50, 50, 50));
        profileHeader.setBounds(contentStartX, contentY, 300, 30);
        add(profileHeader);

        JPanel profileBox = new JPanel();
        profileBox.setLayout(null);
        profileBox.setBackground(Color.WHITE);
        profileBox.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2));
        profileBox.setBounds(contentStartX, contentY + 40, profileWidth, 280);

        JTextArea profileText = new JTextArea();
        profileText.setBounds(20, 20, profileWidth - 40, 240);
        profileText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        profileText.setBackground(Color.WHITE);
        profileText.setEditable(false);
        profileText.setBorder(null);
        profileText.setText(String.format(
                "Name: %s %s\n" +
                "Age: %d years\n" +
                "Gender: %s\n" +
                "Height: %.1f cm\n" +
                "Current Weight: %.1f kg\n" +
                "Activity: %s\n" +
                "BMI: %.1f\n\n" +
                "Goal: %s weight to %.1f kg\n" +
                "Timeframe: %d months\n" +
                "Daily Target: %.0f kcal",
                app.getUserProfile().getFirstName(),
                app.getUserProfile().getLastName(),
                app.getUserProfile().getAge(),
                app.getUserProfile().getGender(),
                app.getUserProfile().getHeight(),
                app.getUserProfile().getWeight(),
                app.getUserProfile().getActivityLevel(),
                app.getUserProfile().getBMI(),
                app.getUserGoal().getGoalType(),
                app.getUserGoal().getTargetWeight(),
                app.getUserGoal().getMonths(),
                app.getUserGoal().getDailyCalorieGoal()
        ));
        profileBox.add(profileText);
        add(profileBox);

        // Meals header + box
        int mealsX = contentStartX + profileWidth + columnsSpacing;

        JLabel mealsHeader = new JLabel("Today's Meals");
        mealsHeader.setFont(new Font("SansSerif", Font.BOLD, 22));
        mealsHeader.setForeground(new Color(50, 50, 50));
        mealsHeader.setBounds(mealsX, contentY, 300, 30);
        add(mealsHeader);

        JPanel mealsBox = new JPanel();
        mealsBox.setLayout(null);
        mealsBox.setBackground(Color.WHITE);
        mealsBox.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2));
        mealsBox.setBounds(mealsX, contentY + 40, mealsWidth, 280);

        if (app.getMeals().isEmpty()) {
            JLabel noMeals = new JLabel(
                    "<html><center>No meals logged yet.<br><br>Click 'Log Meal' to get started!</center></html>");
            noMeals.setFont(new Font("SansSerif", Font.PLAIN, 16));
            noMeals.setForeground(new Color(150, 150, 150));
            noMeals.setHorizontalAlignment(SwingConstants.CENTER);
            noMeals.setBounds(0, 80, mealsWidth, 100);
            mealsBox.add(noMeals);
        } else {
            JTextArea mealsText = new JTextArea();
            mealsText.setBounds(20, 20, mealsWidth - 40, 240);
            mealsText.setFont(new Font("SansSerif", Font.PLAIN, 15));
            mealsText.setBackground(Color.WHITE);
            mealsText.setEditable(false);
            mealsText.setBorder(null);

            StringBuilder mealsList = new StringBuilder();
            int count = 1;
            for (Meal meal : app.getMeals()) {
                mealsList.append(String.format(
                        "%d. %s (%s)\n   %.0f kcal\n\n",
                        count++,
                        meal.getName(),
                        meal.getMealType(),
                        meal.getCalories() * meal.getServingAmount()
                ));
            }
            mealsText.setText(mealsList.toString());
            mealsBox.add(mealsText);
        }
        add(mealsBox);

        // =======================
        // Bottom buttons (3 centered)
        // =======================
        int btnY = contentY + 350;
        int btnWidth = 240;
        int btnHeight = 60;
        int btnSpacing = 40;
        int totalBtnWidth = btnWidth * 3 + btnSpacing * 2;
        int btnStartX = (screenW - totalBtnWidth) / 2;

        JButton logMealBtn = createActionButton("+ Log Meal", new Color(46, 204, 113));
        logMealBtn.setBounds(btnStartX, btnY, btnWidth, btnHeight);
        logMealBtn.addActionListener(e -> app.showAddMealPage());
        add(logMealBtn);

        JButton viewProgressBtn = createActionButton("📈 View Progress", new Color(52, 152, 219));
        viewProgressBtn.setBounds(btnStartX + btnWidth + btnSpacing, btnY, btnWidth, btnHeight);
        viewProgressBtn.addActionListener(e -> showProgressDialog());
        add(viewProgressBtn);

        JButton editProfileBtn = createActionButton("⚙️ Edit Profile", new Color(150, 150, 150));
        editProfileBtn.setBounds(btnStartX + (btnWidth + btnSpacing) * 2, btnY, btnWidth, btnHeight);
        editProfileBtn.addActionListener(e -> app.showProfilePanel());
        add(editProfileBtn);

        JButton chatbot = createActionButton("⚙️ Talk to chatbot!", new Color(100, 100, 100));
        chatbot.setBounds(btnStartX + (btnWidth + btnSpacing) * 3, btnY, btnWidth, btnHeight);
        chatbot.addActionListener(e -> app.showChatBotPanel());
        add(chatbot);
    }



    // ===== helper: stat card =====
    private JPanel createStatCard(String title, String mainValue, String subValue,
                                  Color bgColor, Color accentColor) {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(bgColor);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(accentColor, 3),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        titleLabel.setForeground(new Color(80, 80, 80));
        titleLabel.setBounds(15, 15, 310, 25);
        card.add(titleLabel);

        JLabel mainLabel = new JLabel(mainValue);
        mainLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        mainLabel.setForeground(new Color(40, 40, 40));
        mainLabel.setBounds(15, 50, 310, 40);
        card.add(mainLabel);

        JLabel subLabel = new JLabel(subValue);
        subLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subLabel.setForeground(new Color(100, 100, 100));
        subLabel.setBounds(15, 100, 310, 25);
        card.add(subLabel);

        return card;
    }

    // ===== helper: button style =====
    private JButton createActionButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        Color hoverColor = bgColor.darker();
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    // ===== progress popup =====
    private void showProgressDialog() {
        double weightDiff = app.getUserGoal().getTargetWeight() - app.getUserProfile().getWeight();
        double weeklyRate = Math.abs(weightDiff) / (app.getUserGoal().getMonths() * 4.0);

        JOptionPane.showMessageDialog(this,
                String.format(
                        "📊 PROGRESS OVERVIEW\n\n" +
                        "Current Weight: %.1f kg\n" +
                        "Target Weight: %.1f kg\n" +
                        "Weight to %s: %.1f kg\n\n" +
                        "⏱️ TIMELINE\n" +
                        "Time Frame: %d months\n" +
                        "Weekly Rate: %.2f kg/week\n\n" +
                        "🔥 CALORIES\n" +
                        "Daily Goal: %.0f kcal\n" +
                        "Today's Intake: %.0f kcal\n" +
                        "Remaining: %.0f kcal\n\n" +
                        "💪 Keep pushing forward!",
                        app.getUserProfile().getWeight(),
                        app.getUserGoal().getTargetWeight(),
                        weightDiff > 0 ? "gain" : "lose",
                        Math.abs(weightDiff),
                        app.getUserGoal().getMonths(),
                        weeklyRate,
                        app.getUserGoal().getDailyCalorieGoal(),
                        todayCalories,
                        Math.max(0, app.getUserGoal().getDailyCalorieGoal() - todayCalories)
                ),
                "Your Progress",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
