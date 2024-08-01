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
 * Represents user activities with details such as username, action performed, and
 * date. It provides methods to retrieve these details and format the activity into
 * a human-readable string. The class encapsulates information about a single user
 * activity event.
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
     * Returns a string representing the username. It retrieves and provides access to
     * the value stored in the `username` variable, allowing it to be used elsewhere in
     * the code. The function does not modify the `username` value.
     *
     * @returns a string representing the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns a string value representing an action. It simply retrieves and returns the
     * stored `action` variable, providing access to its contents for external use. This
     * functionality allows other parts of the program to utilize the action information
     * without modifying the original source.
     *
     * @returns a string value of `action`.
     */
    public String getAction() {
        return action;
    }

    /**
     * Retrieves and returns a `Date` object. It simply accesses and provides the value
     * stored in the `date` variable, without performing any modifications or calculations
     * on it. The returned `Date` object represents a point in time.
     *
     * @returns a `Date`.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Generates a string describing an event, combining `username`, `action`, and date
     * (`yyyy-MM-dd`) formats using `SimpleDateFormat`. The resulting string represents
     * an activity performed by the specified username on the given date.
     *
     * @returns a string formatted as "username performed action on yyy-MM-dd".
     */
    public String format() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return username + " performed " + action + " on " + sdf.format(date);
    }
}

/**
 * Represents a user entity with attributes for name, age, active status, role,
 * address, and activities. It provides getter methods to access these attributes and
 * allows creation of users with associated data. The class also includes an inner
 * Address class to represent a user's location.
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
     * Returns a string representing an object's name. It simply retrieves and provides
     * the value of the `name` variable without any processing or modification. The
     * returned string is used to access the object's name for further use or display.
     *
     * @returns a string representing the object's name attribute.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns an integer value representing a person's age. The function does not take
     * any input parameters and simply retrieves the stored `age` value, which is presumably
     * an instance variable or field.
     *
     * @returns an integer value representing the age of an entity.
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns a boolean value indicating whether the related object is currently active
     * or not. It simply retrieves the current state of the `active` variable and reflects
     * it back to the caller. The returned value determines if the object is functioning
     * properly.
     *
     * @returns a boolean value indicating whether an object's state is active or not.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Returns an instance of type `UserRole`. It appears to provide access to a predefined
     * role object, allowing for retrieval or manipulation of the associated user role
     * information. The returned object likely contains details about the user's role or
     * privileges.
     *
     * @returns an instance of the `UserRole` class.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Retrieves an object representing an address and returns it. This method provides
     * access to the current address, allowing for its retrieval or usage elsewhere in
     * the program. The returned address can be used for various purposes such as displaying
     * information or processing geographic data.
     *
     * @returns an `Address` object.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns a list of user activities. It does not perform any computations or
     * interactions with external systems, instead simply retrieving and providing access
     * to an existing collection of activities. This allows for easy retrieval and
     * utilization of the activities data elsewhere.
     *
     * @returns a list of user activities.
     */
    public List<UserActivity> getActivities() {
        return activities;
    }

    /**
     * Represents geographic locations with attributes for street, city, and country. It
     * provides getter methods and an overridden toString method to facilitate representation
     * of address data.
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
         * Returns a string value representing the street name. This method retrieves and
         * provides access to the existing street information, allowing it to be used or
         * processed elsewhere in the program. The returned value is an exact copy of the
         * original street data.
         *
         * @returns a string value stored in the `street` variable.
         */
        public String getStreet() {
            return street;
        }

        /**
         * Retrieves and returns a city value. It is likely part of a larger class representing
         * an address or geographic location, allowing other parts of the program to access
         * the city information. The returned value is a string containing the name of the city.
         *
         * @returns a string representing the city.
         */
        public String getCity() {
            return city;
        }

        /**
         * Returns a string representing a country. It accesses and retrieves the value stored
         * in the `country` variable, allowing other parts of the program to access or display
         * the country information without modifying the underlying data.
         *
         * @returns a string representing the country.
         */
        public String getCountry() {
            return country;
        }

        /**
         * Returns a string representation of an object, comprising three concatenated strings:
         * `street`, `city`, and `country`, separated by commas. This allows for easy conversion
         * into a human-readable format. The resulting string provides information about the
         * location.
         *
         * @returns a string concatenating `street`, `city`, and `country` with commas.
         */
        @Override
        public String toString() {
            return street + ", " + city + ", " + country;
        }
    }
}

/**
 * Generates a user activity report based on the provided users, minimum age, and
 * activity filter. It takes into account inactive users and filters out logout
 * activities, providing a comprehensive report of each user's activities.
 */
class UserActivityReport {

    /**
     * Generates a list of user activity reports based on provided criteria. It filters
     * users by age and activity status, then collects their activities that match a given
     * predicate, formatting each into a report string. The results are aggregated into
     * a list.
     *
     * @param users collection of users to be processed and generates the user activity
     * report for each user.
     *
     * @param minAge minimum age requirement for users to be included in the report, with
     * only users who meet or exceed this age threshold being considered.
     *
     * @param includeInactive option to include users who are not active in the report
     * if set to true or only active users if set to false.
     *
     * @param activityFilter predicate used to filter out certain user activities from
     * being included in the report generation process.
     *
     * Predicate<UserActivity> activityFilter;
     *
     * @returns a list of formatted user reports.
     *
     * Returns a list of strings where each string represents a user activity report.
     * Each report includes activities for users who meet the specified age and activity
     * filter criteria.
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
     * Constructs a formatted string report about a user, including their name, age,
     * status, role, and address. It also lists their activities below, each activity
     * prefixed with a hyphen and a newline character. The resulting string is then
     * returned as the report.
     *
     * @param user user object from which various attributes are retrieved to construct
     * the formatted report.
     *
     * Name: Retrieves the user's name.
     * Age: Displays the user's age.
     * Status: Reflects whether the user is active or inactive based on their status.
     * Role: Shows the user's assigned role.
     * Address: Reveals the user's address.
     *
     * @param activities list of strings that are appended to the StringBuilder object,
     * representing various activities performed by the user.
     *
     * Unwrapped as an interface-based collection of Strings; contains elements.
     *
     * @returns a formatted string representation of user information and activities.
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
     * Aggregates user data and activity logs for multiple users. It creates a list of
     * users with their respective activities, then generates a report based on certain
     * conditions (age above 18, non-logout actions) using the `generateUserActivityReport`
     * method.
     *
     * @param args command-line arguments passed to the Java program when it is executed,
     * but it is not used in this particular code.
     *
     * String[] args - an array of strings representing the command-line arguments.
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
