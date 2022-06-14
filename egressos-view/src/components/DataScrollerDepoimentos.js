
import React, { Component } from 'react';
import { DataScroller } from 'primereact/datascroller';
import { Button } from 'primereact/button';
import { Container, Row, Col } from 'react-bootstrap';
import './Components.css';
import 'bootstrap/dist/css/bootstrap.min.css';


export class DataScrollerDepoimentos extends Component {

    constructor(props) {
        super(props);

        this.state = {
            depoimentos: []
        };

        this.depoimentoTemplate = this.depoimentoTemplate.bind(this);
    }

    componentDidMount() {
        this.setState({ depoimentos: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10] });
    }

    depoimentoTemplate(data) {
        return (
            <Container className="mt-3 mb-3 px-5 py-3 card-depoimento">
                <Row>
                    <Col className="col-sm-11">
                        <p className="texto-depoimento my-2">
                            Lorem ipissão Lorem ipissão Lorem ipissão Lorem ipissão Lorem ipissão
                            Lorem ipissão Lorem ipissão Lorem ipissão Lorem ipissão Lorem ipissão
                            Lorem ipissão Lorem ipissão Lorem ipissão Lorem ipissão Lorem ipissão
                            Lorem ipissão Lorem ipissão Lorem ipissão Lorem ipissão Lorem ipissão
                        </p>
                    </Col>
                    <Col className="col-sm-1">
                        <i className="pi pi-comment" />
                    </Col>
                </Row>

                <Row>
                    <Col className="col-sm-10">
                        <Row>
                            <p className="nome-depoimento my-0">Nome Egresso</p>
                        </Row>
                        <Row>
                            <p className="date-depoimento my-0">Dezembro, 2020</p>
                        </Row>
                    </Col>
                    <Col className="col-sm-2">
                        <Row>
                            <div className='botoes-contato-card-egresso'>
                                <Button className="botao-informacao mx-1 my-1" icon="pi pi-envelope" />
                                <Button className="botao-informacao mx-1 my-1" icon="pi pi-linkedin" />
                                <Button className="botao-informacao mx-1 my-1" icon="pi pi-instagram" />
                            </div>
                        </Row>
                    </Col>
                </Row>
            </Container >
        );
    }

    render() {
        const footer = <Button type="text" icon="pi pi-plus" label="Mostrar mais" onClick={() => this.ds.load()} />;

        return (
            <div className="datascroller-depoimentos mt-3 mb-3">
                <DataScroller ref={(el) => this.ds = el} value={this.state.depoimentos} itemTemplate={this.depoimentoTemplate} rows={3}
                    loader footer={footer} emptyMessage="Sem depoimentos" />
            </div>
        );
    }
}
