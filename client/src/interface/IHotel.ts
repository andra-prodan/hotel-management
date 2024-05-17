import { IFeedback } from "./IFeedback";
import { IRoom } from "./IRoom";

export interface IHotel {
  id: number;
  name: string;
  latitude: number;
  longitude: number;
  rooms: IRoom[];
  feedback: IFeedback[];
}
