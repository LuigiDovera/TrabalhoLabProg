import React from 'react'
import { Chart } from 'primereact/chart';
import styles from './Informacoes.module.css'
import CargoService from '../services/CargoService';
import CursoService from '../services/CursoService';

class Informacoes extends React.Component {
    constructor(props) {
        super(props);
        this.cargoService = new CargoService();
        this.cursoService = new CursoService();

        this.state = {
            egressosPorCargo: {},
            egressosPorCurso: {},
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
        this.carregarEgressosPorCurso = this.carregarEgressosPorCurso.bind(this)
    }

    carregarEgressosPorCargo() {
        this.cargoService.obterCargos()
            .then(response => {
                console.log(response.data)
                let idsCargos = []
                let rotulos = []
                for (let i = 0; i < response.data.length; i++) {
                    idsCargos.push(response.data[i].id)
                    rotulos.push(response.data[i].nome)
                }
                let requests = []
                for (let i = 0; i < idsCargos.length; i++) {
                    let id = idsCargos[i]
                    requests.push(this.cargoService.quantidaDeEgressosPorCargo(id))
                }
                Promise.all(requests)
                    .then(response => {
                        let data = []
                        for (let i = 0; i < response.length; i++) {
                            data.push(response[i].data)
                        }
                        // Remover
                        if (data.length === 0) {
                            data = [1,2,3]
                            rotulos = ["Cargo1", "Cargo2", "Cargo3"]
                        }
                        this.setState({
                            egressosPorCargo: {
                                labels: rotulos,
                                datasets: [
                                    {
                                        data: data,
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
                            }
                        })
                    })
            }).catch(error => {
                console.log(error)
                let data = [1,2,3]
                let rotulos = ["Cargo1", "Cargo2", "Cargo3"]
                this.setState({
                    egressosPorCargo: {
                        labels: rotulos,
                        datasets: [
                            {
                                data: data,
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
                })
            })
    }

    carregarEgressosPorCurso() {
        this.cursoService.obterCursos()
            .then(response => {
                console.log(response.data)
                let idsCursos = []
                let rotulos = []
                for (let i = 0; i < response.data.length; i++) {
                    idsCursos.push(response.data[i].id)
                    rotulos.push(response.data[i].nome)
                }
                let requests = []
                for (let i = 0; i < idsCursos.length; i++) {
                    let id = idsCursos[i]
                    requests.push(this.cursoService.quantidaDeEgressosPorCurso(id))
                }
                Promise.all(requests)
                    .then(response => {
                        let data = []
                        for (let i = 0; i < response.length; i++) {
                            data.push(response[i].data)
                        }
                        // Remover
                        if (data.length === 0) {
                            data = [1,2,3]
                            rotulos = ["Curso1", "Curso2", "Curso3"]
                        }
                        this.setState({
                            egressosPorCurso: {
                                labels: rotulos,
                                datasets: [
                                    {
                                        data: data,
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
                        })
                    })
            }).catch(error => {
                console.log(error)
                let data = [1,2,3]
                let rotulos = ["Curso1", "Curso2", "Curso3"]
                this.setState({
                    egressosPorCurso: {
                        labels: rotulos,
                        datasets: [
                            {
                                data: data,
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
                })
            })
    }

    componentDidMount() {
        this.carregarEgressosPorCargo()
        this.carregarEgressosPorCurso()
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