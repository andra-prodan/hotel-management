const hotelsService = () => {
  const getHotelsNearby = (queryParams: URLSearchParams) => {
    return fetch(`http://localhost:8080/api/hotels/nearby?${queryParams}`).then(
      (data) => data.json()
    );
  };

  return { getHotelsNearby };
};

export default hotelsService;
