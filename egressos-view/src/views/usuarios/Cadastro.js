import React from 'react'
import styles from './Cadastro.module.css'
import { InputText } from 'primereact/inputtext';
import { InputNumber } from 'primereact/inputnumber';
import { InputTextarea } from 'primereact/inputtextarea';
import { Password } from 'primereact/password';
import { Button } from 'primereact/button';

class Cadastro extends React.Component {
    render() {
        return (
            <div className={styles.mainDiv}>
                <div className="surface-card p-4 shadow-2 border-round w-full lg:w-6">
                    <div className="text-center mb-5">
                        <div className="text-900 text-3xl font-medium mb-3">Cadastro</div>
                    </div>

                    <div className={styles.formDiv}>
                        <span className={`${styles.formItemSpan} p-float-label`}>
                            <InputText id="inputnome" className="w-full" />
                            <label htmlFor="inputnome">Nome Completo</label>
                        </span>
                        <span className={`${styles.formItemSpan} p-float-label`}>
                            <InputNumber id="inputcpf" mode="decimal" useGrouping={false} className="w-50" />
                            <label htmlFor="inputtext">CPF</label>
                        </span>
                        <span className={`${styles.formItemSpan} p-float-label`}>
                            <InputText id="email" type="text" className="w-full" />
                            <label htmlFor="email">Email</label>
                        </span>
                        <span className={`${styles.formItemSpan} p-float-label`}>
                            <InputTextarea id="textresumo" rows={3} className="w-full" />
                            <label htmlFor="textresumo">Resumo</label>
                        </span>
                        <span className={`${styles.formItemSpan} p-float-label`}>
                            <Password id="inputsenha"  className="w-full" />
                            <label htmlFor="inputsenha">Senha</label>
                        </span>
                        <Button label="Cadastrar" icon="pi pi-user" className="w-full" />
                    </div>
                </div>
            </div>
        )
    }
}

export default Cadastro