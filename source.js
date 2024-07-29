function generateUserReport(users, minAge = 18, options = {}) {
  const { includeInactive = false, dateFormat = 'YYYY-MM-DD' } = options;
  
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
