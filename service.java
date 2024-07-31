import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

enum UserRole {
    ADMIN, USER, GUEST
}

/**
 * Represents an activity performed by a user. It captures essential details such as
 * username, action, and date. The class provides methods to retrieve these attributes
 * and format the activity in a specific string representation.
 */
class UserActivity {
    private String username;
    private String action;
    private Date date;

    public UserActivity(String username, String action, Date date) {
        this.username = username;
        this.action = action;
        this.date = date;
    }

    /**
     * Retrieves and returns a string value representing a user's name, referred to as
     * `username`. The returned string is an instance variable that stores the username.
     *
     * @returns a string representing the user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the value of the `action` variable. This suggests that it is a getter
     * method, used to retrieve the current state or value of the `action` attribute. It
     * provides read-only access to the `action` property for external use.
     *
     * @returns a string value stored in the `action` variable.
     */
    public String getAction() {
        return action;
    }

    /**
     * Retrieves and returns a `Date` object. It does not modify or manipulate any input,
     * but simply provides access to the stored `date`. The returned value is a snapshot
     * of the current date at the time the method was invoked.
     *
     * @returns a `Date` object representing a specific point in time.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns a string that combines the `username`, `action`, and the formatted date
     * in "yyyy-MM-dd" format, using `SimpleDateFormat`. The formatted date is obtained
     * from the `date` object.
     *
     * @returns a string containing formatted date and user-action information.
     */
    public String format() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return username + " performed " + action + " on " + sdf.format(date);
    }
}

/**
 * Represents an individual user with attributes such as name, age, activity status,
 * role, address, and a list of activities performed. The class also has getter methods
 * to retrieve these attributes and a constructor for initializing the object.
 */
class User {
    private String name;
    private int age;
    private boolean active;
    private UserRole role;
    private Address address;
    private List<UserActivity> activities;

    public User(String name, int age, boolean active, UserRole role, Address address, List<UserActivity> activities) {
        this.name = name;
        this.age = age;
        this.active = active;
        this.role = role;
        this.address = address;
        this.activities = activities;
    }

    /**
     * Retrieves a string value stored in the variable `name`. The retrieved value is
     * then returned by the function to its caller. This function allows external access
     * to the private member variable `name`, providing a way to obtain its current state.
     *
     * @returns a string representing an instance variable named "name".
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves and returns an integer value representing an individual's age. It appears
     * to be a simple getter method, providing access to a private or protected variable
     * named `age`. The returned value is presumably used by the calling code for further
     * processing or display purposes.
     *
     * @returns an integer value representing the age of an entity.
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns a boolean value indicating whether an object's state is currently active
     * or not. This value is determined by the value of the `active` variable, which is
     * directly accessed and returned without any modification. The function does not
     * perform any actions but simply retrieves and reports the status.
     *
     * @returns a boolean value indicating the current state of an object's activity.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Retrieves the value of an object's `role` attribute and returns it as a `UserRole`
     * object. The returned object represents a user's role or identity. This function
     * provides access to the stored role information for further processing or use.
     *
     * @returns an instance of the `UserRole` class representing a user's role.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Retrieves an instance of the `Address` class and returns it. This method allows
     * access to the `address` object, potentially for use or modification elsewhere in
     * the program. The returned object represents a location with specific attributes
     * such as street, city, state, etc.
     *
     * @returns an instance of the `Address` class.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns a list of user activities. It does not accept any parameters and simply
     * retrieves the pre-initialized list of activities, likely stored as an instance
     * variable within the class. The returned list is likely to be used elsewhere in the
     * program for further processing or display.
     *
     * @returns a list of user activities represented as `UserActivity` objects.
     */
    public List<UserActivity> getActivities() {
        return activities;
    }

    /**
     * Represents an address with properties for street, city, and country. It provides
     * getter methods to access these properties and overrides the toString method to
     * return a string representation of the address in the format "street, city, country".
     */
    public static class Address {
        private String street;
        private String city;
        private String country;

        public Address(String street, String city, String country) {
            this.street = street;
            this.city = city;
            this.country = country;
        }

        /**
         * Returns a string value representing the street address. The function simply retrieves
         * and provides access to an existing string variable named `street`. This variable
         * is likely set elsewhere in the code, making it possible to obtain the street
         * information when needed.
         *
         * @returns a string value representing the street information.
         */
        public String getStreet() {
            return street;
        }

        /**
         * Retrieves a value representing a city and returns it as a string. The returned
         * value is stored in the `city` variable. This method allows access to the city information.
         *
         * @returns a string representing the value of the `city` variable.
         */
        public String getCity() {
            return city;
        }

        /**
         * Returns a string value representing the country. It simply retrieves and exposes
         * the stored country information, allowing it to be accessed or used elsewhere in
         * the program.
         *
         * @returns a string value representing the country.
         */
        public String getCountry() {
            return country;
        }

