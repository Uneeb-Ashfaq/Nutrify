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
    private String registeredEmail;
    private String registeredPassword;


    private IntroPanel introPanel;
    private ProfilePanel profilePanel;
    private SignupPanel signPanel;
    private LoginPanel loginPanel;
    private GoalPanel goalPanel;
    private AddMealPanel addMealPanel;
    private ResetPasswordPanel resetPasswordPanel;
    private ChatBotPanel chatbotPanel;


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
        loginPanel = new LoginPanel(this);
        signPanel = new SignupPanel(this);
        profilePanel = new ProfilePanel(this);
        goalPanel = new GoalPanel(this);
        addMealPanel = new AddMealPanel(this);
        chatbotPanel = new ChatBotPanel(this);
        resetPasswordPanel = new ResetPasswordPanel(this);


        showIntroPanel();
        frame.setVisible(true);
    }

    public void showIntroPanel() {
        setContent(introPanel);
    }

    public void showLoginPanel() {
        setContent(new LoginPanel(this));

    }

    public void showSignupPanel() {
        setContent(new SignupPanel(this));

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

    public void showChatBotPanel() {
        setContent(chatbotPanel);
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