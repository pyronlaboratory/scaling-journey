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
 * Represents user activities, comprising username, action taken, and date of occurrence.
 * It provides utility for formatting these activities into human-readable strings.
 * This class plays a crucial role in the UserActivityReport, facilitating the
 * generation of reports on user activities.
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
     * Returns a string representing the current user's username. The function does not
     * accept any parameters and simply retrieves the value stored in the `username`
     * variable, returning it as a string result.
     *
     * @returns a string representing the current user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves and returns a string value representing an action. It does not modify
     * any data and simply accesses a previously stored value, indicating a read-only
     * operation. The returned value is assigned to the `action` variable.
     *
     * @returns a string representing the value of the `action` variable.
     */
    public String getAction() {
        return action;
    }

    /**
     * Retrieves and returns a `Date` object. The returned object represents a point in
     * time, capturing both date and time information. This method provides access to the
     * stored date value for use elsewhere in the program.
     *
     * @returns a `Date` object representing a specific date and time.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Concatenates user information with a date string, using a specified format for the
     * date. It returns a formatted string containing the username, action performed, and
     * date. The function utilizes the `SimpleDateFormat` class to achieve the desired
     * formatting of the date.
     *
     * @returns a string describing an event, including date.
     */
    public String format() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return username + " performed " + action + " on " + sdf.format(date);
    }
}

/**
 * Represents a user entity with attributes such as name, age, active status, role,
 * address, and activities. It provides methods to retrieve these attributes and has
 * an inner Address class for storing the user's address details.
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
     * Returns a string value representing the object's name property. This method is
     * likely part of a class that encapsulates an entity with a unique identifier, such
     * as a person or a product, and provides read-only access to its name attribute.
     *
     * @returns a string representing the current value of the `name` variable.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns an integer value representing the age. The function does not take any
     * parameters and simply retrieves the stored age value, which is presumably set
     * elsewhere in the class or object. This method provides a way to access the age information.
     *
     * @returns an integer value representing the object's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns a boolean value indicating whether an object is currently active or not.
     * It simply retrieves and returns the value of the `active` variable. This allows
     * external code to query the state of the object's activity.
     *
     * @returns a boolean value representing the current state of the `active` variable.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Returns a `UserRole` object representing the current user's role. It simply retrieves
     * and provides access to the pre-set `role` variable without performing any computations
     * or operations on it. The returned `UserRole` object can be used by other parts of
     * the program.
     *
     * @returns an instance of `UserRole` class.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Returns an instance of the `Address` class. This method simply retrieves and
     * provides access to the existing `address` object, allowing other parts of the
     * program to utilize its contents. The returned value is a reference to the internal
     * `address` state.
     *
     * @returns an instance of `Address` class.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Retrieves a list of user activities and returns it. The list is stored as an
     * instance variable named `activities`. This function does not perform any operation
     * on the data, but simply provides access to the pre-existing collection of activities.
     *
     * @returns a list of user activities.
     */
    public List<UserActivity> getActivities() {
        return activities;
    }

    /**
     * Represents an address with three properties: street, city, and country. It provides
     * a constructor to initialize these properties and getter methods to retrieve them.
     * The class also overrides the toString method to provide a string representation
     * of the address.
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
         * Retrieves the value of a variable named `street`, returning it as a string. This
         * suggests that the object or class containing this method has an attribute for
         * storing and retrieving street information. The returned value is accessible to
         * whoever calls this method.
         *
         * @returns a string value representing the street address.
         */
        public String getStreet() {
            return street;
        }

        /**
         * Retrieves and returns a string value representing the current city. This method
         * appears to be a getter, providing read-only access to the `city` variable. It does
         * not modify or manipulate the value in any way, simply returning it as is.
         *
         * @returns a string representing the value of the `city` variable.
         */
        public String getCity() {
            return city;
        }

        /**
         * Retrieves and returns the value of a variable named `country`, which is likely an
         * attribute or property of the object it belongs to, providing access to its current
         * value.
         *
         * @returns a string representing the country's name.
         */
        public String getCountry() {
            return country;
        }

        /**
         * Concatenates three strings: `street`, `city`, and `country`, separated by commas,
         * to form a single string representation of an object. This method overrides the
         * default `toString` implementation provided by Java's Object class. The resulting
         * string is returned as the output.
         *
         * @returns a string concatenation of street, city, and country.
         */
        @Override
        public String toString() {
            return street + ", " + city + ", " + country;
        }
    }
}

/**
 * Generates a formatted report of user activities based on a list of users, minimum
 * age, and activity filter. It collects user data and formats it into a string
 * representation of the user's name, age, status, role, address, and a list of their
 * activities.
 */
class UserActivityReport {

    /**
     * Generates a list of user activity reports based on input parameters: a list of
     * users, minimum age, inclusion of inactive users, and an activity filter predicate.
     * It filters and formats activities for each user meeting the conditions, then
     * collects the results into a list.
     *
     * @param users collection of users whose activity reports are to be generated based
     * on certain criteria.
     *
     * Stream of User objects containing age and activity information for each user.
     *
     * @param minAge minimum age requirement for users to be included in the generated report.
     *
     * @param includeInactive option to include inactive users in the report, allowing
     * for filtering based on user activity status.
     *
     * @param activityFilter predicate used to filter out user activities that do not
     * match its condition when collecting and formatting user activity reports.
     *
     * @returns a list of user reports.
     *
     * The output is a list of strings representing user activity reports. Each string
     * in the list corresponds to an individual user's report and contains information
     * about their activities that match the provided filter criteria.
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
     * Constructs a string representation of a user's report by appending their name,
     * age, status, role, and address to a StringBuilder. It then iterates over a list
     * of activities and appends each one to the report with indentation. The result is
     * returned as a string.
     *
     * @param user user whose report is being formatted, providing information such as
     * name, age, status, role, and address to be included in the report.
     *
     * Deconstructs `user`: Yes
     * Properties:
     * - Name
     * - Age
     * - Status (Active or Inactive)
     * - Role
     * - Address
     *
     * @param activities collection of strings that are appended to the report after the
     * user's information, with each activity preceded by " - ".
     *
     * Iterates over each activity, and appends its string representation to the
     * StringBuilder. The activities are separated by lines with leading hyphens.
     *
     * @returns a formatted string describing a user's details and their activities.
     *
     * The returned string is formatted as a report for a user, consisting of several
     * lines. Each line represents a specific attribute of the user such as name, age,
     * status, role, address and activities. The activities are listed with dashes in
     * front of each activity description.
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
     * Aggregates user activities and generates a report for users who are at least 18
     * years old, have logged in, and performed an activity other than logout. The report
     * includes information about each user's name, age, and activity history.
     *
     * @param args command-line arguments passed to the Java program when it is executed.
     *
     * The type of `args` is an array of `String`, and its length is not specified. Each
     * element in the array represents a command-line argument passed to the program.
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
