import React from 'react';
import './App.css';
//import Home from './views/home';
import "primereact/resources/themes/vela-blue/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import MenubarDemo from './components/MainMenubar';

function App() {
  return (
    <div className="App">
        <MenubarDemo />
    </div>
    //<Home>
    //</Home>
  );
}

export default App;
