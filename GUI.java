import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


// ---------------------------------------------------------------------

public class GUI {
    // Variables to store user data
    private JFrame frame;
    private Profile userProfile;
    private Goal userGoal;
    private ArrayList<Meal> meals;
    
    private IntroPanel introPanel;
    private ProfilePanel profilePanel;
    private GoalPanel goalPanel;
    private AddMealPanel addMealPanel;
    private DashboardPanel dashboardPanel;

    // ---------------------------------------------------------------------

    public GUI() {
        frame = new JFrame("CalorieAppTracker");
        userProfile = new Profile(); // Create empty profile
        userGoal = new Goal();
        meals = new ArrayList<>();

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // window fills the screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window
        
        introPanel = new IntroPanel(this);
        profilePanel = new ProfilePanel(this);
        goalPanel = new GoalPanel(this);
        addMealPanel = new AddMealPanel(this);
        dashboardPanel = new DashboardPanel(this);



        showIntroPanel();
        frame.setVisible(true);
    }

    public void showIntroPanel() {
        setContent(introPanel);
    }

    public void showDashboard() {
    setContent(dashboardPanel);
    }

    public void showProfilePanel() {
        setContent(profilePanel);
    }

    public void showGoalPanel() {
        setContent(goalPanel);
    }
   public void showAddMealPage() {
        setContent(addMealPanel);
    }

    private void setContent(JPanel panel) {
        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }

    
   public Profile getUserProfile() {
        return userProfile;
    }

    public Goal getUserGoal() {
        return userGoal;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}