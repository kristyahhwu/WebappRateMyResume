import './App.css';
import React from 'react';
import { Router, Switch, Route, Routes, Link } from "react-router-dom";
import Teams from "./Teams";
import Home from "./Home";
import Michael from "./team/Michael";
import Nicholas from "./team/Nicholas";


function App() {
  return (
    <>
      <h1>Welcome to Team04</h1>
      <nav>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/michael">Michael</Link>
          </li>
          <li>
            <Link to="/nicholas">Nicholas</Link>
          </li>
        </ul>
      </nav>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/team" element={<Teams />} />\
        <Route path="/michael" element={<Michael />} />
        <Route path="/nicholas" element={<Nicholas />} /> 
      </Routes>
    </>
  );
}

export default App;
