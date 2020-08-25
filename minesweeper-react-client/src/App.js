import React, { Component } from 'react';
import './App.css';
import Board from './components/Board';
import {API_URL, putLockerActions, fetchPutLocker} from './api'

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

  fetchPutLockerFlag = async (idGame, x, y, flag, question, uncheck) => {
    const response = await putLockerActions(idGame, x, y, flag, question, uncheck)
    this.setState(response);
  }

  fetchPutLocker = async (idGame, x, y) => {
    let result = await fetchPutLocker(idGame, x, y);
    this.setState(result);
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
