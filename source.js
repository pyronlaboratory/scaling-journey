/**
 * @description Generates a report for users based on specified criteria. It filters
 * users by age and activity status, and formats their registration dates according
 * to a user-defined date format. The resulting report includes user name, age,
 * activity status, and formatted registration date.
 *
 * @param {user[]} users - An array of user objects.
 *
 * @param {number} minAge - Used to filter users based on their age.
 *
 * @param {object} options - Optional, which can be used to customize the report
 * generation process.
 *
 * @returns {object} An array of objects with properties name, age, active, and
 * registrationDate. Each object represents a user meeting certain criteria.
 */
function generateUserReport(users, minAge = 18, options = {}) {
  const { includeInactive = false, dateFormat = 'YYYY-MM-DD' } = options;
  
  /**
   * @description Converts a date into a specified string format. It takes a date and
   * a format as inputs, extracts the day, month, and year from the date, formats them
   * according to the input format, and returns the formatted string.
   *
   * @param {string} date - Expected to represent a date.
   *
   * @param {string} format - Used to define the desired output format.
   *
   * @returns {string} A formatted date in accordance with the specified format. The
   * returned string replaces placeholders 'YYYY', 'MM', and 'DD' with corresponding
   * year, month, and day values, respectively.
   */
  function formatDate(date, format) {
    const d = new Date(date);
    let day = d.getDate();
    let month = d.getMonth() + 1;
    const year = d.getFullYear();

    if (day < 10) day = '0' + day;
    if (month < 10) month = '0' + month;

    return format.replace('YYYY', year).replace('MM', month).replace('DD', day);
  }

  return users
    .filter(user => user.age >= minAge && (includeInactive || user.active))
    .map(user => ({
      name: user.name,
      age: user.age,
      active: user.active,
      registrationDate: formatDate(user.registrationDate, dateFormat),
    }));
}
