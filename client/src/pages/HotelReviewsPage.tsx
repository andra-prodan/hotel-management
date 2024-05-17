import { Button, Grid, TextField, Typography } from "@mui/material"
import useFeedbackByHotel from "../hooks/useFeedbackByHotel"
import { useParams } from "react-router-dom";
import { ChangeEvent, useState } from "react";
import feedbackService from "../services/feedbackService";
import { Nav } from "../components/Nav";

const HotelReviewsPage = () => {
    const [message, setMessage] = useState("");
    const { hotelId } = useParams();
    const { feedback } = useFeedbackByHotel(Number(hotelId));

    const handleClick = () => {
        feedbackService().createFeedback(Number(hotelId), message);
        window.location.reload();
    }

    return (
        <>
            <Nav />
            <Grid container justifyContent='center'>
                <Grid container direction='column' pt={6} gap={4} width={500}>
                    {feedback.map(feedback => {
                        return (
                            <Typography sx={{ wordBreak: "break-word" }} width={500} key={feedback.id}>"{feedback.message}"</Typography>
                        )
                    })}
                    <TextField label="Leave your review" multiline maxRows={4} fullWidth value={message} onChange={(e: ChangeEvent<HTMLInputElement>) => setMessage(e.target.value)} />
                    <Button variant='contained' onClick={handleClick}>Leave review</Button>
                </Grid>
            </Grid>
        </>
    )
}

export default HotelReviewsPage