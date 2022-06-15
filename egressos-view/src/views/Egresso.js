import React from 'react'
import { DataScrollerDepoimentos } from '../components/DataScrollerDepoimentos'
import { Container, Row, Col } from 'react-bootstrap'
import foto_placeholder from '../images/foto_placeholder.png';
import { Button } from 'primereact/button';
import styles from './Egresso.module.css'

class Egresso extends React.Component {
    render() {
        return (
            <div className={styles.mainDiv}>
                <Container className="w-75 py-4">
                    <Row className="align-items-center">
                        <Col className="col-sm-3">
                            <Row>
                                <div className="img-fluid rounded-circle">
                                    <img className="img-fluid rounded-circle bg-white" alt="Card" src={foto_placeholder} onError={(e) => e.target.src = 'https://www.primefaces.org/wp-content/uploads/2020/05/placeholder.png'} />
                                </div>
                            </Row>
                        </Col>
                        <Col className="col-sm-8">
                            <Row className="text-start">
                                <div className={styles.nomeEgresso}>Nome Egresso</div>
                            </Row>
                            <Row className="text-start">
                                <div className={styles.cargoEgresso}>Cargo</div>
                            </Row>
                            <Row className="text-start">
                                <div className={styles.empresaEgresso}>Empresa</div>
                            </Row>
                        </Col>
                        <Col className="col-sm-1">
                            <Row>
                                <div className='botoes-contato-card-egresso'>
                                    <Button className="botao-informacao mx-1 my-1" icon="pi pi-envelope" />
                                </div>
                            </Row>
                            <Row>
                                <div className='botoes-contato-card-egresso'>
                                    <Button className="botao-informacao mx-1 my-1" icon="pi pi-linkedin" />
                                </div>
                            </Row>
                            <Row>
                                <div className='botoes-contato-card-egresso'>
                                    <Button className="botao-informacao mx-1 my-1" icon="pi pi-instagram" />
                                </div>
                            </Row>
                        </Col>
                    </Row>

                    <h1 className='titulo mt-3'>Depoimentos</h1>
                    <DataScrollerDepoimentos />
                </Container>
            </div>
        )
    }
}

export default Egresso