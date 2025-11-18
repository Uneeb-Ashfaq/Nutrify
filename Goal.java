import java.util.ArrayList;
import java.util.List;
// ---------------------------------------------------------------------

public class Goal {
    // ==========================
    // Private Attributes
    // ==========================
    private String goalType;           // Lose, Gain, Maintain
    private double targetWeight;       // Target weight in kg
    private int months;                // Number of months to reach goal
    private double dailyCalorieGoal;   // Daily calorie goal calculated
    private List<Double> dailyCalories; // Tracks planned daily calorie intake

    // ==========================
    // Constructors
    // ==========================
    
    /**
     * Default constructor
     */
    public Goal() {
        this.goalType = "";
        this.targetWeight = 0.0;
        this.months = 0;
        this.dailyCalorieGoal = 0.0;
        this.dailyCalories = new ArrayList<>();
    }

    /**
     * Parameterized constructor
     * 
     * @param goalType
     * @param targetWeight
     * @param months
     */
    public Goal(String goalType, double targetWeight, int months) {
        this.goalType = goalType;
        this.targetWeight = targetWeight;
        this.months = months;
        this.dailyCalorieGoal = 0.0;
        this.dailyCalories = new ArrayList<>();
    }

    // ==========================
    // Getters and Setters
    // ==========================
    
    public String getGoalType() {
        return this.goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public double getTargetWeight() {
        return this.targetWeight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public int getMonths() {
        return this.months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public double getDailyCalorieGoal() {
        return this.dailyCalorieGoal;
    }

    public void setDailyCalorieGoal(double dailyCalorieGoal) {
        this.dailyCalorieGoal = dailyCalorieGoal;
    }

    public List<Double> getDailyCalories() {
        return this.dailyCalories;
    }

    // ==========================
    // Methods
    // ==========================

    /**
     * Calculate maintenance calories using Profile's weight and activity level
     * 
     * @param profile User's profile with physical stats
     * @return Daily maintenance calories
     */
    private double calculateMaintenanceCalories(Profile profile) {
        double bmr;
        
        // BMR formula (Mifflin-St Jeor)
        if (profile.getGender().equalsIgnoreCase("Male")) {
            bmr = 10 * profile.getWeight() + 6.25 * profile.getHeight() - 5 * profile.getAge() + 5;
        } else {
            bmr = 10 * profile.getWeight() + 6.25 * profile.getHeight() - 5 * profile.getAge() - 161;
        }

        // Activity multiplier
        double multiplier = 1.0;
        switch (profile.getActivityLevel().toLowerCase()) {
            case "light":
                multiplier = 1.375;
                break;
            case "moderate":
                multiplier = 1.55;
                break;
            case "active":
                multiplier = 1.725;
                break;
            case "very active":
                multiplier = 1.9;
                break;
            default:
                multiplier = 1.2; 
                break;
        }

        return bmr * multiplier;
    }

    /**
     * Calculate daily calorie goal based on weight difference and time frame
     * 
     * @param profile User's profile
     */
    public void calculateDailyCalories(Profile profile) {
        double currentWeight = profile.getWeight();
        double weightDiff = targetWeight - currentWeight; // +ve = gain, -ve = loss
        int totalDays = months * 30;

        double totalCaloriesChange = weightDiff * 7700; // 1 kg ≈ 7700 kcal
        double dailyAdjustment = totalCaloriesChange / totalDays;

        double maintenanceCalories = calculateMaintenanceCalories(profile);
        dailyCalorieGoal = maintenanceCalories + dailyAdjustment;

        // Fill ArrayList with daily calorie plan
        dailyCalories.clear();
        for (int i = 0; i < totalDays; i++) {
            dailyCalories.add(dailyCalorieGoal);
        }
    }

    // ==========================
    // toString
    // ==========================

    @Override
    public String toString() {
        String string = "";
        string += "Goal Type: " + this.goalType + "\n";
        string += "Target Weight: " + this.targetWeight + " kg\n";
        string += "Time Frame: " + this.months + " months\n";
        string += "Daily Calorie Goal: " + this.dailyCalorieGoal + " kcal\n";
        return string;
    }
}