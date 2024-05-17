import { Grid, Typography } from "@mui/material";
import { Reservation } from "../components/Reservation";
import useAllReservations from "../hooks/useAllReservations";
import { Nav } from "../components/Nav";

const BookingsPage = () => {
    const { reservations } = useAllReservations();

    if (reservations.length === 0) return (<><Nav /><Grid container justifyContent='center' pt={10}><Typography >No bookings made!</Typography></Grid></>)

    return (
        <>
            <Nav />
            <Grid container direction='row' justifyContent='space-evenly' pt={6}>
                {reservations.map(reservation => {
                    return <Reservation key={reservation.id} reservation={reservation} />
                })}
            </Grid>
        </>
    )
}

export default BookingsPage;