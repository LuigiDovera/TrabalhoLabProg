import React, { Component } from 'react';
import { Menubar } from 'primereact/menubar';

export class MainMenubar extends Component {
    constructor(props) {
        super(props);

        this.items = [
            {
                label: 'Home',
                icon: 'pi pi-fw pi-home',
            },
            {
                label: 'Depoimentos',
                icon: 'pi pi-fw pi-pencil',
            },
            {
                label: 'Informações',
                icon: 'pi pi-fw pi-file',
            },
            {
                label: 'Usuário',
                icon: 'pi pi-fw pi-users',
                items: [
                    {
                        label: 'Cadastro',
                        icon: 'pi pi-fw pi-user-edit',
                    },
                    {
                        label: 'Login',
                        icon: 'pi pi-fw pi-sign-in',
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
            <div>
                <div className="card">
                    <Menubar model={this.items} start={start} />
                </div>
            </div>
        );
    }
}

export default MainMenubar;