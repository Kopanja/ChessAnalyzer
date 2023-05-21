import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import ChessBoardComponent from './components/ChessBoardComponent';
import TestPage from './components/TestPage';
function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <Routes>
        
        <Route path="/" element = {<ChessBoardComponent/>}/>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
