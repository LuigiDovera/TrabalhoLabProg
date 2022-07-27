import React from 'react'
import { DataScrollerDepoimentos } from '../components/DataScrollerDepoimentos'
import { Container, Row, Col } from 'react-bootstrap'
import foto_placeholder from '../images/foto_placeholder.png';
import { Button } from 'primereact/button';
import styles from './Egresso.module.css'
import EgressoService from '../services/EgressoService';
import CargoService from '../services/CargoService';
import { withRouter } from '../withRouter';
import Botao from '../components/Botao';
import { InputTextarea } from 'primereact/inputtextarea';

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
            contatos: {},
            contatosBloco: [],
            depoimento_txt: "",

            isLoading: true,
        }

        this.carregarEgresso = this.carregarEgresso.bind(this);
        this.editarClickHandler = this.editarClickHandler.bind(this);
        this.salvarClickHandler = this.salvarClickHandler.bind(this);
    }

    carregarEgresso(id) {
        this.egressoService.obterEgressoPorId(id)
            .then(response => {
                console.log(response.data);

                this.setState({ egresso: response.data });

                //TODO: Adicionar um order by no retorno de profissões, para o ulitmo sempre ser a profissão mais recente
                try {
                    this.setState({ ultimoProfEgresso: response.data.profsEgressos[response.data.profsEgressos.length - 1] });
                    this.state({ contatos: response.data.contatos });
                    Promise.resolve(this.cargoService.obterCargoPorIdProfEgresso(response.data.profsEgressos[response.data.profsEgressos.length - 1].id))
                        .then(response => {
                            this.setState({ cargo: response.data });
                        }).catch(error => {
                            console.log(error);
                        });
                } catch (error) {
                    this.setState({ ultimoProfEgresso: { empresa: "" } });
                    this.setState({ contatos: { nome: "" } });
                    this.setState({ cargo: "" });
                }

                //Contatos
                try {
                    let contatos = response.data.contatos;
                    contatos.forEach(contato => {
                        let contatoIcone;
                        if (contato.nome === "Facebook") {
                            contatoIcone = "pi pi-facebook";
                        } else if (contato.nome === "Linkedin") {
                            contatoIcone = "pi pi-linkedin";
                        } else if (contato.nome === "Instagram") {
                            contatoIcone = "pi pi-instagram";
                        }

                        let contatoBotao =
                            <Row>
                                <div className='botoes-contato-card-egresso'>
                                    <Button className="botao-informacao mx-1 my-1" icon={`${contatoIcone}`} />
                                </div>
                            </Row>;

                        this.state.contatosBloco.push(contatoBotao);
                    });

                    //Evitando duplicações de contatos
                    if (response.data.contatos.length < this.state.contatosBloco.length) {
                        this.setState({ contatosBloco: this.state.contatosBloco.slice(0, response.data.contatos.length) });
                    }

                }
                catch (error) {
                    console.log(error);
                }

                //Depoimentos
                let depoimentos = response.data.depoimentos;
                depoimentos.forEach(depoimento => {
                    depoimento.nome = response.data.nome;
                    depoimento.contatos = response.data.contatos;
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

    editarClickHandler() {
        if (sessionStorage.getItem('token') === null) {
            //this.props.navigate(`/Editar/${this.state.egresso.id}`);
            this.props.navigate('/Login');
        } else {
            this.props.navigate(`/Editar/${this.state.egresso.id}`);
        }
    }

    salvarClickHandler() {
        if ("egresso" in sessionStorage && this.state.egresso.id == JSON.parse(sessionStorage.getItem('egresso')).id) {
            let depoimento = {
                texto: this.state.depoimento_txt
            };
            let egresso = this.state.egresso;
            egresso.depoimentos.push(depoimento);
            for (let index = 0; index < egresso.depoimentos.length; index++) {
                try {
                    let diaMesAno = egresso.depoimentos[index].data.split("/");
                    egresso.depoimentos[index].data = diaMesAno[2] + "-" + diaMesAno[1] + "-" + diaMesAno[0];
                    //console.log(diaMesAno);
                } catch (e) {

                }
            }
            //console.log(egresso)
            let token = sessionStorage.getItem("token");
            this.egressoService.atualizarDepoimentos(egresso, token).then(response => {
                sessionStorage.setItem("egresso", JSON.stringify(response.data));
                this.carregarEgresso(response.data.id);
                this.setState({ depoimento_txt: "" })
            }).catch(error => {
                console.log(error);
            });
        } else {
            this.props.navigate('/Cadastro');
        }
    }

    componentDidMount() {
        const { id } = this.props.params
        if (id == 0) {
            this.props.navigate('/Cadastro');
        } else {
            this.carregarEgresso(id);
        }
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
                            <Row>
                                <div className="text-center ">
                                    <Botao title="Editar" icon="pi pi-pencil" onClick={this.editarClickHandler} />
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
                            {this.state.contatosBloco}
                        </Col>
                    </Row>

                    <h1 className='titulo mt-3'>Depoimentos</h1>
                    <InputTextarea id="textresumo" rows={3} className="w-full"
                        value={this.state.depoimento_txt} placeholder="Escreva seu depoimento"
                        onChange={(e) => this.setState({ depoimento_txt: e.target.value })} />
                    <Botao title="Salvar" onClick={this.salvarClickHandler} />
                    <DataScrollerDepoimentos depoimentos={this.state.depoimentos} />

                </Container>
            </div>
        )
    }
}

export default withRouter(Egresso)