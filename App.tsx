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
 * @description Takes an object `activity` with three properties: `username`, `action`,
 * and `date`. It returns a formatted string describing the activity, combining these
 * properties with a specific format. The date is converted to a localized string
 * using the `toLocaleDateString()` method.
 *
 * @param {UserActivity} activity - Required for formatting.
 *
 * @returns {string} A formatted message representing an activity performed by a user,
 * including their username, action taken, and date in a specified format.
 */
const formatActivity = (activity: UserActivity): string => {
  const [username, action, date] = activity;
  return `${username} performed ${action} on ${date.toLocaleDateString()}`;
};

/**
 * @description Filters and maps a list of users based on their age, activity status,
 * and specific activities, then renders a report displaying user information and
 * their corresponding activities.
 *
 * @param {object} obj - Non-optional. It contains two properties: 'users' and
 * 'options'. The 'users' property expects an array, and it's default value is not
 * defined. The 'options' property also expects an object, with nested objects inside.
 *
 * @param {User[]} obj.users - Used to filter and render user activity reports.
 *
 * @param {number} obj.minAge - Used to filter users based on their age.
 *
 * @param {{
 *     includeInactive = defaultOptions.includeInactive,
 *     activityFilter = defaultOptions.activityFilter,
 *   } = defaultOptions} obj.options - Used to customize filtering and sorting of
 * user data.
 *
 * @returns {React.ReactElement} A JSX element representing the user activity report,
 * including a list of users with their activities, sorted by age and filtered based
 * on the criteria specified in the props.
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
 * @description Renders a React component with a title "User Activity Report" and
 * passes three props to the `UserActivityReport` component: an array of users, a
 * minimum age (18), and options for filtering user activity.
 */
const App: React.FC = () => (
  <div>
    <h1>User Activity Report</h1>
    <UserActivityReport users={users} minAge={18} options={{ includeInactive: true, activityFilter: ([, action]) => action !== 'logout' }} />
  </div>
);

export default App;
