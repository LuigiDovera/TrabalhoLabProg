import React from 'react'
import { DataScrollerDepoimentos } from '../components/DataScrollerDepoimentos'
import { Container, Row, Col } from 'react-bootstrap'
import foto_placeholder from '../images/foto_placeholder.png';
import { Button } from 'primereact/button';
import styles from './Egresso.module.css'
import EgressoService from '../services/EgressoService';
import CargoService from '../services/CargoService';
import { withRouter } from '../withRouter';

class Egresso extends React.Component {

    constructor(props) {
        super(props);
        this.egressoService = new EgressoService();
        this.cargoService = new CargoService();

        this.state = {
            egresso: {},
            ultimoProfEgresso: {},
            cargo: {},
            depoimentos: {},

            isLoading: true,
        }

        this.carregarEgresso = this.carregarEgresso.bind(this);
    }

    carregarEgresso(id) {
        this.egressoService.obterEgressoPorId(id)
            .then(response => {
                console.log(response.data);

                this.setState({ egresso: response.data });

                //TODO: Adicionar um order by no retorno de profissões, para o ulitmo sempre ser a profissão mais recente
                try {
                    this.setState({ ultimoProfEgresso: response.data.profsEgressos[response.data.profsEgressos.length - 1] });
                    Promise.resolve(this.cargoService.obterCargoPorIdProfEgresso(response.data.profsEgressos[response.data.profsEgressos.length - 1].id))
                        .then(response => {
                            this.setState({ cargo: response.data });
                        }).catch(error => {
                            console.log(error);
                        });
                } catch (error) {
                    this.setState({ ultimoProfEgresso: { empresa: "" } });
                    this.setState({ cargo: ""  });
                }

                let depoimentos = response.data.depoimentos;
                depoimentos.forEach(depoimento => {
                    depoimento.nome = response.data.nome;
                });
                depoimentos.forEach(depoimento => {
                    depoimento.data = depoimento.data.split('-').reverse().join('/');
                });

                this.setState({ depoimentos: depoimentos });

                this.setState({ isLoading: false });


            }).catch(error => {
                console.log(error);
            });

    }



    componentDidMount() {
        const { id } = this.props.params
        this.carregarEgresso(id);

    }

    render() {
        const isLoading = this.state.isLoading;
        if (isLoading) {
            return <div className="App">Loading...</div>;
        }

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
                                <div className={styles.nomeEgresso}>{this.state.egresso.nome}</div>
                            </Row>
                            <Row className="text-start">
                                <div className={styles.cargoEgresso}>{this.state.cargo.nome}</div>
                            </Row>
                            <Row className="text-start">
                                <div className={styles.empresaEgresso}>{this.state.ultimoProfEgresso.empresa}</div>
                            </Row>
                            <Row className="text-start">
                                <div className={styles.resumoEgresso}>{this.state.egresso.resumo}</div>
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
                    <DataScrollerDepoimentos depoimentos={this.state.depoimentos} />

                </Container>
            </div>
        )
    }
}

export default withRouter(Egresso)