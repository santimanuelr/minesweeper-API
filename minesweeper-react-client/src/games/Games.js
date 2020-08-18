import React, { Component } from 'react';
import './../App.css';
//import Board from './components/Board';

class Games extends Component {

  state = {};

  componentDidMount() {
    const requestOptions = {
      method: 'GET'
    };

    fetch("http://localhost:8080/api/games", requestOptions)
      .then(res => res.json())
      .then(data => {
        this.setState(data);
      })
      .catch(console.log);
  }

  fetchPutLockerFlag = (idGame, x, y) => {
    const requestOptionsPut = {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        idGame: idGame,
        x:x,
        y:y,
        exposed:false,
        question:false,
        flag:true,
        uncheck:false
      })
    };
    fetch("http://localhost:8080/api/lockers", requestOptionsPut)
    .then(res => res.json())
    .then(data => {
      this.setState(data);
    })
    .catch(console.log);
  }

//   fetchPutLocker = (idGame, x, y) => {
//     const requestOptionsPut = {
//       method: 'PUT',
//       headers: { 'Content-Type': 'application/json' },
//       body: JSON.stringify({
//         idGame: idGame,
//         x:x,
//         y:y,
//         exposed:true,
//         question:false,
//         flag:false,
//         uncheck:false
//       })
//     };

//     fetch("http://localhost:8080/api/lockers", requestOptionsPut)
//     .then(res => res.json())
//     .then(data => {
//       this.setState(data);
//     })
//     .catch(console.log);
//   }

  render() {
    return (
      <div className="App">
        
      </div>
    );
  }
}

export default Games;
