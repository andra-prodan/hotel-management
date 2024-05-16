import { Link } from "react-router-dom"

export const Hotels = ({ hotels }: { hotels: IHotel[] }) => {
    return <>
        {hotels.map(hotel => {
            return (
                <div key={hotel.id}>
                    <Link to={`/hotel/${hotel.id}`}>{hotel.name}</Link>
                </div>
            )
        })}
    </>
}