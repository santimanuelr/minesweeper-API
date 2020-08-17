import React, { Component } from 'react';
import logo from './logo.svg';
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

  render() {
    return (
      <div className="App">
        {/* <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Edit <code>src/App.js</code> and save to reload.
          </p>
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer" 
          >
            Learn React
          </a>
        </header> */}
        <Board lockers={this.state.lockers}></Board>
      </div>
    );
  }
}

export default App;
