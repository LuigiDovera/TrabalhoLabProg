import React from 'react';
import styles from './Cadastro.module.css';
//import "primereact/resources/themes/vela-green/theme.css";
import "./Cadastro.css";
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';
import UsuarioService from '../../services/UsuarioService';
import Botao from '../../components/Botao';
import {withRouter} from '../../withRouter';

class Login extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            email: "",
            senha: ""
        };
        this.UsuarioService = new UsuarioService();

        this.handleOnClick = this.handleOnClick.bind(this);
    } // ana@gmail.com

    handleOnClick() {
        this.UsuarioService.logar(this.state.email, this.state.senha)
            .then(response => {
                console.log(response);
                this.props.navigate('/');
            }).catch(error => {
                console.log(error);
                if (error.response.status === 403) {
                    alert("Credenciais incorretas")
                } else  {
                    alert("Erro ao logar")
                }
            }).finally(() => {
                this.setState({
                    email: "",
                    senha: ""
                });
            }
            );
    }

    render() {
        return (

            <div className={`p-4 shadow-2 w-full lg:w-6 ${styles.cardForm}`}>
                <div className="text-center mb-5">
                    <div className="text-900 text-3xl font-medium mb-3">Login</div>
                </div>

                <div className={styles.formDiv}>
                    <span className={`${styles.formItemSpan} p-float-label`}>
                        <InputText id="email" type="text" className="w-full"
                            value={this.state.email}
                            onChange={(e) => this.setState({ email: e.target.value })} />
                        <label htmlFor="email">Email</label>
                    </span>
                    <span className={`${styles.formItemSpan} p-float-label`}>
                        <Password id="inputsenha" className="w-full"
                            value={this.state.senha}
                            onChange={(e) => this.setState({ senha: e.target.value })}
                            feedback={false} />
                        <label htmlFor="inputsenha">Senha</label>
                    </span>
                </div>

                <div className="text-center mt-3 mb-3">
                    <Botao title="Entrar" icon="pi pi-user" className="w-50" onClick={this.handleOnClick} />
                </div>
            </div>


        )
    }
}

export default withRouter(Login)