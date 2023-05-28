import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import ChessBoardComponent from './components/ChessBoardComponent';
import TestPage from './components/TestPage';
import KnightGameComponent from './components/KnightGameComponent';
function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <Routes>
        
        <Route path="/" element = {<ChessBoardComponent/>}/>
        <Route path="/knight" element = {<KnightGameComponent/>}/>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
