export interface IReservation {
  id: number;
  provUser: string | null;
  roomId: number;
  checkIn: string;
  checkOut: string;
}
