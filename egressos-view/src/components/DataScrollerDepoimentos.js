
import React, { Component } from 'react';
import { DataScroller } from 'primereact/datascroller';
import { Button } from 'primereact/button';
import './Components.css';

export class DataScrollerDepoimentos extends Component {

    constructor(props) {
        super(props);

        this.state = {
            depoimentos: []
        };

        this.depoimentoTemplate = this.depoimentoTemplate.bind(this);
    }

    componentDidMount() {
        this.setState({ depoimentos: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10] });
    }

    depoimentoTemplate(data) {
        return (
            <div className="card-depoimento">
                <div className="citacao-depoimento">
                    <p className="texto-depoimento">Lorem ipissão Lorem ipissão Lorem ipissão Lorem ipissão Lorem ipissão</p>
                    <i className="pi pi-comment" />
                </div>
                <p className="nome-depoimento">Nome Egresso</p>
            </div>
        );
    }

    render() {
        const footer = <Button type="text" icon="pi pi-plus" label="Mostrar mais" onClick={() => this.ds.load()} />;

        return (
            <div className="datascroller-depoimentos">
                <div className="card">
                    <DataScroller ref={(el) => this.ds = el} value={this.state.depoimentos} itemTemplate={this.depoimentoTemplate} rows={3}
                        loader footer={footer} emptyMessage="Sem depoimentos" />
                </div>
            </div>
        );
    }
}
