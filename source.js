/**
 * @description Generates a report for users based on their age and activity status.
 * It filters out users under a specified minimum age (default is 18) and inactive
 * users unless explicitly included. The report includes the user's name, age, activity
 * status, and registration date in a formatted string.
 *
 * @param {User[]} users - Required for report generation.
 *
 * @param {number} minAge - Used to filter users based on their age.
 *
 * @param {object} options - Optional.
 *
 * @returns {object} An array of objects containing properties name, age, active and
 * registrationDate for users who meet specified conditions.
 */
function generateUserReport(users, minAge = 18, options = {}) {
  const { includeInactive = false, dateFormat = 'YYYY-MM-DD' } = options;
  
  /**
   * @description Takes a date and a format string as input, converts the date to a
   * human-readable format, and returns a string representing the formatted date based
   * on the provided format string.
   *
   * @param {number} date - The date to be formatted.
   *
   * @param {string} format - Used to define the output date format.
   *
   * @returns {string} A formatted date representation according to the specified format,
   * replacing placeholders 'YYYY' with the year, 'MM' with the month and 'DD' with the
   * day in the original date.
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
