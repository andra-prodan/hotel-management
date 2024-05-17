import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import MainPage from './pages/MainPage'
import HotelPage from './pages/HotelPage'
import BookingsPage from './pages/BookingsPage'
import HotelReviewsPage from './pages/HotelReviewsPage'

function App() {
  const router = createBrowserRouter([
    {
      path: "/",
      element: <MainPage />
    },
    {
      path: "/hotel/:hotelId",
      element: <HotelPage />
    },
    {
      path: "/hotel/:hotelId/change/:reservationId",
      element: <HotelPage change={true} />
    },
    {
      path: "/bookings",
      element: <BookingsPage />
    },
    {
      path: "/hotel/feedback/:hotelId",
      element: <HotelReviewsPage />
    }
  ])

  return (
    <>
      <RouterProvider router={router} />
    </>
  )
}

export default App
