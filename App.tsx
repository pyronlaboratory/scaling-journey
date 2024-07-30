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
 * @description Takes a user activity object as input and returns a formatted string
 * representing the activity. The activity is composed of a username, action, and
 * date. The function concatenates these components into a human-readable string with
 * the date converted to a localized string representation.
 *
 * @param {UserActivity} activity - Processed into a formatted string.
 *
 * @returns {string} A formatted activity log message. The returned string combines
 * username, action, and date in the specified format.
 */
const formatActivity = (activity: UserActivity): string => {
  const [username, action, date] = activity;
  return `${username} performed ${action} on ${date.toLocaleDateString()}`;
};

/**
 * @description Filters and maps a list of users based on age and activity criteria,
 * then renders a report displaying user information, including their name, role,
 * age, address, status, and activities.
 *
 * @param {object} obj - Non-optional. It has two properties: `users`, which is an
 * array, and `options`. The `users` property corresponds to a set of user objects,
 * while `options` contains nested properties for filtering users and activities.
 *
 * @param {User[]} obj.users - Required for rendering user activity reports.
 *
 * @param {number} obj.minAge - Used to filter users by age.
 *
 * @param {{
 *     includeInactive = defaultOptions.includeInactive,
 *     activityFilter = defaultOptions.activityFilter,
 *   } = defaultOptions} obj.options - Optional, it allows to customize report
 * generation with filtering and excluding inactive users.
 *
 * @returns {JSX.Element} A React component representing a report containing information
 * about multiple users, including their activities. The component consists of a list
 * of user cards with details and a list of activities for each user.
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
 * @description Renders a user interface with a heading "User Activity Report" and
 * passes `users`, `minAge`, and options to a `UserActivityReport` component for
 * rendering. The options include an activity filter that excludes 'logout' actions
 * when calculating the report.
 */
const App: React.FC = () => (
  <div>
    <h1>User Activity Report</h1>
    <UserActivityReport users={users} minAge={18} options={{ includeInactive: true, activityFilter: ([, action]) => action !== 'logout' }} />
  </div>
);

export default App;
