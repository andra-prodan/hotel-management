const hotelsService = () => {
  const getHotelsNearby = async (queryParams: URLSearchParams) => {
    return await fetch(
      `http://localhost:8080/api/hotels/nearby?${queryParams}`
    ).then((data) => data.json());
  };

  return { getHotelsNearby };
};

export default hotelsService;
