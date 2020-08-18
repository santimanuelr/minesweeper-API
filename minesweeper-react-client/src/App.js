import React, { Component } from 'react';
import './App.css';
import Board from './components/Board';

class App extends Component {

  state = {
    lockers: Array(9).fill().map(()=>Array(9).fill()),
    x: 0,
    y: 0,
    name: ""
  };

  componentDidMount() {
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        "lockers": [],
        "minesCount": 0,
        "name": "string"
      })
    };

    fetch("https://minesweeper-java-api.herokuapp.com/api/games", requestOptions)
      .then(res => res.json())
      .then(data => {
        this.setState(data);
      })
      .catch(console.log);
  }

  fetchPutLockerFlag = (idGame, x, y, flag, question) => {
    const requestOptionsPut = {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        idGame: idGame,
        x:x,
        y:y,
        exposed:false,
        question:question,
        flag:flag,
        uncheck:false
      })
    };
    fetch("https://minesweeper-java-api.herokuapp.com/api/lockers", requestOptionsPut)
    .then(res => res.json())
    .then(data => {
      this.setState(data);
    })
    .catch(console.log);
  }

  fetchPutLocker = (idGame, x, y) => {
    const requestOptionsPut = {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        idGame: idGame,
        x:x,
        y:y,
        exposed:true,
        question:false,
        flag:false,
        uncheck:false
      })
    };

    fetch("https://minesweeper-java-api.herokuapp.com/api/lockers", requestOptionsPut)
    .then(res => res.json())
    .then(data => {
      this.setState(data);
    })
    .catch(console.log);
  }

  render() {
    return (
      <div className="App">
        <Board game={this.state} onClickLocker = {this.fetchPutLocker} onClickFlag = {this.fetchPutLockerFlag}></Board>
      </div>
    );
  }
}

export default App;
