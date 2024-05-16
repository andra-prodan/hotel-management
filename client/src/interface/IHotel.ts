interface IHotel {
  id: number;
  name: string;
  latitude: number;
  longitude: number;
  rooms: IRoom[];
  feedback: IFeedback[];
}
