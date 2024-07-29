/**
 * @description Generates a report for users who are at least a specified minimum age
 * and meet certain criteria for activity status. The report includes user name, age,
 * activity status, and registration date in a specific format.
 *
 * @param {object} users - Expected to be an array of user objects.
 *
 * @param {number} minAge - Used to filter users by age.
 *
 * @param {object} options - Used to specify additional options for generating the report.
 *
 * @returns {UserReport} An array of objects. Each object represents a user and
 * contains properties name, age, active status, and registration date in the specified
 * format.
 */
function generateUserReport(users, minAge = 18, options = {}) {
  const { includeInactive = false, dateFormat = 'YYYY-MM-DD' } = options;
  
  /**
   * @description Formats a date string according to a specified format, replacing
   * 'YYYY' with the full year, 'MM' with the two-digit month, and 'DD' with the two-digit
   * day, padding single digits with zeros if necessary.
   *
   * @param {number} date - 13 digits, representing a timestamp in seconds since 1970.
   *
   * @param {string} format - Used for formatting date.
   *
   * @returns {string} A formatted date representation based on the provided format and
   * the original date.
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
