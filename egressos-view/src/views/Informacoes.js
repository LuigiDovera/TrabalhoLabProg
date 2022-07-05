import React from 'react'
import { Chart } from 'primereact/chart';
import styles from './Informacoes.module.css'
import CargoService from '../services/CargoService';

class Informacoes extends React.Component {
    constructor(props) {
        super(props);
        this.service = new CargoService();

        this.state = {
            cargos: [],
            egressosPorCargo: {
                labels: [],//['Cargo1', 'Cargo2', 'Cargo3'],
                datasets: [
                    {
                        data: [],//data: [1, 2, 3],
                        backgroundColor: [
                            "#42A5F5",
                            "#66BB6A",
                            "#FFA726"
                        ],
                        hoverBackgroundColor: [
                            "#64B5F6",
                            "#81C784",
                            "#FFB74D"
                        ]
                    }
                ]
            },
            cursos: [],
            egressosPorCurso: {
                labels: ['Curso1', 'Curso2', 'Curso3'],
                datasets: [
                    {
                        data: [1, 2, 3],
                        backgroundColor: [
                            "#42A5F5",
                            "#66BB6A",
                            "#FFA726",
                            "#FF7043",
                            "#E53935"
                        ],
                        hoverBackgroundColor: [
                            "#64B5F6",
                            "#81C784",
                            "#FFB74D",
                            "#FF8A65",
                            "#d15656"
                        ]
                    }
                ]
            },
            faixas: [],
            egressosPorFaixaSalarial: {
                labels: ['Faixa1', 'Faixa2', 'Faixa3'],
                datasets: [
                    {
                        data: [1, 2, 3],
                        backgroundColor: [
                            "#42A5F5",
                            "#66BB6A",
                            "#FFA726",
                            "#FF7043",
                            "#E53935"
                        ],
                        hoverBackgroundColor: [
                            "#64B5F6",
                            "#81C784",
                            "#FFB74D",
                            "#FF8A65",
                            "#d15656"
                        ]
                    }
                ]
            }
        }

        this.graficoOptions = {
            plugins: {
                legend: {
                    labels: {
                        color: '#000',
                        font: {
                            size: 16
                        }
                    }
                }
            }
        };

        this.graficoBarrasOptions = {
            maintainAspectRatio: false,
            aspectRatio: .9,
            plugins: {
                legend: {
                    display: false,
                }
            },
            scales: {
                x: {
                    ticks: {
                        color: '#000'
                    },
                    grid: {
                        color: '#000'
                    }
                },
                y: {
                    ticks: {
                        color: '#000'
                    },
                    grid: {
                        color: '#000'
                    }
                }
            }
        };

        this.carregarEgressosPorCargo = this.carregarEgressosPorCargo.bind(this)
    }

    carregarEgressosPorCargo() {
        this.service.obterCargos()
            .then(response => {
                console.log(response.data)
                console.log(this.state.egressosPorCargo)
                let cargos = []
                for (let i = 0; i < response.data.length; i++) {
                    cargos.push(response.data[i])
                }
                this.setState({ cargos: cargos })
            })
        for (let i = 0; i < this.state.cargos.length; i++) {
            let cargo = this.state.cargos[i]
            this.setState(
                {
                    egressosPorCargo: {
                        labels: this.state.egressosPorCargo.labels.push(cargo.nome),
                    }
                }
            )
            this.service.quantidaDeEgressosPorCargo(cargo)
                .then(response => {
                    this.setState(
                        {
                            egressosPorCargo: {
                                datasets: this.state.egressosPorCargo.datasets[0].data.push(response.data),
                            }
                        }
                    )
                }).catch(erro => {
                    console.log(erro.response)
                })
        }
        console.log(this.state.egressosPorCargo)
    }



    componentDidMount() {
        this.carregarEgressosPorCargo()
    }

    render() {
        return (
            <div className={styles.cardDiv}>
                <div className={styles.cardGrafico}>
                    <div className="text-900 text-3xl font-medium mb-3">Egressos por cargo</div>
                    <Chart type="pie" data={this.state.egressosPorCargo}
                        options={this.graficoOptions} />
                </div>
                <div className={styles.cardGrafico}>
                    <div className="text-900 text-3xl font-medium mb-3">Egressos por curso</div>
                    <Chart type="doughnut" data={this.state.egressosPorCurso}
                        options={this.graficoOptions} />
                </div>
                <div className={styles.cardGrafico}>
                    <div className="text-900 text-3xl font-medium mb-3">Egressos por faixa salarial</div>
                    <Chart type="bar" data={this.state.egressosPorFaixaSalarial}
                        options={this.graficoBarrasOptions} />
                </div>
            </div>
        )
    }
}

export default Informacoes