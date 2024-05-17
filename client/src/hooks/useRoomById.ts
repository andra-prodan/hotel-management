import { useEffect, useState } from "react";
import { IRoom } from "../interface/IRoom";
import roomsService from "../services/roomsService";

const useRoomById = (id: number) => {
  const [room, setRoom] = useState<IRoom | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      const data = await roomsService().getRoomById(id);
      if (!data.error) setRoom(data);
    };

    fetchData();
  }, [id]);

  return { room };
};

export default useRoomById;
