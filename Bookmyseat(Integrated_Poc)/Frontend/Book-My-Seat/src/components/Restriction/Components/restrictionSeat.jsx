import Paper from '@mui/material/Paper';
import "../../EditSeats/styles/editSeats.css"
import { useState, useEffect } from "react";
import axios from '../../../Services/axiosToken';
import RestrictSeatLegend from './restrictSeatLegend';
import ReservedGroundFloor from './restrictGroundFloor';
import ReservedFirstFloor from './restrictFirstFloor';
import ReservedMezzanineFloor from './restrictMezzanineFlorr';
import ReservedSecondFloor from './restrictSecondFloor';
import ReservedThirdFloor from './restrictThirdFloor';
import ReservedTrainingRoom from './restrictTrainingFloor';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const RestrictSeat = () => {
  const [selectedFloor, setSelectedFloor] = useState("ground-floor")
  const [groundFloorData, setGroundFloorData] = useState(null)
  const [mezzanineFloorData, setMezzanineFloorData] = useState(null)
  const [firstFloorData, setFirstFloorData] = useState(null)
  const [secondFloorData, setSecondFloorData] = useState(null)
  const [thirdFloorData, setThirdFloorData] = useState(null)
  const [tainingRoomData,setTrainingRoomData] = useState(null)
  const [reservedData, setReservedData] = useState(null)
  const [userData, setUserData] = useState(null)
  const [selectedSeatId, setSelectedSeatId] = useState(null);
  
  const onSeatClick = (seatId) => {
    setSelectedSeatId(seatId);
  };

  useEffect(() => {
    fetchData(selectedFloor)
    fetchReserved()
  }, [selectedFloor])

  const renderFloor = () => {
    if (selectedFloor === "ground-floor") {
      return <ReservedGroundFloor groundFloorData={groundFloorData} reservedData={reservedData}  onSeatClick={onSeatClick} />
    } else if (selectedFloor === "mezzanine-floor") {
      return <ReservedMezzanineFloor mezzanineFloorData={mezzanineFloorData} reservedData={reservedData} onSeatClick={onSeatClick}/>
    } else if (selectedFloor === "first-floor") {
      return <ReservedFirstFloor firstFloorData={firstFloorData} reservedData={reservedData} onSeatClick={onSeatClick}/>
    } else if (selectedFloor === "second-floor") {
      return <ReservedSecondFloor secondFloorData={secondFloorData} reservedData={reservedData} onSeatClick={onSeatClick}/>
    } else if (selectedFloor === "third-floor") {
      return <ReservedThirdFloor thirdFloorData={thirdFloorData} reservedData={reservedData} onSeatClick={onSeatClick}/>
    } else if (selectedFloor === "training-room") {
      return <ReservedTrainingRoom tainingRoomData={tainingRoomData} reservedData={reservedData} onSeatClick={onSeatClick}/>
    } else {
      return null
    }
  }

  const handleUserFetch = (UserID) => {
    if(UserID){
      axios.get(`http://localhost:5000/bookmyseat/admin/getAllUsers/${UserID}`)
      .then(response => {
        if (response.data.length === 0) {
          alert('No data found for the provided UserID.');
        } else {
          setUserData(response.data)
          // console.log(response.data[0].userId)
          alert(`${response.data[0].firstName+ " " +response.data[0].lastName} selected for Seat Reservation`)
        }
      })
      .catch(error => {
        console.error('Error fetching UserID:', error);
        alert('Enter valid UserID.');
      });
    }else {
      alert("Please Enter a Employee ID")
    } 
  };

  const fetchReserved = () => {
    axios.get('http://localhost:5000/bookmyseat/admin/reserve')
    .then(response => {
      // console.log("Reserved Data:",response.data)
      setReservedData(response.data)
    })
    .catch(error => {
      console.error('Error fetching reserved data:', error)
    })
  }

  const fetchData = (floor) => {
    axios.get(`http://localhost:5000/bookmyseat/admin/user-seat-info`)
      .then(response => {
        const allData = response.data
        // console.log("Raw Data :", allData)
        let filteredData = null
  
        if (floor === "ground-floor") {
          filteredData = allData.filter(item => item.floor_id === 1)
          setGroundFloorData(filteredData)
        } 
        else if (floor === "mezzanine-floor") {
          filteredData = allData.filter(item => item.floor_id === 2)
          setMezzanineFloorData(filteredData)
        } 
        else if (floor === "first-floor") {
          filteredData = allData.filter(item => item.floor_id === 3)
          setFirstFloorData(filteredData)
        } 
        else if (floor === "second-floor") {
          filteredData = allData.filter(item => item.floor_id === 4)
          setSecondFloorData(filteredData)
        } 
        else if (floor === "third-floor") {
          filteredData = allData.filter(item => item.floor_id === 5)
          setThirdFloorData(filteredData)
        }
        else if (floor === "training-room") {
          filteredData = allData.filter(item => item.floor_id === 6)
          setTrainingRoomData(filteredData)
        }
      })
      .catch(error => {
        console.error('Error fetching floor data:', error)
      })

  }

  const reserveSeat = () => {
    if (!selectedSeatId) {
      alert("Select a Seat to Reserve");
      return;
    } else if (!userData){
      alert("Select a user to reserve");
      return;
    }

    const seatNumber = selectedSeatId
    const userId = userData ? userData[0].userId : null;
    let floorId;
    if (selectedFloor === "ground-floor") {
      floorId = 1
    } 
    else if (selectedFloor === "mezzanine-floor") {
      floorId = 2
    } 
    else if (selectedFloor === "first-floor") {
      floorId = 3
    } 
    else if (selectedFloor === "second-floor") {
      floorId = 4
    } 
    else if (selectedFloor === "third-floor") {
      floorId = 5
    }
    else if (selectedFloor === "training-room") {
      floorId = 6      
    }
    // console.log(floorId)
    // console.log(seatNumber)
    // console.log(userId)
    axios.post(`http://localhost:5000/bookmyseat/admin/reserve/${userId}/${floorId}/${seatNumber}`)
    .then(response => {
      alert( 
        `${response.data} for\n 
        Employee : ${userData[0].firstName+ " " +userData[0].lastName}\n
        Seat Number : ${userId}\n
        \nFloor: ${selectedFloor}`, );
        fetchReserved()
    })
    .catch(error => {
      console.error('Error reserving seat:', error);
      alert('Seat already reserved for User');
    });
  }
  
  return (
    <div className="layout-container">
      <h2 className="layout-header">Reserve Seats</h2>
      <div className="floor-select">
        <Paper className={`floor ${selectedFloor === "ground-floor" ? 'floor-selected' : ''}`} elevation={5} onClick={() => setSelectedFloor("ground-floor")} >Ground Floor</Paper>
        <Paper className={`floor ${selectedFloor === "mezzanine-floor" ? 'floor-selected' : ''}`} elevation={5} onClick={() => setSelectedFloor("mezzanine-floor")} >Mezzanine Floor</Paper>
        <Paper className={`floor ${selectedFloor === "first-floor" ? 'floor-selected' : ''}`} elevation={5} onClick={() => setSelectedFloor("first-floor")} >First Floor</Paper>
        <Paper className={`floor ${selectedFloor === "second-floor" ? 'floor-selected' : ''}`} elevation={5} onClick={() => setSelectedFloor("second-floor")} >Second Floor</Paper>
        <Paper className={`floor ${selectedFloor === "third-floor" ? 'floor-selected' : ''}`} elevation={5} onClick={() => setSelectedFloor("third-floor")} >Third Floor</Paper>
        <Paper className={`floor ${selectedFloor === "training-room" ? 'floor-selected' : ''}`} elevation={5} onClick={() => setSelectedFloor("training-room")} >Training Room</Paper>
      </div>
      <div className="floor-container">
        <RestrictSeatLegend handleUserFetch={handleUserFetch} reserveSeat={reserveSeat}/>
        {renderFloor()}
      </div>
      <ToastContainer
            position="top-center"
            autoClose={1000}
            hideProgressBar={true}
            style={{
              width: '300px',
              borderRadius: '8px',
            }}
          />
    </div>
  )
}

export default RestrictSeat