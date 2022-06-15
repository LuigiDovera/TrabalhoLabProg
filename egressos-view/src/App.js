import React from 'react';
import styles from './App.module.css'
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import "primeflex/primeflex.css";
import { MainMenubar } from './components/MainMenubar';
import Rotas from './components/Rotas';

function App() {
    return (

        <div className={styles.App}>
            <MainMenubar />
            <div className={styles.mainDiv}>
                <Rotas />
            </div>
        </div>
    );
}

export default App;
