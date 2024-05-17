import { useEffect, useState } from "react";
import { IHotel } from "../interface/IHotel";
import hotelsService from "../services/hotelsService";

const useAllHotels = () => {
  const [allHotels, setHotels] = useState<IHotel[]>();

  useEffect(() => {
    const fetchData = async () => {
      const data = await hotelsService().getAllHotels();
      if (!data.error) setHotels(data);
    };

    fetchData();
  }, []);

  return { allHotels };
};

export default useAllHotels;
