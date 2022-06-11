import React from 'react'
import { CarrosselEgressos } from '../components/CarrosselEgressos'
import { DataScrollerDepoimentos } from '../components/DataScrollerDepoimentos'
import './Home.css'

class Home extends React.Component {
    render() {
        return (
            <div className='main-div'>
                <div className='secao-home-div'>
                    <h1>Nossos Egressos</h1>
                        <CarrosselEgressos/>
                </div>
                <div className='secao-home-div'>
                    <h1>Depoimentos</h1>
                        <DataScrollerDepoimentos/>
                </div>
            </div>
        )
    }
}

export default Home