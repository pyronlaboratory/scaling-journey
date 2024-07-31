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
 * Represents a user's activity with date and time information. It provides getter
 * methods for retrieving the username, action performed, and date of the activity,
 * as well as a format method for generating a human-readable string representation.
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
     * Retrieves and returns a stored username as a string. This method provides access
     * to the existing username value, allowing it to be utilized or displayed elsewhere
     * in the program. The returned value is a snapshot of the current username.
     *
     * @returns a string value representing the current username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns a string value representing an unknown entity's "action". It retrieves and
     * passes back the stored `action` state. This functionality serves to provide access
     * to the current state of the action attribute.
     *
     * @returns a string value representing an unknown entity's action.
     */
    public String getAction() {
        return action;
    }

    /**
     * Retrieves a date object and returns it. This method does not accept any parameters
     * and simply provides access to an existing date instance stored as `date`. The
     * returned date is presumably used elsewhere in the application or code.
     *
     * @returns a `Date`.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Combines user's username, a description of an action, and a date to create a
     * formatted string. The date is formatted according to the `yyyy-MM-dd` pattern using
     * the `SimpleDateFormat`. The resulting string represents a log entry with the given
     * details.
     *
     * @returns a string describing an action, including date and username.
     */
    public String format() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return username + " performed " + action + " on " + sdf.format(date);
    }
}

/**
 * Represents individual users with attributes such as name, age, activity status,
 * role, address, and a list of activities performed by that user. It encapsulates
 * these properties and provides getter methods to access them.
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
     * Retrieves a string value representing a name and returns it. This operation is
     * performed without modifying the original data. The returned value is a copy of the
     * internal state of the object.
     *
     * @returns a string representing the object's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns an integer value representing the age of an entity or object. The age is
     * stored in a variable named `age`. This read-only function allows external access
     * to the internal state of the entity, providing its current age for further processing
     * or display.
     *
     * @returns an integer representing the age of a person.
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns a boolean indicating whether an object is currently active. This value is
     * simply retrieved from the `active` field, implying that the activation status is
     * stored elsewhere and not calculated within the function itself. The result determines
     * whether the object's activity can be considered ongoing.
     *
     * @returns a boolean value indicating the current state of "active".
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Retrieves and returns a `UserRole` object representing the current role. This
     * function is likely part of an authentication or authorization system, allowing for
     * the retrieval of user roles. The returned role can be used to determine access
     * levels or permissions within the application.
     *
     * @returns an instance of the `UserRole` class.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Retrieves and returns the `address` object. This read-only operation provides
     * access to the stored address information without modifying it. The obtained address
     * data can then be used for further processing or display.
     *
     * @returns an instance of the `Address` class.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns a list of user activities, allowing access to the stored data. This method
     * provides read-only access to the stored activities, enabling retrieval without
     * modification or creation of new activities. The returned list represents the
     * collection of user activities.
     *
     * @returns a list of `UserActivity` objects.
     */
    public List<UserActivity> getActivities() {
        return activities;
    }

    /**
     * Is used to represent an address with street, city, and country details. It provides
     * methods for getting these details and overrides the toString() method to provide
     * a human-readable representation of the address.
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
         * Returns a string value representing the street address. This method retrieves and
         * provides access to the stored `street` variable. The returned value is simply a
         * read-only copy of the internal state, allowing for inspection but not modification.
         *
         * @returns a string representing the street.
         */
        public String getStreet() {
            return street;
        }

        /**
         * Returns a string value representing the city. It retrieves and provides access to
         * the previously stored or assigned city information. This simple getter method
         * allows other parts of the program to retrieve the city data without modifying it.
         *
         * @returns a string representing the city.
         */
        public String getCity() {
            return city;
        }

        /**
         * Retrieves and returns the value of the `country` variable. This allows external
         * access to the stored country information, enabling its use or manipulation elsewhere
         * in the program. The returned value is a string representing the country name.
         *
         * @returns a string value representing a country.
         */
        public String getCountry() {
            return country;
        }

        /**
         * Returns a string representation of an object, combining three strings: `street`,
         * `city`, and `country`. This concatenated string is formatted with commas separating
         * each part. The resulting string provides a human-readable summary of the address.
         *
         * @returns a string combining street, city, and country information.
         */
        @Override
        public String toString() {
            return street + ", " + city + ", " + country;
        }
    }
}

/**
 * Is designed to generate activity reports for users based on their age and activity
 * filters. It uses Java Stream API to process user data and returns a list of formatted
 * report strings. The main method demonstrates the usage of this class with sample
 * user data.
 */
class UserActivityReport {

    /**
     * Generates a report containing user activity data for users meeting specific criteria:
     * age greater than or equal to `minAge`, and optionally including inactive users if
     * `includeInactive` is `true`. Activities are filtered using the provided `activityFilter`
     * predicate.
     *
     * @param users collection of users from which to generate the activity report,
     * filtering and processing their associated user activities based on specified criteria.
     *
     * Streamed, filtered by age and activity status; contains user objects having an
     * "age" attribute and "getActivities()" method that returns a list of activities.
     *
     * @param minAge minimum age required for a user to be included in the report, with
     * only users whose age is greater than or equal to this value being considered.
     *
     * @param includeInactive condition for including inactive users in the report
     * generation process.
     *
     * @param activityFilter predicate that filters user activities based on specific
     * criteria before formatting and collecting them into a list.
     *
     * Predicate - filters UserActivity instances based on an unspecified condition. Its
     * main property is its predicate logic that determines whether or not a specific
     * activity meets the desired criteria.
     *
     * @returns a list of formatted user reports.
     *
     * A list of strings is returned, where each string represents an activity report for
     * a user. The report includes information about the user and their active activities
     * that meet the specified conditions.
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
     * Constructs a string report for a user, including their name, age, status, role,
     * address, and a list of activities they have performed. The report is formatted
     * with newline characters to represent each section and activity.
     *
     * @param user user's information, which is then used to populate various fields and
     * attributes within the formatted report string.
     *
     * - The user has a `name`, which is extracted and appended to the string builder.
     * - The user's `age` is also extracted and appended.
     * - The user's status depends on whether it is `active` or not, with corresponding
     * strings "Active" or "Inactive".
     * - The user has a `role` that is appended to the string builder.
     * - The user's `address` is extracted and appended.
     *
     * @param activities list of strings that are appended to the report as a series of
     * activities performed by the user, each preceded by an em dash and a line break.
     *
     * Foreachable, containing Strings, unordered.
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
     * Generates a user activity report by creating and populating a list of users with
     * their activities. It then filters out logout events from non-minors (older than
     * 18) and prints the resulting report.
     *
     * @param args command-line arguments passed to the main class when it is run, but
     * it is not used in this specific code snippet.
     *
     * Parameterized array of strings representing command-line arguments. It contains
     * no specific values or information for this function's execution.
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
