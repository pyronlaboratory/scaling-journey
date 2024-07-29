import React from 'react';

// Define an enum for user roles
enum UserRole {
  Admin = 'Admin',
  User = 'User',
  Guest = 'Guest',
}

// Define a tuple type for user activity
type UserActivity = [string, 'login' | 'logout' | 'purchase', Date];

// Define the User type with a nested object and an intersection type
type User = {
  name: string;
  age: number;
  active: boolean;
  role: UserRole;
  activities: UserActivity[];
} & {
  address: {
    street: string;
    city: string;
    country: string;
  };
};

// Define the Props type with generics and default values
type Props<T extends User = User> = {
  users: T[];
  minAge?: number;
  options?: {
    includeInactive?: boolean;
    activityFilter?: (activity: UserActivity) => boolean;
  };
};

const defaultOptions = {
  includeInactive: false,
  activityFilter: (activity: UserActivity) => true,
};

/**
 * @description Converts a user activity object into a string representation. It takes
 * an array-like object with three properties: username, action, and date. The function
 * returns a formatted string combining these properties, displaying the username,
 * action, and date of the activity in a human-readable format.
 *
 * @param {UserActivity} activity - Used to format user activity information.
 *
 * @returns {string} A formatted message indicating the username, action and date of
 * an activity.
 */
const formatActivity = (activity: UserActivity): string => {
  const [username, action, date] = activity;
  return `${username} performed ${action} on ${date.toLocaleDateString()}`;
};

/**
 * @description Filters users based on their age and activity status, then maps them
 * to a report format displaying user information and their activities. The report
 * is rendered as an unordered list with each user's details and activities listed individually.
 *
 * @param {object} obj - 2 level nested. It contains two properties: 'users' and
 * 'options'. The 'users' property expects an array, while the 'options' property is
 * an object with default values for 'includeInactive' and 'activityFilter'.
 *
 * @param {UserActivityReportProps['users']} obj.users - An array of user objects.
 *
 * @param {number} obj.minAge - Used to filter users by age.
 *
 * @param {{
 *     includeInactive = defaultOptions.includeInactive,
 *     activityFilter = defaultOptions.activityFilter,
 *   } = defaultOptions} obj.options - Optional, which means that if it's not passed
 * as an argument when calling this function, then it will have its default values.
 *
 * @returns {JSX.Element} A React component that represents a user activity report.
 * The report consists of a list of user cards with their name, role, age, address
 * and activities. Each user card includes details about the user's status and the
 * activities they are involved in.
 */
const UserActivityReport: React.FC<Props> = ({
  users,
  minAge = 18,
  options: {
    includeInactive = defaultOptions.includeInactive,
    activityFilter = defaultOptions.activityFilter,
  } = defaultOptions,
}) => {
  // Filter and map users based on the criteria
  const filteredUsers = users
    .filter(user => user.age >= minAge && (includeInactive || user.active))
    .map(user => ({
      ...user,
      activities: user.activities.filter(activityFilter),
    }));

  // Render the user activity report
  return (
    <div className="user-activity-report">
      {filteredUsers.map((user, index) => (
        <div key={index} className="user-card">
          <h3>{user.name} ({user.role})</h3>
          <p>Age: {user.age}</p>
          <p>Address: {user.address.street}, {user.address.city}, {user.address.country}</p>
          <p>Status: {user.active ? 'Active' : 'Inactive'}</p>
          <h4>Activities:</h4>
          <ul>
            {user.activities.map((activity, idx) => (
              <li key={idx}>{formatActivity(activity)}</li>
            ))}
          </ul>
        </div>
      ))}
    </div>
  );
};

export default UserActivityReport;

// Example usage in a React component
const users: User[] = [
  {
    name: 'Alice',
    age: 25,
    active: true,
    role: UserRole.Admin,
    address: { street: '123 Main St', city: 'Metropolis', country: 'USA' },
    activities: [
      ['Alice', 'login', new Date('2023-01-01')],
      ['Alice', 'purchase', new Date('2023-02-15')],
    ],
  },
  {
    name: 'Bob',
    age: 17,
    active: false,
    role: UserRole.User,
    address: { street: '456 Elm St', city: 'Gotham', country: 'USA' },
    activities: [
      ['Bob', 'logout', new Date('2023-03-22')],
    ],
  },
  {
    name: 'Charlie',
    age: 30,
    active: true,
    role: UserRole.Guest,
    address: { street: '789 Oak St', city: 'Star City', country: 'USA' },
    activities: [
      ['Charlie', 'login', new Date('2023-04-10')],
    ],
  },
];

/**
 * @description Renders a user interface with an `<h1>` element displaying "User
 * Activity Report". It also renders a `UserActivityReport` component, passing in
 * `users`, `minAge=18`, and an object containing options: `includeInactive=true` and
 * a filter function that excludes 'logout' actions.
 */
const App: React.FC = () => (
  <div>
    <h1>User Activity Report</h1>
    <UserActivityReport users={users} minAge={18} options={{ includeInactive: true, activityFilter: ([, action]) => action !== 'logout' }} />
  </div>
);

export default App;
