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

class Cadastro extends React.Component {
    constructor(props) {
        super(props);
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
    }

    onlyDigitsHandle(event) {
        const result = event.target.value.replace(/\D/g, '');
        this.setState({ cpf: result });
    };

    handleOnClick() {
        this.usuarioService.cadastrar(this.state).then(response => {
            sessionStorage.setItem('egresso', JSON.stringify(response.data));
            this.usuarioService.logar(this.state.email, this.state.senha).then(response => {
                let token = response.data.Authorization;
                sessionStorage.setItem('token', token);
                alert("Cadastro realizado com sucesso");
                this.props.navigate('/');
            });
        }).catch(error => {
            alert("Erro ao realizar o cadastro: " + error.message);
            sessionStorage.removeItem('egresso');
        });
    }

    render() {
        return (
            <div className={`p-4 shadow-2 w-full lg:w-6 ${styles.cardForm}`}>
                <div className="text-center mb-5">
                    <div className="text-900 text-3xl font-medium mb-3">Cadastro</div>
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
                    <Botao title="Cadastrar" icon="pi pi-user" className="w-50" onClick={this.handleOnClick} />
                </div>
            </div>

        )
    }
}

export default withRouter(Cadastro)