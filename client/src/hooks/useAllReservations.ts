import { useEffect, useState } from "react";
import reservationsService from "../services/reservationsService";
import { IReservation } from "../interface/IReservation";

const useAllReservations = () => {
  const [reservations, setReservations] = useState<IReservation[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      const data = await reservationsService().getAllReservations();
      if (!data.error) setReservations(data);
    };

    fetchData();
  }, []);

  return { reservations };
};

export default useAllReservations;
