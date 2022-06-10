import React, { Component } from 'react';
import { Card } from 'primereact/card';
import { Button } from 'primereact/button';
import foto_placeholder from '../images/foto_placeholder.png';
import '../App.css';

export class CardFoto extends Component {

    render() {
        const header = (
            <img className='imagem-card-egresso' alt="Card" src={foto_placeholder} onError={(e) => e.target.src = 'https://www.primefaces.org/wp-content/uploads/2020/05/placeholder.png'} />
        );
        const footer = (
            <div className='botoes-contato-card-egresso'>
                <Button icon="pi pi-envelope" />
                <Button icon="pi pi-linkedin" />
            </div>
        );

        return (
            <div className='card-egresso'>
                <Card title="Nome Egresso" style={{ width: '100%', height: '100%' }} subTitle="Cargo" footer={footer} header={header}>
                </Card>
            </div>
        )
    }
}

export default CardFoto;