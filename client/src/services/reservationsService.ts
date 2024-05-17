import { IReservation } from "../interface/IReservation";

const reservationsService = () => {
  const getAllReservations = async () => {
    return await fetch("http://localhost:8080/api/reservations/").then((data) =>
      data.json()
    );
  };

  const getReservationById = async (id: number) => {
    return await fetch(`http://localhost:8080/api/reservations/${id}`).then(
      (data) => data.json()
    );
  };

  const createReservation = async (
    roomId: number,
    checkIn: string,
    checkOut: string
  ) => {
    return await fetch(
      `http://localhost:8080/api/reservations/create/${roomId}`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          provUser: "1",
          checkIn: checkIn,
          checkOut: checkOut,
        }),
      }
    ).then((data) => data.json());
  };

  const updateReservation = async (
    newRoomId: number,
    reservation: IReservation | null
  ) => {
    return await fetch(
      `http://localhost:8080/api/reservations/update/${reservation?.id}`,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          id: reservation?.id,
          provUser: reservation?.provUser,
          roomId: newRoomId,
          checkIn: reservation?.checkIn,
          checkOut: reservation?.checkOut,
        }),
      }
    ).then((data) => data.json());
  };

  const deleteReservation = async (id: number) => {
    return await fetch(`http://localhost:8080/api/reservations/delete/${id}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    }).then((data) => data.json());
  };

  return {
    getAllReservations,
    getReservationById,
    createReservation,
    updateReservation,
    deleteReservation,
  };
};

export default reservationsService;
