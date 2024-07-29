from enum import Enum
from typing import List, Dict, Tuple, Callable, Optional
from datetime import datetime

class UserRole(Enum):
    ADMIN = "Admin"
    USER = "User"
    GUEST = "Guest"

UserActivity = Tuple[str, str, datetime]

class User:
    # inline comment
    def __init__(self, name: str, age: int, active: bool, role: UserRole, address: Dict[str, str], activities: List[UserActivity]):
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
