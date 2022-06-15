import React from 'react';
import styles from './Cadastro.module.css';
//import "primereact/resources/themes/vela-green/theme.css";
import "./Cadastro.css";
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';
import { Checkbox } from 'primereact/checkbox';
import Botao from '../../components/Botao';
import {Row, Col} from 'react-bootstrap';

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
                        <Row className="col-sm-12">
                            <Col>
                                <Checkbox className="mx-1" inputId="cb1" value="Guardar Senha" onChange={() => { }} checked={() => { }}></Checkbox>
                                <label htmlFor="cb1" className={`${styles.labelSenha} p-checkbox-label`}>Lembrar de mim</label>
                            </Col>
                            <Col>
                                <label htmlFor="cb1" className={`${styles.labelSenha} p-checkbox-label`}>Esqueceu sua senha?</label>
                            </Col>
                        </Row>
                    </div>
                    <Botao title="Cadastrar" icon="pi pi-user"/>
                </div>
        )
    }
}

export default Login