const feedbackService = () => {
  const getFeedbackByHotel = async (id: number) => {
    return await fetch(`http://localhost:8080/api/feedback/${id}`).then(
      (data) => data.json()
    );
  };

  const createFeedback = async (id: number, message: string) => {
    return await fetch(`http://localhost:8080/api/feedback/create/${id}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        message: message,
      }),
    }).then((data) => data.json());
  };

  return { getFeedbackByHotel, createFeedback };
};

export default feedbackService;
