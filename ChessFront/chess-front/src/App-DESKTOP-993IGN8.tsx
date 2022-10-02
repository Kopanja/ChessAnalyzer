import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import TestPage from './components/TestPage';
function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <Routes>
        <Route path="/" element = {<TestPage/>}/>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
