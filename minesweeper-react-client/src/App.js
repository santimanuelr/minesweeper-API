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

    fetch("http://localhost:8080/api/games", requestOptions)
      .then(res => res.json())
      .then(data => {
        this.setState({ lockers: data.lockers, 
          x: data.x, 
          y: data.y, 
          name: data.name 
        });
      })
      .catch(console.log);
  }

  fetchPutLocker() {
    const requestOptionsPut = {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        x:3,
        y:3,
        exposed:true,
        question:false,
        flag:false,
        uncheck:false
      })
    };

    fetch("http://localhost:8080/api/games", requestOptionsPut)
    .then(res => res.json())
    .then(data => {
      this.setState({ lockers: data.lockers, 
        x: data.x, 
        y: data.y, 
        name: data.name 
      });
    })
    .catch(console.log);
  }

  render() {
    return (
      <div className="App">
        <Board lockers={this.state.lockers} onClickLocker = {this.fetchPutLocker}></Board>
      </div>
    );
  }
}

export default App;
