import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from 'react-router-dom';
import RCService from './context/RCService';
import RCServices from "./services/rc-service"

const root = ReactDOM.createRoot(document.getElementById('root'));
const rcService = new RCServices;

root.render(
  <React.StrictMode>
    <BrowserRouter>
      <RCService.Provider value={ rcService }>
        <App />
      </RCService.Provider>
    </BrowserRouter>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
