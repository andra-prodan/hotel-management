const roomsService = () => {
  const getRoomsByHotelId = async (hotelId: number, isAvailable?: boolean) => {
    return await fetch(
      `http://localhost:8080/api/rooms/${hotelId}/rooms?isAvailable=${
        isAvailable ? true : false
      }`
    ).then((data) => data.json());
  };

  const getRoomById = async (id: number) => {
    return await fetch(`http://localhost:8080/api/rooms/${id}`).then((data) =>
      data.json()
    );
  };

  return { getRoomsByHotelId, getRoomById };
};

export default roomsService;
