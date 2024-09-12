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
 * Represents an activity performed by a user with attributes for username, action,
 * and date. It provides methods to access these attributes and format them into a
 * human-readable string. The class is designed to encapsulate and display user
 * activity data in a structured manner.
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
     * Retrieves and returns the value of a private field named "username". The returned
     * value is a string representing the user's name. This allows other parts of the
     * application to access the username without modifying its internal state.
     *
     * @returns a string representing the user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the current value of the `action` variable as a string. This suggests that
     * it provides read-only access to the `action` property, allowing other parts of the
     * code to retrieve its value without modifying it. The return type is a simple string.
     *
     * @returns a string value representing an action.
     */
    public String getAction() {
        return action;
    }

    /**
     * Returns a Date object that represents a specific date value. The returned date is
     * stored within an instance variable named "date". This value is accessible via the
     * getter method implemented here.
     *
     * @returns a `Date` object representing a specific point in time.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns a formatted string containing user information and a date. It combines
     * username, action, and date into a single string using SimpleDateFormat to format
     * the date as year-month-day. The resulting string is then returned.
     *
     * @returns a string representing an event, formatted as username-action-date.
     */
    public String format() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return username + " performed " + action + " on " + sdf.format(date);
    }
}

/**
 * Represents an entity with attributes and behaviors associated with a user in a
 * system. It encapsulates data about the user's identity, status, role, location,
 * and activities.
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
     * Returns a string representing a name. It retrieves and returns the value of an
     * instance variable named `name`. This allows for access to the object's name property
     * from outside its class.
     *
     * @returns a string representing the object's name attribute.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns an integer representing the person's current age. The value is retrieved
     * from a private instance variable named `age`. This method allows external classes
     * to access the age attribute without modifying it directly.
     *
     * @returns an integer representing a person's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns a boolean value indicating whether an entity or object is currently active
     * or not, based on the value of the `active` variable. This method provides a simple
     * and direct way to determine the status of an object's activity. The return type
     * is boolean.
     *
     * @returns a boolean value representing the current state of an object's activity status.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Retrieves and returns a user's assigned role. It exposes the current state of a
     * user's role to external access, allowing for potential modification or evaluation.
     * The function delegates to an existing instance variable named "role".
     *
     * @returns a value of type `UserRole`, representing the user's assigned role.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Returns an instance of the `Address` class, providing access to the address data.
     * This method allows for retrieval of the stored address without modifying it. The
     * returned value is a read-only reference to the internal state of the object.
     *
     * @returns an instance of the `Address` class containing its properties and values.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns a list of user activities. It provides read-only access to the stored
     * activities, allowing external components to retrieve them without modifying the
     * internal state. The returned list is likely a collection of `UserActivity` objects.
     *
     * @returns a list of `UserActivity` objects stored in the `activities` variable.
     */
    public List<UserActivity> getActivities() {
        return activities;
    }

    /**
     * Represents an address with attributes for street, city, and country. It provides
     * a constructor to initialize these attributes and overrides toString() to return a
     * formatted string representation of the address. The class is nested within the
     * User class.
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
         * Returns a string representing the address of the object, specifically the street
         * name. It retrieves and provides access to the `street` field. The returned value
         * is a string containing the street information.
         *
         * @returns a string value representing the street address.
         */
        public String getStreet() {
            return street;
        }

        /**
         * Returns a string representing the city. It is likely used to access and retrieve
         * the value of a private instance variable named `city`. The returned string can be
         * used by external classes or methods that require access to the city data.
         *
         * @returns a string representing the current city attribute.
         */
        public String getCity() {
            return city;
        }

        /**
         * Returns the value of the variable `country`. It is a getter method that provides
         * read access to the `country` property. This allows other parts of the code to
         * retrieve its current value.
         *
         * @returns the value of a variable named `country`.
         * It is a string representing the country.
         * This variable contains the current country.
         */
        public String getCountry() {
            return country;
        }

        /**
         * Returns a string representation of an object, comprising three fields: street,
         * city, and country. The values are concatenated with commas to form a single string.
         * This allows for convenient display or logging of address information as a formatted
         * string.
         *
         * @returns a string containing address details formatted as: street, city, country.
         */
        @Override
        public String toString() {
            return street + ", " + city + ", " + country;
        }
    }
}

/**
 * Generates a report of user activities based on specified criteria such as age and
 * activity type.
 * It uses Java streams to filter and process user data from the User class.
 * The generated report includes details about each user, their status, role, address,
 * and activities.
 */
class UserActivityReport {

    /**
     * Aggregates activity data for users meeting specified criteria, including minimum
     * age and active status, and filters activities using a custom predicate. It formats
     * user reports by combining user information with filtered activities.
     *
     * @param users collection of users from which activity reports are generated based
     * on specified criteria.
     *
     * List of User objects.
     * Mainly consists of age, activities, and activity status.
     * Each user object has an age property with getter method for access.
     *
     * @param minAge minimum age of users that should be included in the report, and
     * filters out users whose age is less than this threshold.
     *
     * @param includeInactive option to include users of minimum age who are not currently
     * active in the report generation process.
     *
     * @param activityFilter criteria for selecting UserActivity instances that will be
     * included in the report of each user's activities.
     *
     * Predicate contains test to be applied over user activity. It can match or reject
     * one user activity. The `test` method is used by Predicate for this purpose.
     *
     * @returns a list of formatted user activity reports.
     *
     * The output is a list of user reports. Each report contains a user's information
     * and their activities. The activities are formatted as strings according to the
     * UserActivity::format method.
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
     * Generates a formatted string report containing user information and their associated
     * activities. It retrieves data from the provided `user` object and appends it to a
     * StringBuilder, followed by iterating over the `activities` list to include each
     * activity on a new line. The result is then returned as a string.
     *
     * @param user object being reported on, whose details are to be extracted and formatted
     * into a string representation.
     *
     * Depict user as an object with name, age, status, role, and address attributes. The
     * status attribute is either active or inactive based on a conditional expression
     * evaluating to true or false respectively.
     *
     * @param activities list of user's activities to be included in the formatted report
     * and is processed with `forEach` to append each activity to the output string.
     *
     * The object `activities` is an unmodifiable list of strings. Its main property is
     * that each element in the list represents an activity associated with a user.
     *
     * @returns a formatted string containing user information and their activities.
     *
     * The string contains multiple lines of user information.
     * Each line contains a key-value pair where keys are labels and values are user attributes.
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
     * Generates a user activity report by filtering users based on age and role, then
     * selects activities that are not logout events. It adds the resulting activities
     * to a list, which is then printed to the console as a report. The filter criteria
     * include age (>= 18) and role (admin).
     *
     * @param args command-line arguments passed to the program when it is executed, but
     * it remains unutilized and commented out within this function.
     *
     * It has type `String[]`, which represents an array of strings. Each element in the
     * array is a string representing command-line arguments passed to the program when
     * it was invoked. The array is empty because no arguments are provided when running
     * this Java program.
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
