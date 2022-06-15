import React from 'react'
import styles from './Cadastro.module.css'
import { InputText } from 'primereact/inputtext';
import { Checkbox } from 'primereact/checkbox';
import { Col, Row } from 'react-bootstrap';
import { Password } from 'primereact/password';
import { Button } from 'primereact/button';
import Botao from '../../components/Botao';

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: null,
            senha: null
        };
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
                            onChange={(e) => this.setState({ senha: e.target.value })} />
                        <label htmlFor="inputsenha">Senha</label>
                    </span>
                </div>

                <div className="text-center mt-3 mb-3">
                    <Botao title="Entrar" icon="pi pi-user" className="w-50" />
                </div>
            </div>

        )
    }
}

export default Login