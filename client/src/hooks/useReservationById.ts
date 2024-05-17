import { useEffect, useState } from "react";
import { IReservation } from "../interface/IReservation";
import reservationsService from "../services/reservationsService";

const useReservationById = (id: number | null) => {
  const [reservation, setReservation] = useState<IReservation | null>(null);

  useEffect(() => {
    if (id == null) return;
    const fetchData = async () => {
      const data = await reservationsService().getReservationById(id);
      if (!data.error) setReservation(data);
    };

    fetchData();
  }, [id]);

  return { reservation };
};

export default useReservationById;
