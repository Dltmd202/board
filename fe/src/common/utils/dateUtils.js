export const formatDate = (datetime) => {
  const [date, time] = datetime.split('T');
  return `${date.replace(/-/g, '.')}.${time.slice(0, 5)}`;
}