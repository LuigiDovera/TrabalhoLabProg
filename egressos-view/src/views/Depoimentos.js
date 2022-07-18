import React from 'react';
import { Row, Col } from 'react-bootstrap';
import Botao from '../components/Botao';
import { DataScrollerDepoimentos } from '../components/DataScrollerDepoimentos';
import styles from './Home.module.css';
import { Link } from "react-router-dom";  

import EgressoService from '../services/EgressoService';
import DepoimentoService from '../services/DepoimentoService';
import { withRouter } from '../withRouter';

class Depoimento extends React.Component {

    constructor(props) {
        super(props);
        this.egressoService = new EgressoService();
        this.depoimentoService = new DepoimentoService();
        console.log(this.depoimentoService)

        this.state = {
            depoimentos: {},

            isLoading: true,
            depoimentoRecente: this.props.depoimentoRecente == null ? true : this.props.depoimentoRecente,
        }

        this.carregarDepoimentos = this.carregarDepoimentos.bind(this);
        //this.mudarOrdemDepoimentos = this.mudarOrdemDepoimentos.bind(this);
    }

    carregarDepoimentos(){
        let obter;
        if (this.state.depoimentoRecente) {
            obter = this.depoimentoService.obterDepoimentosMaisRecentes()
                .then(response => {
                    this.setState({ depoimentos: response.data });
                    this.setState({ isLoading: false });
                }).catch(error => {
                    console.log(error);
                });
        } else {
            obter = this.depoimentoService.obterDepoimentosMaisAntigos()
                .then(response => {
                    this.setState({ depoimentos: response.data });
                    this.setState({ isLoading: false });
                }).catch(error => {
                    console.log(error);
                });
        }   
    }

    /*
     Ordem: true - Mais Recente
            false - Mais Antigo
    */
   /*
    mudarOrdemDepoimentos(ordem){
        
        if (ordem != this.state.depoimentoRecente) {
            this.setState({ depoimentos: this.state.depoimentos.reverse() });
            this.setState({ depoimentoRecente: !this.state.depoimentoRecente });
        }
    }
    */

    componentDidMount(){
        this.carregarDepoimentos();
    }

    render() {

        const isLoading = this.state.isLoading;
        if (isLoading) {
            return <div className="App">Loading...</div>;
        }

        return (
            <div className={styles.mainDiv}>
                <div className={styles.secaoDiv}>
                    <h1 className='titulo mt-3'>Depoimentos</h1>
                    <Row className='text-end'>
                        <Col>
                            <Link to={`../Depoimentos/MaisRecente`}>
                                <Botao className="mx-2" title="Mais Recentes" icon="pi pi-user"/>
                            </Link>
                            <Link to={`../Depoimentos/MaisAntigo`}>
                                <Botao className="mx-2" title="Mais Antigos" icon="pi pi-user"/>
                            </Link>
                        </Col>
                    </Row>
                    <DataScrollerDepoimentos depoimentos={this.state.depoimentos}/>
                </div>
            </div>
        )
    }
}

export default withRouter(Depoimento)