import { useEffect, useState } from "react";
import { IFeedback } from "../interface/IFeedback";
import feedbackService from "../services/feedbackService";

const useFeedbackByHotel = (id: number) => {
  const [feedback, setFeedback] = useState<IFeedback[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      const data = await feedbackService().getFeedbackByHotel(id);
      if (!data.error) setFeedback(data);
    };

    fetchData();
  }, [id]);

  return { feedback };
};

export default useFeedbackByHotel;
