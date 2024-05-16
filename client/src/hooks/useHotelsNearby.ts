import { useEffect, useState } from "react";
import hotelsService from "../services/hotels";

const useHotelsNearby = (kmValue: string) => {
  const [hotels, setHotels] = useState<IHotel[]>([]);
  const [latitude, setLatitude] = useState<number | null>(0);
  const [longitude, setLongitude] = useState<number | null>(0);

  useEffect(() => {
    const getLocation = () => {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position) => {
          setLatitude(position.coords.latitude);
          setLongitude(position.coords.longitude);
        });
      }
    };
    getLocation();
  }, []);

  useEffect(() => {
    const queryParams = new URLSearchParams({
      latitude: String(latitude),
      longitude: String(longitude),
      userDistanceInKm: kmValue == "" ? "0" : kmValue,
    });

    const fetchData = async () => {
      const data = await hotelsService().getHotelsNearby(queryParams);

      setHotels(data);
    };

    fetchData();
  }, [kmValue, latitude, longitude]);

  return hotels;
};

export default useHotelsNearby;
