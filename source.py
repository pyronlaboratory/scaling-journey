from enum import Enum
from typing import List, Dict, Tuple, Callable, Optional
from datetime import datetime

class UserRole(Enum):
    """
    Represents a set of predefined roles for users, which can be accessed as enum
    values (ADMIN, USER, GUEST). It associates each role with a string value
    ("Admin", "User", "Guest"). The class is likely used to categorize and manage
    user permissions.

    Attributes:
        ADMIN (str|bytes): 4 characters long, with a value representing the user
            role 'Admin'. It corresponds to the first member of the Enum.
        USER (UserRole): Associated with the string value "User". It represents a
            user role and can be used to differentiate users from admins or guests.
        GUEST (str|Literal[Admin,User,Guest]): 5 characters long. It represents a
            user role with the name 'Guest'.

    """
    ADMIN = "Admin"
    USER = "User"
    GUEST = "Guest"

UserActivity = Tuple[str, str, datetime]

class User:
    """
    Instantiates a user object with properties including name, age, activity status,
    role, address, and activities, allowing for comprehensive representation of
    individual users within an application or system.

    """
    # inline comment
    def __init__(self, name: str, age: int, active: bool, role: UserRole, address: Dict[str, str], activities: List[UserActivity]):
        """
        Initializes user attributes such as name, age, activity status, role,
        address, and list of activities.

        Args:
            name (str): Assigned to an instance attribute of the same name, which
                is then set to the value passed as an argument when creating an
                object of this class. It represents the user's name.
            age (int): Required for initialization, indicating that it is an
                essential attribute of the class instance.
            active (bool): Used to indicate whether a user account is active or
                not. It defaults to True, implying that users are active by default
                unless specified otherwise.
            role (UserRole): Assigned to an instance variable with the same name.
                The UserRole type is not defined in this code snippet, but it
                likely represents a predefined set of user roles.
            address (Dict[str, str]): Defined as a dictionary with string keys and
                values representing an individual's address. It maps street names
                to their corresponding addresses.
            activities (List[UserActivity]): Expected to contain multiple instances
                of UserActivity, representing various activities associated with
                the user.

        """
        # inline comment
        self.name = name
        self.age = age
        self.active = active
        self.role = role
        self.address = address
        self.activities = activities

def format_activity(activity: UserActivity) -> str:
    # inline comment
    username, action, date = activity
    return f"{username} performed {action} on {date.strftime('%Y-%m-%d')}"

def generate_user_activity_report(
    users: List[User],
    min_age: int = 18,
    options: Optional[Dict[str, Optional[Callable[[UserActivity], bool]]]] = None
) -> List[Dict[str, str]]:
    # inline comment
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
