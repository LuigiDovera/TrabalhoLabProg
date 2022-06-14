import React from 'react';
import './App.css';
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import { MainMenubar } from './components/MainMenubar';
import Rotas from './components/Rotas';

function App() {
    return (
        <div className="App">
            <MainMenubar />
            <Rotas />
        </div>
    );
}

export default App;
