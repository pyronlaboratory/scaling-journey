from enum import Enum
from typing import List, Dict, Tuple, Callable, Optional
from datetime import datetime

class UserRole(Enum):
    """
    Defines an enumeration for user roles, which are ADMIN, USER, and GUEST. Each
    role is represented by a unique string value. This allows for a clear and
    concise way to define and refer to these roles within the program.

    Attributes:
        ADMIN (UserRole): Assigned a value of "Admin". It represents one of the
            possible user roles in the system, specifically an administrative role
            with elevated privileges.
        USER (UserRole): 4 characters long, representing a user role with value "User".
        GUEST (str): 5 characters long. It represents a predefined role in the
            system, denoted as "Guest", which likely signifies that the user does
            not have any specific permissions or access rights.

    """
    ADMIN = "Admin"
    USER = "User"
    GUEST = "Guest"

UserActivity = Tuple[str, str, datetime]

class User:
    # inline comment
    """
    Initializes a user with attributes: name, age, activity status, role, address,
    and activities. It represents a complex user profile, incorporating multiple
    details that can be accessed or modified later.

    Attributes:
        name (str): Initialized with a given string value during object creation.
        age (int): Initialized with an integer value during object creation through
            the `__init__` method. It represents a user's age, which can be any
            positive or zero integer.
        active (bool): Initialized through the constructor with a value passed as
            parameter. It indicates whether the user's account is active or not,
            allowing for filtering or processing based on this status.
        role (UserRole): Used to store the user's role, likely indicating their
            permissions or privileges within a system or application.
        address (Dict[str,str]): Initialized with a dictionary that maps strings
            to strings, representing the user's address details, where each key-value
            pair represents a specific piece of information about their address.
        activities (List[UserActivity]): Used to store a list of user activities.

    """
    def __init__(self, name: str, age: int, active: bool, role: UserRole, address: Dict[str, str], activities: List[UserActivity]):
        # inline comment
        """
        Initializes objects with given parameters: name, age, active status, role,
        address (a dictionary), and activities (a list). These attributes are
        assigned to corresponding instance variables for further use within the class.

        Args:
            name (str): Assigned to an instance variable with the same name. It
                represents a string value, likely a user's full or nickname.
            age (int): Assigned to an instance variable with the same name. It
                represents the age of the user as an integer value.
            active (bool): Assigned a boolean value indicating whether the user's
                account is active or not.
            role (UserRole): Assigned to an instance variable named `self.role`.
                It represents the role of the user, possibly indicating their
                position or status within an organization.
            address (Dict[str, str]): Used to store the user's address information,
                where keys are field names (e.g., "street", "city", etc.) and
                values are corresponding field values.
            activities (List[UserActivity]): Initialized with an empty list. It
                represents a collection of UserActivity objects, which are presumably
                instances of a custom class representing specific user activities
                or habits.

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
    Takes a tuple representing user activity as input and returns a formatted
    string describing the activity. It extracts username, action, and date from
    the tuple, then combines them into a sentence with the specified date format.

    Args:
        activity (UserActivity): Unpacked into three variables: username, action,
            and date.

    Returns:
        str: A formatted string representing an activity, consisting of a username,
        action, and date. The format is "{username} performed {action} on {date}".

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
    It filters users by age and activity status (active or inactive), applies an
    optional activity filter, and includes relevant information such as name, role,
    address, and activities.

    Args:
        users (List[User]): Required to generate a report for one or more users.
            It can contain multiple instances of User objects.
        min_age (int): 18 by default. It filters users based on their age, allowing
            only those with an age greater than or equal to `min_age` to be included
            in the report.
        options (Optional[Dict[str, Optional[Callable[[UserActivity], bool]]]]):
            Defaulted to an empty dictionary. It allows users to specify additional
            filtering criteria for user activities and whether inactive users
            should be included in the report.

    Returns:
        List[Dict[str, str]]: A list of dictionaries. Each dictionary represents
        a user's information and contains the following keys: "name", "age",
        "active", "role", "address", and "activities".

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
