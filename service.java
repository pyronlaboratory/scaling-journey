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
 * Represents an activity performed by a user in a system. It encapsulates the details
 * of a specific action taken by a user, including the user's name, the action itself,
 * and the date it was performed. The class provides a way to format this information
 * into a human-readable string.
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
     * Retrieves the value of a variable named `username` and returns it as a string.
     * This suggests that the variable `username` stores the username of an entity, likely
     * for identification or authentication purposes. The returned string can be used to
     * access or display the username accordingly.
     *
     * @returns a string representing the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves and returns a string value representing an action. The returned value
     * is stored in the `action` variable, which may have been previously set by another
     * part of the program. This allows other parts of the code to access and utilize the
     * current action value.
     *
     * @returns a string value stored in the `action` variable.
     */
    public String getAction() {
        return action;
    }

    /**
     * Retrieves a `Date` object and returns it. The returned object represents a specific
     * point in time, encapsulating the year, month, day, hour, minute, second, and millisecond.
     *
     * @returns a `Date` object containing the current system date and time.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Concatenates a string representation of date, an action, and a username to produce
     * a formatted string. It uses a SimpleDateFormat object to format the date in the
     * specified format "yyyy-MM-dd". The resulting string describes an event performed
     * by a user on a specific date.
     *
     * @returns a formatted string containing the username, action, and date.
     */
    public String format() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return username + " performed " + action + " on " + sdf.format(date);
    }
}

/**
 * Represents an individual with details such as name, age, activity status, role,
 * address, and a list of activities. It provides getters for these properties and
 * has a nested Address class to represent the user's location.
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
     * Retrieves and returns a string value representing the name. The returned value is
     * stored in a variable named `name`. This function provides an interface to access
     * the name attribute.
     *
     * @returns a string representation of an instance variable named "name".
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves and returns an integer value representing a person's age. The age is
     * stored as a variable within the class, which allows for its retrieval through this
     * method. This function does not modify the age but rather provides access to it.
     *
     * @returns an integer representing the current age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns a boolean value indicating the state of an object or variable called
     * "active". The exact purpose and scope of "active" are not specified, but it is
     * likely a flag or indicator used elsewhere in the program to control execution flow.
     *
     * @returns a boolean value indicating whether the object's "active" state is true
     * or false.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Returns an instance of the `UserRole` class, representing a user's role. This
     * method retrieves and provides access to the existing user role without modifying
     * it. The returned object is read-only, reflecting the current state of the user's
     * role.
     *
     * @returns an instance of `UserRole`.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Retrieves and returns an object of type `Address`. The returned object is assigned
     * to a variable named `address`, which is presumably declared elsewhere in the code.
     * This function provides read-only access to the stored address information.
     *
     * @returns an object of type `Address`.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Retrieves a list of user activities and returns it. The returned list is not
     * modified by the function, as it simply provides access to an existing collection
     * of activities. This read-only approach allows for querying or rendering the
     * activities without altering their state.
     *
     * @returns a list of `UserActivity` objects.
     */
    public List<UserActivity> getActivities() {
        return activities;
    }

    /**
     * Represents an address with properties for street, city, and country. It provides
     * a way to construct an address from these components and offers a string representation
     * of the address. The class is designed to encapsulate an address value object.
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
         * Retrieves and returns a string value representing the street address. This method
         * is likely part of a larger class for encapsulating or manipulating geographic
         * information, such as an address book entry. The returned value is presumably stored
         * in an instance variable named `street`.
         *
         * @returns a string representing the value of the `street` variable.
         */
        public String getStreet() {
            return street;
        }

        /**
         * Retrieves and returns a string value representing a city. This method appears to
         * provide read-only access to the stored city information, allowing users to retrieve
         * the current city value without modifying it. The returned value is a copy of the
         * internal `city` variable.
         *
         * @returns a string value representing the city.
         */
        public String getCity() {
            return city;
        }

        /**
         * Retrieves and returns a string value representing a country. This function provides
         * read-only access to the internal state of an object, allowing users to obtain the
         * current country without modifying it. The returned value is a simple string
         * representation of the country.
         *
         * @returns a string representing the country's name or value.
         */
        public String getCountry() {
            return country;
        }

        /**
         * Concatenates three string variables: `street`, `city`, and `country`, separated
         * by commas, to form a single string representation of an object's address. This
         * method overrides the default `toString` behavior, providing a custom string output
         * for objects of this class.
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
 * Generates a user activity report based on provided input parameters, filtering
 * users by age and including inactive users if required, then formatting each user's
 * activities in a readable format. The generated report includes essential information
 * about the user and their activities.
 */
class UserActivityReport {

    /**
     * Generates a report on user activity by filtering users based on age and activity
     * status, then collecting their activities that match a specified filter. The report
     * includes formatted activities for each user.
     *
     * @param users list of user objects to be processed and filtered according to specific
     * criteria.
     *
     * Streamed for filtering and mapping operations. Contains a collection of user
     * objects. Each user object has an age property and an isActive property.
     *
     * @param minAge minimum age of users to include in the report, and it filters out
     * users with an age less than this value.
     *
     * @param includeInactive boolean flag that determines whether to include inactive
     * users or only active users in the report generation process.
     *
     * @param activityFilter predicate used to filter user activities, allowing only those
     * activities that satisfy the specified condition to be included in the report.
     *
     * Filtered by predicate, accepting user activities that match specific conditions.
     * The exact condition is unknown due to deconstruction.
     *
     * @returns a list of user activity reports.
     *
     * The output is a list of strings. Each string represents a user's report activity.
     * The list contains only users whose age is greater than or equal to the minimum age
     * and either include inactive users or have active status.
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
     * Concatenates user information and a list of activities into a formatted string.
     * The string includes the user's name, age, status, role, address, and a list of
     * activities separated by dashes.
     *
     * @param user information to be formatted and appended to a string, including name,
     * age, status, role, address, and activities.
     *
     * Extracts and includes user's name, age, status, role, and address in the output report.
     *
     * @param activities list of strings that are appended to the report as "Activities"
     * with each activity preceded by a hyphen and separated by new lines.
     *
     * Iterates over a list of strings; each string represents an activity.
     *
     * @returns a formatted string containing user information and their activities.
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
     * Generates a user activity report by filtering activities of users above a specified
     * age (18) and excluding logout actions, then prints the filtered activities as strings.
     *
     * @param args command-line arguments passed to the program when it is executed.
     *
     * It is an array of type `String`, containing multiple strings representing command-line
     * arguments passed to the program.
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
