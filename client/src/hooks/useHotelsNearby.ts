import { useEffect, useState } from "react";
import hotelsService from "../services/hotelsService";
import { IHotel } from "../interface/IHotel";

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
    const fetchData = async () => {
      const queryParams = new URLSearchParams({
        latitude: String(latitude),
        longitude: String(longitude),
        userDistanceInKm: kmValue == "" ? "0" : kmValue,
      });
      const data = await hotelsService().getHotelsNearby(queryParams);
      if (!data.error) setHotels(data);
    };

    fetchData();
  }, [kmValue, latitude, longitude]);

  return { hotels };
};

export default useHotelsNearby;
