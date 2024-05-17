import { Container, Grid, TextField } from "@mui/material"
import { ChangeEvent, useState } from "react"
import { Hotels } from "./Hotels";
import useHotelsNearby from "../hooks/useHotelsNearby";

export const NearbyHotelsForm = () => {
    const [kmValue, setKmValue] = useState<string>("");
    const { hotels } = useHotelsNearby(kmValue);

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        setKmValue(e.target.value)
    };

    return (
        <Container maxWidth="sm">
            <Grid container justifyContent='center' direction='column' gap={4} pt={4}>
                <TextField id="radius" label="Radius in km" variant="outlined" value={kmValue} onChange={handleChange} />
                <Hotels hotels={hotels} />
            </Grid>
        </Container>
    )
}