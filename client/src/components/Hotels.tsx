import { Link } from "react-router-dom"
import { IHotel } from "../interface/IHotel"
import { Button, Grid, Typography } from "@mui/material"

export const Hotels = ({ hotels }: { hotels: IHotel[] }) => {
    return <>
        {hotels.map(hotel => {
            return (
                <Grid container justifyContent='space-between' alignItems='center' key={hotel.id}>
                    <Typography><Link to={`/hotel/${hotel.id}`} style={{ color: '#000' }}>{hotel.name}</Link></Typography>
                    <Button variant='contained'><Link to={`/hotel/feedback/${hotel.id}`} style={{ textDecoration: 'none', color: '#fff' }}>Reviews</Link></Button>
                </Grid>
            )
        })}
    </>
}