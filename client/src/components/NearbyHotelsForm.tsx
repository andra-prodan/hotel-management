import { Checkbox, Container, FormControlLabel, Grid, TextField, Typography } from "@mui/material"
import { ChangeEvent, useEffect, useState } from "react"
import { Hotels } from "./Hotels";
import useHotelsNearby from "../hooks/useHotelsNearby";
import { IHotel } from "../interface/IHotel";
import useAllHotels from "../hooks/useAllHotels";

export const NearbyHotelsForm = () => {
    const [kmValue, setKmValue] = useState<string>("");
    const [checked, setChecked] = useState<boolean>(false);
    const [hotelsData, setHotelsData] = useState<IHotel[]>([]);
    const { hotels } = useHotelsNearby(kmValue);
    const { allHotels } = useAllHotels();

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        setKmValue(e.target.value)
    };

    useEffect(() => {
        setHotelsData(hotels);
    }, [hotels])

    const handleCheckbox = () => {
        if (!allHotels?.length) return;
        if (!checked) {
            setHotelsData(allHotels)
        } else {
            setHotelsData([]);
        }
        setChecked(!checked);
    }

    return (
        <Container maxWidth="sm">
            <Grid container justifyContent='center' direction='column' gap={4} pt={4}>
                <Grid container justifyContent='space-evenly' alignItems='center' direction='row'>
                    <TextField id="radius" label="Radius in km" variant="outlined" value={kmValue} onChange={handleChange} />
                    <FormControlLabel label="Show all hotels" control={<Checkbox checked={checked} onChange={handleCheckbox} />} />
                </Grid>
                <Hotels hotels={hotelsData} />
            </Grid>
        </Container>
    )
}