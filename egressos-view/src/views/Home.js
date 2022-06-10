import React from 'react'
import CardFoto from '../components/CardFoto'
import '../App.css'

class Home extends React.Component {
    render() {
        return (
            <div className='main-div'>
                <div className='secao-home-div'>
                    <h1>Nossos Egressos</h1>
                    <div className='cards-egressos'>
                        <CardFoto />
                        <CardFoto />
                        <CardFoto />
                    </div>
                </div>
            </div>
        )
    }
}

export default Home