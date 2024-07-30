from enum import Enum
from typing import List, Dict, Tuple, Callable, Optional
from datetime import datetime

class UserRole(Enum):
    """
    Defines an enumeration type with three values: `ADMIN`, `USER`, and `GUEST`.
    Each value is associated with a string label, allowing for descriptive references
    to user roles within the application. This implementation enables type-safe
    and readable handling of user role data.

    Attributes:
        ADMIN (UserRole): Assigned the value "Admin". It is an enumeration member
            that represents the role of an administrator.
        USER (UserRole): 4 characters long, representing a user role with a string
            value "User".
        GUEST (str): 5 characters long, representing a user role with name 'Guest'
            and value 'Guest'. It is one of three roles defined by this Enum class:
            Admin, User, Guest.

    """
    ADMIN = "Admin"
    USER = "User"
    GUEST = "Guest"

UserActivity = Tuple[str, str, datetime]

class User:
    # inline comment
    """
    Initializes a user object with attributes such as name, age, activity status,
    role, and address. It also includes a list of activities. This class provides
    a structure for representing users in an application.

    Attributes:
        name (str): Initialized with a given name when creating an instance of the
            class.
        age (int): Initialized with a given integer value during object creation.
            It represents the age of a user.
        active (bool): Used to track whether a user's account is currently active
            or not.
        role (UserRole): Assigned during initialization. It represents the user's
            role, which could be a predefined set of roles, such as administrator,
            moderator, or regular user.
        address (Dict[str,str]): Expected to contain a dictionary where the keys
            are strings and the values are also strings, representing the user's
            address information.
        activities (List[UserActivity]): Initialized in the constructor with the
            provided argument. It represents a collection of user activities, each
            activity being an instance of the UserActivity class.

    """
    def __init__(self, name: str, age: int, active: bool, role: UserRole, address: Dict[str, str], activities: List[UserActivity]):
        # inline comment
        """
        Initializes objects with provided parameters: name, age, active status,
        role, address as a dictionary, and activities as a list. It sets instance
        variables to these values, allowing for user object creation with specific
        attributes.

        Args:
            name (str): Assigned to an instance variable named `self.name`. It
                represents the name of the user.
            age (int): Required to initialize an object of the class. It represents
                a user's age, which must be an integer value.
            active (bool): Used to represent whether the user is active or not.
            role (UserRole): Assigned to an instance variable with the same name.
                The specific details of this role are not specified, but it appears
                to be an enumeration or an abstract class that represents different
                user roles.
            address (Dict[str, str]): Expected to be a dictionary where keys are
                strings and values are also strings. It represents the user's address.
            activities (List[UserActivity]): Expected to hold a list of user
                activity objects.

        """
        self.name = name
        self.age = age
        self.active = active
        self.role = role
        self.address = address
        self.activities = activities

def format_activity(activity: UserActivity) -> str:
    # inline comment
    """
    Takes a tuple containing user activity data (username, action, date) and returns
    a formatted string representing the activity. The date is converted to the
    format 'YYYY-MM-DD' before being included in the output string.

    Args:
        activity (UserActivity): Unpacked into three variables: `username`, `action`,
            and `date`. This indicates that `activity` is an object with at least
            three attributes or properties.

    Returns:
        str: A formatted string that describes an activity performed by a user.
        The string includes the username, action, and date of the activity, in the
        format "username performed action on YYYY-MM-DD".

    """
    username, action, date = activity
    return f"{username} performed {action} on {date.strftime('%Y-%m-%d')}"

def generate_user_activity_report(
    users: List[User],
    min_age: int = 18,
    options: Optional[Dict[str, Optional[Callable[[UserActivity], bool]]]] = None
) -> List[Dict[str, str]]:
    # inline comment
    """
    Generates a list of dictionaries, each representing a user's activity report.
    The report includes user details, such as name, age, and role, along with their
    activities. It filters users by minimum age and optionally includes inactive
    users.

    Args:
        users (List[User]): Required to generate the report. It takes in a list
            of User objects, which are then processed and formatted into a report.
        min_age (int): 18 by default. It filters users based on their age, including
            only those who are at least as old as this minimum age specified.
        options (Optional[Dict[str, Optional[Callable[[UserActivity], bool]]]]):
            Optional by default. It filters users based on the presence of inactive
            users and activities that match a specified filter.

    Returns:
        List[Dict[str, str]]: A list of dictionaries containing information about
        users, including their name, age, activity status, role, address and
        activities they have done.

    """
    if options is None:
        options = {}
    include_inactive = options.get('include_inactive', False)
    activity_filter = options.get('activity_filter', lambda x: True)

    filtered_users = [
        {
            "name": user.name,
            "age": user.age,
            "active": user.active,
            "role": user.role.value,
            "address": f"{user.address['street']}, {user.address['city']}, {user.address['country']}",
            "activities": [
                format_activity(activity) for activity in user.activities if activity_filter(activity)
            ]
        }
        for user in users if user.age >= min_age and (include_inactive or user.active)
    ]

    return filtered_users

# Example usage
users = [
    User(
        name="Alice",
        age=25,
        active=True,
        role=UserRole.ADMIN,
        address={"street": "123 Main St", "city": "Metropolis", "country": "USA"},
        activities=[("Alice", "login", datetime(2023, 1, 1)), ("Alice", "purchase", datetime(2023, 2, 15))]
    ),
    User(
        name="Bob",
        age=17,
        active=False,
        role=UserRole.USER,
        address={"street": "456 Elm St", "city": "Gotham", "country": "USA"},
        activities=[("Bob", "logout", datetime(2023, 3, 22))]
    ),
    User(
        name="Charlie",
        age=30,
        active=True,
        role=UserRole.GUEST,
        address={"street": "789 Oak St", "city": "Star City", "country": "USA"},
        activities=[("Charlie", "login", datetime(2023, 4, 10))]
    ),
]

report = generate_user_activity_report(users, 18, {"include_inactive": True, "activity_filter": lambda activity: activity[1] != "logout"})
for entry in report:
    print(entry)
