export const getRoomType = (type: string) => {
  switch (type) {
    case "1":
      return "Single Room";
    case "2":
      return "Double Room";
    case "3":
      return "Suite Room";
    case "4":
      return "Matrimonial Room";
    default:
      return "Unknown Room Type";
  }
};
