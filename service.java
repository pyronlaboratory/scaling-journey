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
 * Represents an activity performed by a user, including the username, action taken,
 * and date of the activity. It provides a format method to convert the activity
 * details into a string representation. The class is used to track and report on
 * user activities.
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
     * Retrieves and returns a stored value representing a user's username. The returned
     * value is of type `String`. This function provides read-only access to the stored
     * username.
     *
     * @returns a string representing a user's name.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves and returns a string value representing an action. This method simply
     * accesses a private variable `action` and returns its current state without modifying
     * it. The returned string provides information about a specific action.
     *
     * @returns a string value stored in the `action` variable.
     */
    public String getAction() {
        return action;
    }

    /**
     * Retrieves a `Date` object and returns it. The returned `Date` object represents a
     * point in time, which can be used for various purposes such as storing or processing
     * dates in a program.
     *
     * @returns a `Date` object representing a point in time.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Concatenates strings to form a message describing an action performed by a user.
     * The message includes the username, action, and date in the format "yyyy-MM-dd".
     * The function returns this formatted string.
     *
     * @returns a string combining username, action, and date in a specific format.
     */
    public String format() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return username + " performed " + action + " on " + sdf.format(date);
    }
}

/**
 * Encapsulates user-related data and functionality. It represents an individual with
 * attributes such as name, age, active status, role, address, and activities. The
 * class allows for the creation of user objects and provides methods to access their
 * properties.
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
     * Returns the value of the `name` variable as a string. It retrieves and provides
     * access to the stored name. This method simply wraps up the getter functionality
     * for the `name` attribute.
     *
     * @returns a string representation of an instance variable named "name".
     */
    public String getName() {
        return name;
    }

    /**
     * Returns an integer value representing the age of an object. The returned value is
     * stored in a variable named `age`. This function does not modify the age, it only
     * retrieves and returns its current state.
     *
     * @returns an integer value representing the age of an object.
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns a boolean value indicating whether an object is active or not. It simply
     * retrieves and returns the value of the `active` variable, without performing any
     * calculations or modifying external state. The function does not take any parameters
     * and always returns a boolean result.
     *
     * @returns a boolean value representing the current state of the "active" variable.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Retrieves and returns an instance of `UserRole`. This indicates that it provides
     * access to the current user's role, possibly for purposes such as authentication
     * or authorization. The returned value is read-only, suggesting that it may be used
     * for querying or display purposes only.
     *
     * @returns a `UserRole` object representing the current user's role.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Retrieves an object of type `Address`. It returns the value stored in the `address`
     * variable, providing access to the contained address information.
     *
     * @returns an instance of class `Address`.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Retrieves and returns a list of user activities. It appears to be a simple getter
     * method that provides read-only access to an existing list of `UserActivity` objects,
     * which are stored in the `activities` variable.
     *
     * @returns a list of `UserActivity` objects named `activities`.
     */
    public List<UserActivity> getActivities() {
        return activities;
    }

    /**
     * Represents a geographic address with attributes for street, city, and country. It
     * provides methods to get these values and overrides the toString method to return
     * a string representation of the address in the format "street, city, country".
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
         * Retrieves the value of a string variable `street`. The returned value is a
         * representation of the address's street name or equivalent information. This method
         * does not modify the original data, it simply provides access to the existing information.
         *
         * @returns a string representing the value of the `street` variable.
         */
        public String getStreet() {
            return street;
        }

        /**
         * Retrieves the value of a `city` variable and returns it as a string. This method
         * allows access to the stored city information, making it available for use by other
         * parts of the program or presentation layer. The returned value is a simple string
         * representation of the city name.
         *
         * @returns a string representing the value of the `city` variable.
         */
        public String getCity() {
            return city;
        }

        /**
         * Returns a string value representing a country. It retrieves and returns an existing
         * value stored in the variable `country`. This method allows access to the country
         * information without modifying it.
         *
         * @returns a string value representing the country.
         */
        public String getCountry() {
            return country;
        }

        /**
         * Returns a string representation of an object by concatenating the values of `street`,
         * `city`, and `country`. The resulting string is formatted with commas separating
         * each value, providing a human-readable output. This method overrides the default
         * `toString` behavior in Java.
         *
         * @returns a string containing street, city, and country separated by commas.
         */
        @Override
        public String toString() {
            return street + ", " + city + ", " + country;
        }
    }
}

/**
 * Generates and formats user activity reports from a list of users based on filters
 * and criteria. It uses Java Stream API to process data and generate a report string
 * for each user with their activities, status, role, and address. The main method
 * demonstrates how to use this class to generate a report.
 */
class UserActivityReport {

    /**
     * Generates a list of reports for users who meet certain criteria: their age is
     * greater than or equal to the specified minimum age and they are either active or
     * inactive (if includeInactive is true). It includes their activities that match a
     * given predicate.
     *
     * @param users collection of users from which active user reports are generated based
     * on specified conditions and filtering criteria.
     *
     * Stream of users. Each user has age and activity status. Users may have multiple activities.
     *
     * @param minAge minimum age of users to be included in the report, and users with
     * ages less than `minAge` are filtered out from the result.
     *
     * @param includeInactive option to include users who are inactive, along with active
     * ones, in the generated report when their age is greater than or equal to the
     * specified minimum age.
     *
     * @param activityFilter predicate used to filter out UserActivity instances from the
     * list of activities for each user based on specific criteria.
     *
     * Predicate<UserActivity> activityFilter;
     * The main property of this predicate is that it filters the user activities based
     * on some condition defined in its implementation.
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
     * Concatenates user information and activity list into a string, formatted as a
     * report. It takes a `User` object and a list of `activities`, extracts relevant
     * details, and returns the resulting string.
     *
     * @param user user object whose information, including name, age, status, role, and
     * address, is extracted and formatted into a string report.
     *
     * Deconstructs user: Name, Age, Status, Role, and Address.
     *
     * @param activities list of user activities that are appended to the formatted report
     * as a series of bullet points, one per activity.
     *
     * Enumerates a collection of strings.
     *
     * @returns a formatted string containing user information and activity list.
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
     * Generates a user activity report by filtering activities of users older than 18
     * with administrator role and excluding logout actions, then printing the filtered
     * list of strings to the console.
     *
     * @param args command-line arguments passed to the Java program when it is executed,
     * which are not utilized in this main method.
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
