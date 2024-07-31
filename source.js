/**
 * @description Generates a report for users based on their age and activity status.
 * It filters users who are at least a certain minimum age (defaulting to 18) and
 * either active or not inactive, as specified by the options. The report includes
 * user name, age, activity status, and registration date.
 *
 * @param {object} users - An array of user data.
 *
 * @param {number} minAge - Used to filter users based on their age.
 *
 * @param {object} options - Used to specify additional settings.
 *
 * @returns {object[]} An array of objects containing user information such as name,
 * age, activity status, and registration date in a specified format.
 */
function generateUserReport(users, minAge = 18, options = {}) {
  const { includeInactive = false, dateFormat = 'YYYY-MM-DD' } = options;
  
  /**
   * @description Takes a date and a format string as input, and returns a formatted
   * date string based on the provided format. It extracts the year, month, and day
   * from the input date and pads single-digit values with zeros before replacing
   * placeholders in the format string.
   *
   * @param {number} date - Unix timestamp.
   *
   * @param {string} format - Used for formatting output.
   *
   * @returns {string} Formatted date according to the specified format, replacing
   * placeholders 'YYYY', 'MM', and 'DD' with year, month, and day respectively, if
   * necessary adding leading zero for single-digit values.
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
