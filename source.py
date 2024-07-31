from enum import Enum
from typing import List, Dict, Tuple, Callable, Optional
from datetime import datetime

class UserRole(Enum):
    """
    Defines an enumeration for user roles, which can be either `ADMIN`, `USER`,
    or `GUEST`. This allows developers to assign a role to a user and easily
    distinguish between different types of users based on their privileges and
    access levels.

    Attributes:
        ADMIN (UserRole): Equal to "Admin".
        USER (UserRole): Assigned the string value `"User"`. It represents a user
            role with limited permissions.
        GUEST (UserRole): Assigned a value of "Guest". It represents a guest user
            role with limited access or privileges.

    """
    ADMIN = "Admin"
    USER = "User"
    GUEST = "Guest"

UserActivity = Tuple[str, str, datetime]

class User:
    # inline comment
    """
    Defines a user entity with attributes: name, age, activity status, role, and
    address. It also maintains a list of user activities. This class provides a
    way to represent users with varying roles and activities, facilitating storage
    and retrieval of user information.

    Attributes:
        name (str): Initialized with a given string value in the `__init__` method.
            It represents the user's name.
        age (int): Initialized during object creation with a specified integer
            value. It represents the age of the user.
        active (bool): Initialized with a given boolean value during object creation,
            indicating whether the user is active or not.
        role (UserRole): Initialized with a value provided to the class constructor.
            It represents the role played by the user, likely categorizing them
            into specific groups or positions within a system.
        address (Dict[str,str]): Expected to contain string-value pairs representing
            the user's address, where keys are likely street name, city, state,
            country, or similar details.
        activities (List[UserActivity]): Initialized with a list of user activities
            when the `User` object is created. It represents a collection of
            activities performed by the user.

    """
    def __init__(self, name: str, age: int, active: bool, role: UserRole, address: Dict[str, str], activities: List[UserActivity]):
        # inline comment
        """
        Initializes a user object by setting its properties: name, age, active
        status, role, address (a dictionary), and activities (a list).

        Args:
            name (str): Assigned to the instance variable `self.name`. It is
                expected to hold a string value representing the user's name.
            age (int): Required to create an instance of this class, indicating
                the user's age.
            active (bool): Assigned to an instance variable of the same name. It
                represents a boolean value indicating whether the user is active
                or not.
            role (UserRole): Assigned to an instance variable named `self.role`.
            address (Dict[str, str]): Expected to be a dictionary where keys are
                strings and values are also strings. This suggests that an
                individual's address can have multiple attributes with corresponding
                values.
            activities (List[UserActivity]): Used to initialize an instance variable
                with a list of user activities.

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
    Formats a user activity into a string, taking a tuple-like object (`activity`)
    as input, which contains username, action, and date. It returns a string
    describing the activity in the format "username performed action on YYYY-MM-DD".

    Args:
        activity (UserActivity): Expected to be a tuple or list that contains
            exactly three elements: username, action, and date.

    Returns:
        str: A formatted string that represents an activity, indicating the username
        who performed the action and when it was done.

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
    Generates a report summarizing user activities, filtering by age and activity
    status. It takes a list of users, an optional minimum age, and an optional
    dictionary of filter options (including inactive users and custom activity filters).

    Args:
        users (List[User]): Expected to be a list of User objects, where each User
            object has attributes such as name, age, active status, role, and address.
        min_age (int): 18 by default. It filters out users whose age is less than
            this minimum age.
        options (Optional[Dict[str, Optional[Callable[[UserActivity], bool]]]]):
            Optional by default. It allows filtering users based on two options:
            whether to include inactive users and a custom activity filter.

    Returns:
        List[Dict[str, str]]: A list of dictionaries. Each dictionary represents
        a user and contains their name, age, whether they are active or not, role,
        address, and a list of activities that match the provided filter criteria.

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