        /**
         * Returns a string representation of an object, concatenating three attributes:
         * `street`, `city`, and `country`. The result is a comma-separated string containing
         * these values, providing a human-readable summary of the object's location. This
         * method overrides the default implementation in its superclass.
         *
         * @returns a string combining street, city, and country values.
         */
        @Override
        public String toString() {
            return street + ", " + city + ", " + country;
        }
    }
}

/**
 * Generates a comprehensive report for a list of users. It filters users based on
 * minimum age and activity status, then collects their activities that match a given
 * filter predicate. The report includes user details and their activities in a
 * formatted string.
 */
class UserActivityReport {

    /**
     * Generates a report for user activity based on specified conditions. It filters
     * users by age and activity status, then for each eligible user, it retrieves their
     * activities that match a given filter and formats them into a list. The result is
     * a list of reports.
     *
     * @param users collection of user objects that will be processed to generate the
     * activity report.
     *
     * Stream of `User` objects is filtered based on age and activity status. Each `User`
     * has an age and an active status, which can be included in the report depending on
     * the input parameters.
     *
     * @param minAge minimum age of users to be included in the report, and users with
     * an age less than this value are filtered out.
     *
     * @param includeInactive condition for filtering users based on their activity status,
     * allowing inactive users to be included in the report if set to true or excluded otherwise.
     *
     * @param activityFilter predicate that filters out certain user activities based on
     * specific conditions during the report generation process.
     *
     * Predicate<UserActivity> - specifies a condition for filtering user activities.
     *
     * @returns a list of formatted user reports.
     */
    public static List<String> generateUserActivityReport(
            List<User> users,
            int minAge,
            boolean includeInactive,
            Predicate<UserActivity> activityFilter
    ) {
        return users.stream()
                .filter(user -> user.getAge() >= minAge && (includeInactive || user.isActive()))
                .map(user -> {
                    List<String> activities = user.getActivities().stream()
                            .filter(activityFilter)
                            .map(UserActivity::format)
                            .collect(Collectors.toList());
                    return formatUserReport(user, activities);
                })
                .collect(Collectors.toList());
    }

    /**
     * Generates a formatted string that represents user information and their associated
     * activities. The string includes name, age, status, role, address, and a list of
     * activities with dashes in front of each activity.
     *
     * @param user object whose information is to be formatted into a string report,
     * providing details such as name, age, status, role, and address.
     *
     * Extracted from user: name, age, status, role, address.
     *
     * @param activities list of strings to be appended under the "Activities" section
     * of the formatted user report, displaying each activity on a new line with a prefix
     * "- ".
     *
     * It is a list containing strings representing user activities. Each string element
     * represents an activity with no further information provided.
     *
     * @returns a formatted string representing user information and their activities.
     */
    private static String formatUserReport(User user, List<String> activities) {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(user.getName()).append("\n")
                .append("Age: ").append(user.getAge()).append("\n")
                .append("Status: ").append(user.isActive() ? "Active" : "Inactive").append("\n")
                .append("Role: ").append(user.getRole()).append("\n")
                .append("Address: ").append(user.getAddress()).append("\n")
                .append("Activities: ").append("\n");
        activities.forEach(activity -> sb.append(" - ").append(activity).append("\n"));
        return sb.toString();
    }

    /**
     * Generates a user activity report based on a list of users and their activities,
     * excluding logout actions for users with age greater than or equal to 18. The report
     * contains user names and corresponding activities.
     *
     * @param args command-line arguments passed to the Java program, but it is not used
     * within the provided code snippet.
     *
     * Arrays, contains zero or more elements of type String.
     */
    public static void main(String[] args) {
        List<UserActivity> aliceActivities = new ArrayList<>();
        aliceActivities.add(new UserActivity("Alice", "login", new Date(1672531200000L))); // 2023-01-01
        aliceActivities.add(new UserActivity("Alice", "purchase", new Date(1676419200000L))); // 2023-02-15

        List<UserActivity> bobActivities = new ArrayList<>();
        bobActivities.add(new UserActivity("Bob", "logout", new Date(1679616000000L))); // 2023-03-22

        List<UserActivity> charlieActivities = new ArrayList<>();
        charlieActivities.add(new UserActivity("Charlie", "login", new Date(1681209600000L))); // 2023-04-10

        List<User> users = new ArrayList<>();
        users.add(new User("Alice", 25, true, UserRole.ADMIN,
                new User.Address("123 Main St", "Metropolis", "USA"), aliceActivities));
        users.add(new User("Bob", 17, false, UserRole.USER,
                new User.Address("456 Elm St", "Gotham", "USA"), bobActivities));
        users.add(new User("Charlie", 30, true, UserRole.GUEST,
                new User.Address("789 Oak St", "Star City", "USA"), charlieActivities));

        List<String> report = generateUserActivityReport(users, 18, true, activity -> !activity.getAction().equals("logout"));
        report.forEach(System.out::println);
    }
}
