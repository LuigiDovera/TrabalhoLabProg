import React from 'react'
import { CarrosselEgressos } from '../components/CarrosselEgressos'
import { DataScrollerDepoimentos } from '../components/DataScrollerDepoimentos'
import styles from './Home.module.css'

class Home extends React.Component {
    render() {
        return (
            <div>
                <div className={styles.secaoHomeDiv}>
                    <h1 className='titulo mt-3'>Nossos Egressos</h1>
                        <CarrosselEgressos/>
                </div>
                <div className={styles.secaoHomeDiv}>
                    <h1 className='titulo mt-3'>Depoimentos</h1>
                        <DataScrollerDepoimentos/>
                </div>
            </div>
        )
    }
}

export default Home