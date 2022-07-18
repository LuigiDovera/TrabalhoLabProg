import React, { Component } from 'react';
import { Menubar } from 'primereact/menubar';

import './Components.css';

class MainMenubar extends Component {
    navigateToPage = (path) => {
        this.props.history.push(path);
    }

    constructor(props) {
        super(props);

        this.state = {
            egresso: {
                id: 0,
            },
            items: [
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
                            label: 'Meu Perfil',
                            icon: 'pi pi-fw pi-user',
                            url: `#/Egresso/${this.obterId()}`
                        },
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
                            command: () => { sessionStorage.removeItem('token'); sessionStorage.removeItem('egresso'); },
                            url: '#/Home'
                        }
                    ]
                }
            ]
        }
    }

    obterId = () => {
        let egresso = {}
        try {
            egresso = JSON.parse(sessionStorage.getItem('egresso'));
            if (egresso == null) {
                egresso = { id: 0 };
            }
        }
        catch (e) {
            egresso = { id: 0 };
        }
        return egresso.id;
    }

    render() {
        const start = <h3>Portal de Egressos</h3>;

        return (
            <div className="card">
                <Menubar model={this.state.items} start={start} />
            </div>
        );
    }
}

export default MainMenubar