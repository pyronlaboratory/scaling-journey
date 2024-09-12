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
 * Represents an activity performed by a user with attributes such as username, action
 * type, and date of occurrence. It provides functionality to format these activities
 * into human-readable strings for reporting purposes. This class is likely used
 * within a larger application to track and analyze user interactions.
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
     * Returns a string representing a user's username. It is a simple accessor method
     * that allows other parts of the program to retrieve the value stored in the `username`
     * field. The return value is based on an instance variable.
     *
     * @returns a string representing the user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns a string value representing an action. It retrieves and exposes the
     * encapsulated `action` variable. This allows external access to the stored action
     * for further processing or display.
     *
     * @returns a string representing the current action state of the object.
     */
    public String getAction() {
        return action;
    }

    /**
     * Returns a Date object representing the current date or a previously set date. The
     * function is read-only, meaning it does not modify any data but rather provides
     * access to existing information. It likely serves as a getter for date-related data
     * within an application.
     *
     * @returns a Date object representing the current stored date value.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns a formatted string representation of a user's action, combining username,
     * action type, and date in the format "yyyy-MM-dd". It uses SimpleDateFormat to
     * convert the date object into a human-readable string format. The result is a
     * concatenated string describing the user's activity.
     *
     * @returns a string in the format "username performed action on date".
     */
    public String format() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return username + " performed " + action + " on " + sdf.format(date);
    }
}

/**
 * Represents an entity with attributes such as name, age, and role. It contains
 * functionality to access and manage user data. The class also includes a nested
 * Address class for representing user addresses.
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
     * Retrieves and returns a string value representing the object's name attribute. It
     * directly accesses and returns the private instance variable `name`, likely a field
     * declared earlier in the class.
     *
     * @returns a string representing the object's `name`.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current value of the `age` field. This field is likely a numeric
     * representation of an entity's age, and its value is presumably maintained elsewhere
     * in the codebase. The function provides direct access to this age data for external
     * usage or further processing.
     *
     * @returns an integer representing the age of the object.
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns a boolean value indicating whether an object is in an active state. The
     * method simply exposes the value of the `active` field, allowing external access
     * to its status without modifying it. It provides read-only access to the object's
     * active state.
     *
     * @returns a boolean value indicating the state of the `active` variable.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Returns an instance of `UserRole`, which is presumably a data class or object
     * representing a user's role within an application. The returned value is likely
     * obtained from an internal state, such as a member variable or attribute named
     * `role`. Its purpose is to expose the current user role.
     *
     * @returns an instance of a `UserRole` class.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Returns an instance of the `Address` class, representing a physical location or
     * postal address. It is likely used to provide access to the user's address information
     * outside of the object that owns it. The address itself is stored within the instance
     * variable `address`.
     *
     * @returns an instance of the `Address` class.
     * It contains attributes representing a physical location, such as street and postal
     * code.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns a list of user activities stored in the variable `activities`. The return
     * value is a reference to an existing list, implying it does not create or modify
     * any data. It allows external code to access the stored activity information.
     *
     * @returns a list of UserActivity objects. The list contains all user activities
     * stored within it. It is retrieved directly from the class field.
     */
    public List<UserActivity> getActivities() {
        return activities;
    }

    /**
     * Represents a physical address with attributes for street, city, and country. It
     * has constructors and getters to access these properties. The class also overrides
     * the toString method for string representation.
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
         * Retrieves and returns a string representing the street address from its associated
         * data or object. The function has no parameters and does not modify the data. It
         * is used to access the street field of an object, allowing for its value to be
         * utilized elsewhere in the code.
         *
         * @returns a string representing the value of the `street` field.
         */
        public String getStreet() {
            return street;
        }

        /**
         * Returns the value of a string variable named `city`. It is likely used for data
         * retrieval, allowing other parts of the program to access and utilize the stored
         * city information. The function does not perform any validation or modification on
         * the returned value.
         *
         * @returns a string representing the city attribute of an object.
         * The value is directly retrieved from a field named `city`.
         * The result is a copy of the original `city` data.
         */
        public String getCity() {
            return city;
        }

        /**
         * Returns a string representing the country. It is likely used for data retrieval
         * or display purposes, allowing access to a stored country value. The country value
         * is presumably set elsewhere in the code.
         *
         * @returns the value of the instance variable `country`.
         */
        public String getCountry() {
            return country;
        }

        /**
         * Returns a string representation of an object, combining street, city, and country
         * values with commas as delimiters. This allows for easy conversion to a human-readable
         * format. The resulting string is returned when the object is used in a context
         * requiring a string representation.
         *
         * @returns a string combining address components: street, city, and country.
         */
        @Override
        public String toString() {
            return street + ", " + city + ", " + country;
        }
    }
}

/**
 * Generates a comprehensive report of user activities based on specified criteria.
 * It takes into account various parameters such as minimum age and includes inactive
 * users if required.
 * The report is generated in the form of formatted strings representing individual
 * user activity summaries.
 */
class UserActivityReport {

    /**
     * Generates a list of reports for users meeting specified criteria.
     * Reports include user information and their active or inactive activities that pass
     * a given filter.
     * The report format is determined by the `formatUserReport` method.
     *
     * @param users list of users from which to generate the user activity report by
     * applying various filters and transformations to their associated activities.
     *
     * Determine its elements.
     * Each element is an instance of `User`.
     *
     * The User class has several main properties:
     * - Age
     * - IsActive (boolean)
     * - GetActivities (method returning a list of activities)
     *
     * @param minAge minimum age requirement for users to be included in the report,
     * filtering out users whose age is less than the specified value.
     *
     * @param includeInactive option to include users who are not active in the report
     * generation process by filtering out inactive users if set to false.
     *
     * @param activityFilter condition that must be met for each user activity to be
     * included in the report.
     *
     * Apply to each user activity.
     *
     * @returns a list of user activity reports.
     *
     * The output is a List, which contains multiple report items. Each report item is a
     * String representation of a user's activity report.
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
     * Formats a user report by concatenating user information and activity details into
     * a string. It takes a `User` object and a list of `String` activities, then returns
     * a formatted string representation of the user's data. The output includes the
     * user's name, age, status, role, address, and activities.
     *
     * @param user user data that is used to construct the report by extracting and
     * formatting its attributes.
     *
     * Extract user properties into separate variables for clarity.
     * Name and address appear to be attributes of the User object.
     *
     * @param activities list of strings that are appended to the report under the
     * "Activities" section, formatted with dashes and newlines for readability.
     *
     * Destructure `activities` into individual activities.
     * It contains a list of strings representing user activities.
     * Each activity is a single string.
     *
     * @returns a formatted string containing user information and associated activities.
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
     * Initializes a list of User objects with their activities and then calls the
     * `generateUserActivityReport` method to generate a report based on user age, role,
     * and activity type. The report is then printed to the console.
     *
     * @param args command-line arguments passed to the program and is typically used for
     * processing or validation, but it is not used here.
     *
     * Declare an array of strings called args. The array has length 0 because no arguments
     * were passed to the main method.
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
