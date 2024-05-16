import { TextField } from "@mui/material"
import { ChangeEvent, useState } from "react"
import { Hotels } from "./Hotels";
import useHotelsNearby from "../hooks/useHotelsNearby";

export const NearbyHotelsForm = () => {
    const [kmValue, setKmValue] = useState<string>("");
    const hotelsNearby = useHotelsNearby(kmValue);

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        setKmValue(e.target.value)
    };

    return (<>
        <TextField id="radius" label="Radius in km" variant="outlined" value={kmValue} onChange={handleChange} />
        <Hotels hotels={hotelsNearby} />
    </>)
}