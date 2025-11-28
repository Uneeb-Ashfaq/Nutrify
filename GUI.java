import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;




/**
 * GUI
 * Main application controller.
 * Creates the main window, holds shared data (Profile, Goal, Meals),
 * and switches between different screens (panels).
 */
public class GUI {
    // Variables to store user data
    private JFrame frame;
    private Profile userProfile;
    private Goal userGoal;
    private ArrayList<Meal> meals;
    private String registeredEmail;
    private String registeredPassword;

    // Panels used in the app
    private IntroPanel introPanel;
    private ProfilePanel profilePanel;
    private SignupPanel signPanel;
    private LoginPanel loginPanel;
    private GoalPanel goalPanel;
    private AddMealPanel addMealPanel;
    private ResetPasswordPanel resetPasswordPanel;


    // ---------------------------------------------------------------------
    /**
     * Constructs the GUI, initializes data and panels, and shows the intro screen.
     */
    public GUI() {
        frame = new JFrame("Nutrify");

        // Create empty data objects
        userProfile = new Profile(); // Create empty profile
        userGoal = new Goal();
        meals = new ArrayList<>();

        // Setup main window
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // window fills the screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window


        // Create panels (some are reused, some recreated in show methods)
        introPanel = new IntroPanel(this);
        loginPanel = new LoginPanel(this);
        signPanel = new SignupPanel(this);
        profilePanel = new ProfilePanel(this);
        goalPanel = new GoalPanel(this);
        addMealPanel = new AddMealPanel(this);
        resetPasswordPanel = new ResetPasswordPanel(this);

        // Show first screen
        showIntroPanel();
        frame.setVisible(true);
    }


// ========= Navigation methods =========

    public void showIntroPanel() {
        setContent(introPanel);
    }

    public void showLoginPanel() {
        setContent(new LoginPanel(this));    // Create fresh LoginPanel each time (so fields are cleared)
    }

    public void showSignupPanel() {
        setContent(new SignupPanel(this));        // Create fresh SignupPanel each time
    }

    public void showresetPassword() {
        setContent(resetPasswordPanel);
    }

    public void showDashboard() {
        setContent(new DashboardPanel(this));
    }

    public void showProfilePanel() {
        setContent(profilePanel);
    }
    
    public void showEditProfilePanel() {
        setContent(new EditProfilePanel(this));
    }
    public void showGoalPanel() {
        setContent(goalPanel);
    }

    public void showAddMealPage() {
        setContent(new AddMealPanel(this));
    }
    /**
     * Helper method that swaps the visible panel.
     */
    private void setContent(JPanel panel) {
        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }

// ========= Getters for shared data =========

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
    // ========= Login / signup credentials =========

    public void setRegisteredCredentials(String email, String password) {
        this.registeredEmail = email;
        this.registeredPassword = password;
    }

    public String getRegisteredEmail() {
        return registeredEmail;
    }

    public String getRegisteredPassword() {
        return registeredPassword;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}