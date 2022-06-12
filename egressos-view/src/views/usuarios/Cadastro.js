import React from 'react'
import './Cadastro.css'
import { InputText } from 'primereact/inputtext';
import { AutoComplete } from 'primereact/autocomplete';
import { Calendar } from 'primereact/calendar';
import { CascadeSelect } from 'primereact/cascadeselect';
import { Chips } from 'primereact/chips';
import { Dropdown } from 'primereact/dropdown';
import { InputMask } from 'primereact/inputmask';
import { InputNumber } from 'primereact/inputnumber';
import { InputTextarea } from 'primereact/inputtextarea';
import { MultiSelect } from 'primereact/multiselect';
import { TreeSelect } from 'primereact/treeselect';
import { Password } from 'primereact/password';
import { Checkbox } from 'primereact/checkbox';
import { Button } from 'primereact/button';
import { StyleClass } from 'primereact/styleclass';

class Cadastro extends React.Component {
    render() {
        return (
            <div className="main-div">
                <div className="surface-card p-4 shadow-2 border-round w-full lg:w-6">
                    <div className="text-center mb-5">
                        <div className="text-900 text-3xl font-medium mb-3">Cadastro</div>
                    </div>

                    <div className='form-div'>
                        <span className="form-item-span p-float-label">
                            <InputText id="inputnome" className="w-full" />
                            <label htmlFor="inputnome">Nome Completo</label>
                        </span>
                        <span className="form-item-span p-float-label">
                            <InputNumber id="inputcpf" mode="decimal" useGrouping={false} className="w-50" />
                            <label htmlFor="inputtext">CPF</label>
                        </span>
                        <span className="form-item-span p-float-label">
                            <InputText id="email" type="text" className="w-full" />
                            <label htmlFor="email">Email</label>
                        </span>
                        <span className="form-item-span p-float-label">
                            <InputTextarea id="textresumo" rows={3} className="w-full" />
                            <label htmlFor="textresumo">Resumo</label>
                        </span>
                        <span className="form-item-span p-float-label">
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