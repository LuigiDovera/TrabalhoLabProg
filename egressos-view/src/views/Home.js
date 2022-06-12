import React from 'react'
import { CarrosselEgressos } from '../components/CarrosselEgressos'
import { DataScrollerDepoimentos } from '../components/DataScrollerDepoimentos'
import styles from './Home.module.css'

class Home extends React.Component {
    render() {
        return (
            <div className={styles.mainDiv}>
                <div className={styles.secaoHomeDiv}>
                    <h1>Nossos Egressos</h1>
                        <CarrosselEgressos/>
                </div>
                <div className={styles.secaoHomeDiv}>
                    <h1>Depoimentos</h1>
                        <DataScrollerDepoimentos/>
                </div>
            </div>
        )
    }
}

export default Home