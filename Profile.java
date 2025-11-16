public class Profile {

    // ==========================
    // Private Attributes
    // ==========================
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private double height;
    private double weight;
    private String activityLevel;


    // ==========================
    // Constructors
    // ==========================

    /**
     * Default constructor
     */
    public Profile() {
        this.firstName = "";
        this.lastName = "";
        this.age = 0;
        this.gender = "";
        this.height = 0.0;
        this.weight = 0.0;
        this.activityLevel = "";
    }


    /**
     * Parameterized constructor
     *
     * @param firstName
     * @param lastName
     * @param age
     * @param gender
     * @param height
     * @param weight
     * @param activityLevel
     */
    public Profile(String firstName, String lastName, int age, String gender,
                   double height, double weight, String activityLevel) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
    }





    // ==========================
    // Getters and Setters
    // ==========================

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getActivityLevel() {
        return this.activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }


    // ==========================
    // toString
    // ==========================

    @Override
    public String toString() {
        String string = "";
        string += "Profile: " + this.firstName + " " + this.lastName + "\n";
        string += "Age: " + this.age + " | Gender: " + this.gender + "\n";
        string += "Height: " + this.height + " cm | Weight: " + this.weight + " kg\n";
        string += "Activity Level: " + this.activityLevel + "\n";
        return string;
    }

}
