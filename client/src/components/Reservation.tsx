import { Button, Grid, TextField } from "@mui/material";
import { IReservation } from "../interface/IReservation"
import reservationsService from "../services/reservationsService";
import useRoomById from "../hooks/useRoomById";
import { getRoomType } from "../utils/func";
import { Link } from "react-router-dom";

export const Reservation = ({ reservation }: { reservation: IReservation }) => {
    const currentDate = new Date();
    const checkInDate = new Date(reservation.checkIn);
    const timeDifferenceMs = checkInDate.getTime() - currentDate.getTime();
    const hoursDifference = Math.floor(timeDifferenceMs / (1000 * 60 * 60));
    const { room } = useRoomById(reservation.roomId);

    const handleDelete = () => {
        reservationsService().deleteReservation(reservation.id);
        window.location.reload();
    }

    return (<Grid container direction='column' width={300} gap={2}>
        <TextField label="Room Number" value={room?.roomNumber || ""} InputProps={{ readOnly: true }} />
        <TextField label="Type" value={getRoomType(room?.type || "")} InputProps={{ readOnly: true }} />
        <TextField label="Price" value={room?.price || ""} InputProps={{ readOnly: true }} />
        <TextField label="CheckIn" value={reservation.checkIn} InputProps={{ readOnly: true }} />
        <TextField label="CheckOut" value={reservation.checkOut} InputProps={{ readOnly: true }} />
        <Grid container justifyContent='space-between'>
            <Button sx={{ maxWidth: "140px" }} variant="contained" size='small' disabled={hoursDifference <= 2}><Link to={`/hotel/${room?.hotelId}/change/${reservation.id}`} style={{ textDecoration: 'none', color: '#fff' }}>Update reservation</Link></Button>
            <Button sx={{ maxWidth: "140px" }} variant="contained" size='small' disabled={hoursDifference <= 2} onClick={handleDelete}>Delete reservation</Button>
        </Grid>
    </Grid>)
}