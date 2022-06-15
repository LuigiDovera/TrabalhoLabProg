import React, { Component } from 'react';
import { Menubar } from 'primereact/menubar';

import './Components.css';

export class MainMenubar extends Component {
    navigateToPage = (path) => {
		this.props.history.push(path);
	}
    
    constructor(props) {
        super(props);

        this.items = [
            {
                label: 'Home',
                icon: 'pi pi-fw pi-home',
                url: '#/Home'
            },
            {
                label: 'Depoimentos',
                icon: 'pi pi-fw pi-pencil',
                url: '#/Depoimentos'
            },
            {
                label: 'Informações',
                icon: 'pi pi-fw pi-file',
                url: '#/Informacoes'
            },
            {
                label: 'Usuários',
                icon: 'pi pi-fw pi-users',
                items: [
                    {
                        label: 'Cadastro',
                        icon: 'pi pi-fw pi-user-edit',
                        url: '#/Cadastro'
                    },
                    {
                        label: 'Login',
                        icon: 'pi pi-fw pi-sign-in',
                        url: '#/Login'
                    },
                    {
                        label: 'Logout',
                        icon: 'pi pi-fw pi-sign-out',
                    }
                ]
            }
        ];
    }

    render() {
        const start = <h3>Portal de Egressos</h3>;

        return (
            <div className="card">
                <Menubar model={this.items} start={start}/>
            </div>
        );
    }
}