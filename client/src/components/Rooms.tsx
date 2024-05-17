import { Button, Grid, Typography } from "@mui/material";
import { IRoom } from "../interface/IRoom";
import reservationsService from "../services/reservationsService";
import { getRoomType } from "../utils/func";
import { useNavigate, useParams } from "react-router-dom";
import useReservationById from "../hooks/useReservationById";
import { DatePicker, LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { useState } from "react";
import { Dayjs } from "dayjs";


export const Rooms = ({ rooms, change }: { rooms: IRoom[], change?: boolean }) => {
    const [checkIn, setCheckIn] = useState<Dayjs | null>(null);
    const [checkOut, setCheckOut] = useState<Dayjs | null>(null);
    const { reservationId } = useParams();
    const navigate = useNavigate();

    const { reservation } = useReservationById(reservationId ? Number(reservationId) : null);
    const { createReservation } = reservationsService();

    const handleBookRoom = (e: React.MouseEvent<HTMLButtonElement>) => {
        if (checkIn == null) return;
        if (checkOut == null) return;

        createReservation(Number(e.currentTarget.name), checkIn.toISOString().slice(0, 10), checkOut.toISOString().slice(0, 10))
        window.location.reload();
    }

    const handleUpdateRoom = (e: React.MouseEvent<HTMLButtonElement>) => {
        reservationsService().updateReservation(Number(e.currentTarget.name), reservation);
        navigate("/bookings");
    }

    if (rooms.length == 0) return <Grid container justifyContent='center' pt={10}><Typography >There are no rooms available!</Typography></Grid>

    return (
        <Grid container justifyContent='space-evenly' pt={6}>
            {rooms.map(room => {
                return (
                    <Grid container direction='column' key={room.id} gap={2} width={200}>
                        <Typography>Room number: {room.roomNumber}</Typography>
                        <Typography>Price: {room.price}</Typography>
                        <Typography>Type: {getRoomType(room.type)}</Typography>
                        <Typography>Availability: {room.available ? "Available" : "Not available"}</Typography>
                        {change ? <></> : <>
                            <LocalizationProvider dateAdapter={AdapterDayjs}>
                                <DatePicker label="Check in" value={checkIn} onChange={(date: Dayjs | null) => setCheckIn(date)} />
                            </LocalizationProvider>
                            <LocalizationProvider dateAdapter={AdapterDayjs}>
                                <DatePicker label="Check out" value={checkOut} onChange={(date: Dayjs | null) => setCheckOut(date)} />
                            </LocalizationProvider>
                        </>}

                        {!change ?
                            <Button variant="contained" name={String(room.id)} onClick={handleBookRoom}>Book room</Button> : <Button variant="contained" name={String(room.id)} onClick={handleUpdateRoom}>Choose room</Button>
                        }

                    </Grid>
                )
            })}
        </Grid>
    )
}