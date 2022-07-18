import React from 'react'
import styles from './Cadastro.module.css'
//import "primereact/resources/themes/vela-green/theme.css";
import "./Cadastro.css";
import { InputText } from 'primereact/inputtext';
import { InputTextarea } from 'primereact/inputtextarea';
import { Password } from 'primereact/password';
import Botao from '../../components/Botao';
import UsuarioService from '../../services/UsuarioService';
import { withRouter } from '../../withRouter';
import EgressoService from '../../services/EgressoService';

class Editar extends React.Component {
    constructor(props) {
        super(props);
        this.egressoService = new EgressoService();
        this.state = {
            nome: "",
            cpf: "",
            email: "",
            resumo: "",
            senha: ""
        };
        this.usuarioService = new UsuarioService();

        this.onlyDigitsHandle = this.onlyDigitsHandle.bind(this);
        this.handleOnClick = this.handleOnClick.bind(this);
        this.carregarEgresso = this.carregarEgresso.bind(this);
    }

    onlyDigitsHandle(event) {
        const result = event.target.value.replace(/\D/g, '');
        this.setState({ cpf: result });
    };

    handleOnClick() {
        let egresso = {
            nome: this.state.nome,
            cpf: this.state.cpf,
            email: this.state.email,
            resumo: this.state.resumo,
            senha: this.state.senha
        }
        let token = sessionStorage.getItem('token');
        this.usuarioService.atualizar(egresso, token).then(response => {
            egresso = response.data;
            this.setState({
                egresso: egresso
            });
            alert("Cadastro atualizado com sucesso");
            console.log(this.state.egresso);
            this.props.navigate(`../Egresso/${this.state.egresso.id}`);
        }).catch(error => {
            console.log(error);
            alert("Erro ao atualizar o cadastro: " + error.message);
        }).finally(() => {
            sessionStorage.setItem('egresso', JSON.stringify(this.state.egresso));
        });
    }

    carregarEgresso(id) {
        this.egressoService.obterEgressoPorId(id)
            .then(response => {
                //console.log(response.data);
                this.setState({ egresso: response.data });
                this.setState({ nome: response.data.nome });
                this.setState({ cpf: response.data.cpf });
                this.setState({ email: response.data.email });
                this.setState({ resumo: response.data.resumo });
            }).catch(error => {
                console.log(error);
            });

    }

    componentDidMount() {
        const { id } = this.props.params;
        this.carregarEgresso(id);
    }

    render() {
        return (
            <div className={`p-4 shadow-2 w-full lg:w-6 ${styles.cardForm}`}>
                <div className="text-center mb-5">
                    <div className="text-900 text-3xl font-medium mb-3">Editar Cadastro</div>
                </div>

                <div className={styles.formDiv}>
                    <span className={`${styles.formItemSpan} p-float-label`}>
                        <InputText id="inputnome" className="w-full"
                            value={this.state.nome}
                            onChange={(e) => this.setState({ nome: e.target.value })} />
                        <label htmlFor="inputnome">Nome Completo</label>
                    </span>
                    <span className={`${styles.formItemSpan} p-float-label`}>
                        <InputText id="inputcpf" mode="decimal" className="w-50"
                            value={this.state.cpf}
                            onChange={(e) => this.onlyDigitsHandle(e)} />
                        <label htmlFor="inputtext">CPF</label>
                    </span>
                    <span className={`${styles.formItemSpan} p-float-label`}>
                        <InputText id="email" type="text" className="w-full"
                            value={this.state.email}
                            onChange={(e) => this.setState({ email: e.target.value })} />
                        <label htmlFor="email">Email</label>
                    </span>
                    <span className={`${styles.formItemSpan} p-float-label`}>
                        <InputTextarea id="textresumo" rows={3} className="w-full"
                            value={this.state.resumo}
                            onChange={(e) => this.setState({ resumo: e.target.value })} />
                        <label htmlFor="textresumo">Resumo</label>
                    </span>

                    <span className={`${styles.formItemSpan} p-float-label`}>
                        <Password id="inputsenha" className="w-full"
                            value={this.state.senha}
                            onChange={(e) => this.setState({ senha: e.target.value })} />
                        <label htmlFor="inputsenha">Senha</label>
                    </span>

                    <Botao title="Atualizar" icon="pi pi-user" className="w-50" onClick={this.handleOnClick} />
                </div>
            </div>

        )
    }
}

export default withRouter(Editar)