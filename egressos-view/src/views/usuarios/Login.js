import React from 'react'
import styles from './Cadastro.module.css'
import { InputText } from 'primereact/inputtext';
import { Checkbox } from 'primereact/checkbox';
import { Col, Row } from 'react-bootstrap';
import { Password } from 'primereact/password';
import { Button } from 'primereact/button';

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
            <div className={styles.mainDiv}>
                <div className="surface-card p-4 shadow-2 border-round w-full lg:w-6">
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

                    <div className="text-center mt-3 mb-3">
                        <Button label="Entrar" icon="pi pi-user" className="w-50" />
                    </div>
                </div>
            </div >
        )
    }
}

export default Login