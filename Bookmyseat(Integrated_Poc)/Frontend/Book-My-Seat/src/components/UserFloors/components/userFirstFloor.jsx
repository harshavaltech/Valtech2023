import React, { useState, useEffect, useMemo } from 'react';
import '../../EditSeats/styles/groundFloor.css';
import Paper from '@mui/material/Paper';

const generateRoomSeats = (room, handleSeatClick, selectedSeatId) => {
  return room.map((col, colIndex) => (
      <React.Fragment key={colIndex}>
          <div className="col">
              {col.map((seat, seatIndex) => {
                  return (
                    <div key={seat && seat.id ? seat.id : `empty-${seatIndex}`} className="seat-container">
                    {seat && typeof seat === 'object' && 'id' in seat ? (
                      <div
                        key={seat.id}
                        className={`seat ${seat.preferred ? "preferred-seat" : ""} ${
                            'bookingStatus' in seat && seat.bookingStatus
                              ? "reserved-seat"
                              : selectedSeatId === seat.id
                              ? "selected-seat"
                              : seat.reservedStatus
                              ? "disabled-seat"
                              : "available-seat"
                          }`}
                        onClick={() => handleSeatClick(seat)}
                      >
                        {seat.id}
                      </div>
                          ) : (
                              <div key={`empty-${seatIndex}`} className="empty-seat"></div>
                          )}
                      </div>
                  );
              })}
          </div>
      </React.Fragment>
  ));
};

const UserFirstFloor = ({firstFloorData,onSeatSelect, selectedSeat, preferredSeatsDataFirstFloor,reserveData}) => {
  const [combinedSeats, setCombinedSeats] = useState([])

  useEffect(() => {
    if (firstFloorData && firstFloorData.length > 0 && preferredSeatsDataFirstFloor && preferredSeatsDataFirstFloor.length > 0 && reserveData) {
        const combinedData = combineData(seats, firstFloorData, preferredSeatsDataFirstFloor,reserveData);
        setCombinedSeats(combinedData);
    } else {
        setCombinedSeats(seats);
    }
}, [firstFloorData, preferredSeatsDataFirstFloor,reserveData]);

  const combineData = (seats, firstFloorData, preferredSeatsData,reserveData) => {
      const combinedData = seats.map(row => {
          return row.map(seat => {
              if (seat === null) return null;
              const matchingSeat = firstFloorData.find(fetchedSeat => fetchedSeat.seatNumber === seat.id);
              const reservedSeat = reserveData.find(reservedSeat => reservedSeat.seatNumber === seat.id);
              const preferredSeat = preferredSeatsData.find(preferredSeat => preferredSeat.seat.seatNumber === seat.id);
              if (reservedSeat) {
                  return { ...seat, ...matchingSeat, ...reservedSeat };
              } else if (preferredSeat) {
                  return { ...seat, ...matchingSeat, ...preferredSeat, preferred: true };
              } else if (matchingSeat) {
                  return { ...seat, ...matchingSeat };
              } else {
                  return seat;
              }
          });
      });
      return combinedData;
  };

  const handleSeatClick = seat => {
      onSeatSelect(seat.id);
  };

  const seats = useMemo(() => [
    [{ id: 1 },{ id: 2 },{ id: 3 },{ id: 4 },null,{ id: 5 },{ id: 6 },{ id: 7 },{ id: 8 }],
    [{ id: 9 },{ id: 10 },{ id: 11 },{ id: 12 },null,{ id: 13 },{ id: 14 },{ id: 15 },{ id: 16 }],
    [{ id: 17 },{ id: 18 },{ id: 19 },{ id: 20 },null,{ id: 21 },{ id: 22 },{ id: 23 },{ id: 24 }],
    [{ id: 25 },{ id: 26 },{ id: 27 },{ id: 28 },null,{ id: 29 },{ id: 30 },{ id: 31 },{ id: 32 }],
    [{ id: 33 },{ id: 34 },{ id: 35 },{ id: 36 },null,{ id: 37 },{ id: 38 },{ id: 39 },{ id: 40 }],
    [null,{ id: 41 },{ id: 42 },{ id: 43 },null,null,{ id: 44 },{ id: 45 },{ id: 46 }],
    [{ id: 47 },{ id: 48 },{ id: 49 },{ id: 50 },null,{ id: 51 },{ id: 52 },{ id: 53 },{ id: 54 }],
    [{ id: 55 },{ id: 56 },{ id: 57 },{ id: 58 },null,{ id: 59 },{ id: 60 },{ id: 61 },{ id: 62 }],
    [{ id: 63 },{ id: 64 },{ id: 65 },{ id: 66 },null,{ id: 67 },{ id: 68 },{ id: 69 },{ id: 70 }],
    [{ id: 71 },{ id: 72 },{ id: 73 },{ id: 74 },null,{ id: 75 },{ id: 76 },{ id: 77 },{ id: 78 }],
    [null,{ id: 79 },{ id: 80 },{ id: 81 },null,null,{ id: 82 },{ id: 83 },{ id: 84 }],
    [{ id: 85 },{ id: 86 },{ id: 87 },{ id: 88 },null,{ id: 89 },{ id: 90 },{ id: 91 },{ id: 92 }],
    [{ id: 93 },{ id: 94 },{ id: 95 },{ id: 96 },{ id: 97 },{ id: 98 },{ id: 99 },{ id: 100 },{ id: 101 },{ id: 102 },{ id: 103 },{ id: 104 },{ id: 105 },null,null],
    [{ id: 106 },{ id: 107 },{ id: 108 },null,{ id: 109 },{ id: 110 },{ id: 111 },{ id: 112 },null,{ id: 113 },{ id: 114 },{ id: 115 },{ id: 116 }],
  ], [])
  
 


    const room4 = combinedSeats.slice(0, 3);
    const room5 = combinedSeats.slice(3, 12);
    const room6 = combinedSeats.slice(12);

    return (
        <div className='groundfloor-container'>
            <div className='seat-map-container'>
                <div className='seat-map-layout-row1'>
                    <Paper className='room-1'><div className='conference-table' >Conference Table</div></Paper>
                    <Paper className='room-2'><div className='conference-table'>Conference Table</div></Paper>
                    <Paper className='room-3'><div className='conference-table'>Conference Table</div></Paper>
                </div>
                <div className='seat-map-layout-row2'>
                    <Paper className='room-4'>{generateRoomSeats(room4, handleSeatClick, selectedSeat)}</Paper>
                    <Paper className='room-5'>{generateRoomSeats(room5, handleSeatClick, selectedSeat)}</Paper>
                    <Paper className='room-6'>{generateRoomSeats(room6, handleSeatClick, selectedSeat)}</Paper>
                </div>
            </div>
        </div>
    );
}

export default UserFirstFloor;
