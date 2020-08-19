import React, { Component } from 'react';
import './../App.css';
import './CustomGames.css';
import Board from '../components/Board';
import { API_URL_LOCKERS, API_URL } from '../api'

class Games extends Component {

  state = { };

  newCustomGameHandler = (event) => {
    event.preventDefault();
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        "x": this.state.x,
        "y": this.state.y,
        "minesCount": this.state.mines,
        "name": this.state.name
      })
    };

    fetch(API_URL, requestOptions)
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
  
  handleChange = (event) => {
    const value = event.target.value;
    this.setState({
      ...this.state,
      [event.target.name]: value
    });
  }

  render() {
    return (
      <div className="App">
        {!!this.state &&
          <form onSubmit={this.newCustomGameHandler}>
            <input placeholder="rows" type="number" min="9" name="y" value={this.state.y} onChange={this.handleChange}></input>
            <input placeholder="columns" type="number" min="9" name="x" value={this.state.x} onChange={this.handleChange}></input>
            <input placeholder="name" type="text" name="name" value={this.state.name} onChange={this.handleChange}></input>
            <input placeholder="mines" type="number" min="3" max={(this.state.x-1) * (this.state.y-1)} name="mines" value={this.state.mines} onChange={this.handleChange}></input>
            <input type="submit" value="Submit" />
          </form>
        }
        {this.state && this.state.gameStatus &&
          <>
          <Board game={this.state} onClickLocker = {this.fetchPutLocker} onClickFlag = {this.fetchPutLockerFlag}></Board>
          <a href="/">Default Game</a>
          </>
        }
      </div>
    );
  }
}

export default Games;
