const hotelsService = () => {
  const getAllHotels = async () => {
    return await fetch("http://localhost:8080/api/hotels/").then((data) =>
      data.json()
    );
  };

  const getHotelsNearby = async (queryParams: URLSearchParams) => {
    return await fetch(
      `http://localhost:8080/api/hotels/nearby?${queryParams}`
    ).then((data) => data.json());
  };

  return { getHotelsNearby, getAllHotels };
};

export default hotelsService;
