from enum import Enum
from typing import List, Dict, Tuple, Callable, Optional
from datetime import datetime

class UserRole(Enum):
    """
    Defines an enumeration with three user role types: `ADMIN`, `USER`, and `GUEST`.
    Each type is represented by a descriptive string. This class provides a way
    to categorize users based on their roles, allowing for easy referencing and
    comparison of roles in the code.

    Attributes:
        ADMIN (UserRole): Assigned the string value "Admin". It represents a role
            with administrative privileges in the system.
        USER (UserRole): 4 characters long, representing a role with privileges.
            It has a string value "User".
        GUEST (str): 6 characters long, having the value "Guest". It represents a
            role that grants limited access to system resources and functionality.

    """
    ADMIN = "Admin"
    USER = "User"
    GUEST = "Guest"

UserActivity = Tuple[str, str, datetime]

class User:
    """
    Initializes a user with specified attributes: name, age, activity status, role,
    and associated data (address) and activities. The attributes are set as instance
    variables for later use. This class serves as a container to hold the relevant
    information about a user in an application.

    Attributes:
        name (str): Initialized with a value provided as input to the class
            constructor. It represents the name of the user.
        age (int): Initialized during object creation with a value provided by the
            user. It represents the age of the user.
        active (bool): Initialized with a boolean value. It represents whether the
            user's account is active or not.
        role (UserRole): Initialized with a value provided by the user. It represents
            the role or position held by the user within a system, organization,
            or community.
        address (Dict[str,str]): Initialized with a dictionary where keys are
            strings and values are also strings, representing user's address.
        activities (List[UserActivity]): Initialized with a list of `UserActivity`
            objects. This allows for a user to have multiple activities associated
            with their account, such as hobbies or interests.

    """
    def __init__(self, name: str, age: int, active: bool, role: UserRole, address: Dict[str, str], activities: List[UserActivity]):
        """
        Initializes an object with various attributes, including name, age, activity
        status, role, address and activities. These attributes are assigned values
        provided as parameters to the constructor.

        Args:
            name (str): Used to set the name attribute of the class instance.
            age (int): Assigned to an instance variable named `age`. It represents
                the age of the user.
            active (bool): Used to set or get whether the user is active. It
                defaults to None but should be provided with either True or False
                when creating an instance of this class.
            role (UserRole): Assigned to an instance variable named `self.role`.
            address (Dict[str, str]): Initialized with an empty dictionary. It
                represents a user's address as a collection of key-value pairs
                where keys are strings (e.g., street, city) and values are also strings.
            activities (List[UserActivity]): Expected to be initialized with a
                list of objects, each representing an activity of a user.

        """
        self.name = name
        self.age = age
        self.active = active
        self.role = role
        self.address = address
        self.activities = activities

def format_activity(activity: UserActivity) -> str:
    """
    Converts a tuple containing user information into a formatted string, displaying
    the username, action taken, and date of activity in 'YYYY-MM-DD' format.

    Args:
        activity (UserActivity): Expected to be an iterable, likely a tuple or
            list, containing at least three elements: username, action, and date.

    Returns:
        str: A formatted string representing an activity by a user, comprising the
        username, action taken, and date on which it was performed.

    """
    username, action, date = activity
    return f"{username} performed {action} on {date.strftime('%Y-%m-%d')}"

def generate_user_activity_report(
    users: List[User],
    min_age: int = 18,
    options: Optional[Dict[str, Optional[Callable[[UserActivity], bool]]]] = None
) -> List[Dict[str, str]]:
    """
    Generates a list of dictionaries representing user activity reports from a
    given list of users, filtered by age and active/inactive status, with optional
    inclusion of inactive users and custom activity filtering.

    Args:
        users (List[User]): Mandatory. It represents a collection of User objects.
        min_age (int): 18 by default. It filters users based on their age, only
            including those who are at least this age in the report.
        options (Optional[Dict[str, Optional[Callable[[UserActivity], bool]]]]):
            0-indexed. It specifies filters for the report generation: whether to
            include inactive users and an activity filter.

    Returns:
        List[Dict[str, str]]: A list of dictionaries representing user activity
        reports. Each dictionary contains information about a user such as their
        name, age, role, address, and activities they have performed.

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
