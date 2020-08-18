import React, { Component } from 'react';
import './App.css';
import Board from './components/Board';
import { API_URL_LOCKERS, API_URL } from './api'

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

    fetch(API_URL, requestOptions)
      .then(res => res.json())
      .then(data => {
        this.setState(data);
      })
      .catch(console.log);
  }

  fetchPutLockerFlag = (idGame, x, y, flag, question, uncheck) => {
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
        uncheck:uncheck
      })
    };
    fetch(API_URL_LOCKERS, requestOptionsPut)
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

    fetch(API_URL_LOCKERS, requestOptionsPut)
    .then(res => res.json())
    .then(data => {
      this.setState(data);
    })
    .catch(console.log);
  }

  render() {
    return (
      <div className="App">
        <a href="/games">Custom Game</a>
        <Board game={this.state} onClickLocker = {this.fetchPutLocker} onClickFlag = {this.fetchPutLockerFlag}></Board>
      </div>
    );
  }
}

export default App;
