import React, { Component } from 'react';
import { Carousel } from 'primereact/carousel';
import { Card } from 'primereact/card';
import { Button } from 'primereact/button';
import foto_placeholder from '../images/foto_placeholder.png';
import './Components.css';

export class CarrosselEgressos extends Component {

    constructor(props) {
        super(props);

        this.state = {
            egressos: []
        };

        this.responsiveOptions = [
            {
                breakpoint: '1024px',
                numVisible: 3,
                numScroll: 3
            },
            {
                breakpoint: '600px',
                numVisible: 2,
                numScroll: 2
            },
            {
                breakpoint: '480px',
                numVisible: 1,
                numScroll: 1
            }
        ];

        this.cardTemplate = this.cardTemplate.bind(this);
    }

    componentDidMount() {
        this.setState({ egressos: [1, 2, 3, 4, 5, 6, 7, 8, 9] });
    }


    cardTemplate(cardEgresso) {
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

    render() {
        return (
            <div className="carossel-egressos-div">
                <Carousel value={this.state.egressos} numVisible={3} numScroll={1} responsiveOptions={this.responsiveOptions} className="carrossel-egressos"
                    circular={true} autoplayInterval={3000} itemTemplate={this.cardTemplate} />
            </div>
        );
    }
}