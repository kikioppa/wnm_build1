import logo from './logo.svg';
import './App.css';
import React, {useState, useEffect} from 'react';

function App() {
  const [message, setMessage] = useState("");
  useEffect(() => {
    fetch('/hello')
        .then(response => response.text())
        .then(message => {
          setMessage(message);
        });
  },[])



  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">{message}</h1>
      </header>
    </div>
  );
}

export default App;
