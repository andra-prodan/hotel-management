import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import './App.css'
import MainPage from './pages/MainPage'
import HotelPage from './pages/HotelPage'

function App() {
  const router = createBrowserRouter([
    {
      path: "/",
      element: <MainPage />
    },
    {
      path: "/hotel/:hotelId",
      element: <HotelPage />

    }
  ])

  return (
    <>
      <RouterProvider router={router} />
    </>
  )
}

export default App
