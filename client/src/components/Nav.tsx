import { Grid, Typography } from "@mui/material"
import { Link } from "react-router-dom"

export const Nav = () => {
    return (
        <Grid container spacing={4} justifyContent="center" p={4} borderBottom={1}>
            <Grid item>
                <Typography><Link to="/" style={{ textDecoration: 'none', color: '#000' }}>Home</Link></Typography>
            </Grid>
            <Grid item>
                <Typography><Link to="/bookings" style={{ textDecoration: 'none', color: '#000' }}>My bookings</Link></Typography>
            </Grid>
        </Grid>
    )
}