import React from 'react'
import { CarrosselEgressos } from '../components/CarrosselEgressos'
import { DataScrollerDepoimentos } from '../components/DataScrollerDepoimentos'
import styles from './Home.module.css'
import EgressoService from '../services/EgressoService'

class Home extends React.Component {
    constructor(props) {
        super(props);
        this.egressoServ = new EgressoService();
        this.state = {
            egressos: {},
            depoimentos: {}
        };
        this.carregarEgressos = this.carregarEgressos.bind(this);
    }

    carregarEgressos() {
        this.egressoServ.obterEgressos()
            .then(response => {
                this.setState({ egressos: response.data });
                sessionStorage.setItem('egressos', JSON.stringify(response.data));
                let depoimentos = [];
                response.data.forEach(egresso => {
                    egresso.depoimentos.forEach(depoimento => {
                        depoimentos.push(depoimento);
                    });
                });
                depoimentos.forEach(depoimento => {
                    depoimento.data = depoimento.data.split('-').reverse().join('/');
                });
                for (let i = depoimentos.length - 1; i > 0; i--) {
                    let j = Math.floor(Math.random() * (i + 1));
                    [depoimentos[i], depoimentos[j]] = [depoimentos[j], depoimentos[i]];
                }
                // setState não está funcionando por razão misteriosa
                this.setState({ depoimentos: depoimentos });
                sessionStorage.setItem('depoimentos', JSON.stringify(depoimentos));

            }).catch(error => {
                console.log(error);
            })
    }

    componentDidMount() {
        //console.log(sessionStorage.getItem('token'))
        //console.log(sessionStorage.getItem('egresso'))
        this.carregarEgressos();
        //console.log(this.state.depoimentos)
        //console.log(sessionStorage.getItem('depoimentos'))
    }
    render() {
        return (
            <div>
                <div className={styles.secaoHomeDiv}>
                    <h1 className='titulo mt-3'>Nossos Egressos</h1>
                    <CarrosselEgressos egressos={JSON.parse(sessionStorage.getItem('egressos'))} />
                </div>
                <div className={styles.secaoHomeDiv}>
                    <h1 className='titulo mt-3'>Depoimentos</h1>
                    <DataScrollerDepoimentos depoimentos={JSON.parse(sessionStorage.getItem('depoimentos'))} />
                </div>
            </div>
        )
    }
}

export default Home