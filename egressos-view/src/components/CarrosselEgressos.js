import React, { Component } from 'react';
import { Carousel } from 'primereact/carousel';
import { Card } from 'primereact/card';
import { Button } from 'primereact/button';
import { Row } from 'react-bootstrap';
import foto_placeholder from '../images/foto_placeholder.png';
import { Link } from "react-router-dom";    
import './Components.css';

export class CarrosselEgressos extends Component {

    constructor(props) {
        super(props);

        this.state = {
            egressos: this.props.egressos
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
        //console.log(this.state.egressos);
    }


    cardTemplate(egresso) {

        const header = (

            <Link to={`../Egresso/${egresso.id}`} className="botao-informacao card-egresso" style={{ textDecoration: 'none' }}>
                <img className='imagem-card-egresso' alt="Card" src={foto_placeholder} onError={(e) => e.target.src = 'https://www.primefaces.org/wp-content/uploads/2020/05/placeholder.png'} />
            </Link>
        );
        
        const footer = (
            <Row>
                <div>
                    <Button className="botao-informacao mx-1" icon="pi pi-envelope" />
                    <Button className="botao-informacao mx-1" icon="pi pi-linkedin" />
                    <Button className="botao-informacao mx-1" icon="pi pi-instagram" />
                </div>
            </Row>
        );

        return ( 
            <Card className='card-egresso mx-4' title={egresso.nome} subTitle={egresso.cargo} footer={footer} header={header} ></Card>
        )
    }

    render() {

        return (
            <Carousel value={this.state.egressos} numVisible={3} numScroll={1} responsiveOptions={this.responsiveOptions}
                className="carrossel-egressos mt-3 mb-3" circular={true} autoplayInterval={5000} itemTemplate={this.cardTemplate} />
        );
    }
}