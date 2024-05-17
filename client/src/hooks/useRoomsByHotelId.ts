import { useEffect, useState } from "react";
import roomsService from "../services/roomsService";
import { IRoom } from "../interface/IRoom";

const useRoomsByHotelId = (hotelId: number, isAvailable?: boolean) => {
  const [rooms, setRooms] = useState<IRoom[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      const data = await roomsService().getRoomsByHotelId(hotelId, isAvailable);
      if (!data.error) setRooms(data);
    };

    fetchData();
  }, [hotelId, isAvailable]);

  return { rooms };
};

export default useRoomsByHotelId;
