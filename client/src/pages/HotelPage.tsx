import { useParams } from "react-router-dom"
import useRoomsByHotelId from "../hooks/useRoomsByHotelId";
import { Rooms } from "../components/Rooms";
import { Nav } from "../components/Nav";

const HotelPage = ({ change }: { change?: boolean }) => {
    const { hotelId } = useParams();
    const { rooms } = useRoomsByHotelId(Number(hotelId), true);

    return (
        <>
            <Nav />
            <Rooms rooms={rooms} change={change} />
        </>
    )
}

export default HotelPage