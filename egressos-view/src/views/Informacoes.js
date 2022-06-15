import React from 'react'
import { Chart } from 'primereact/chart';
import styles from './Informacoes.module.css'

class Informacoes extends React.Component {
    constructor(props) {
        super(props);

        this.egressosPorCargo = {
            labels: ['Cargo1', 'Cargo2', 'Cargo3'],
            datasets: [
                {
                    data: [300, 50, 100],
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
        };

        this.egressosPorCurso = {
            labels: ['Curso1', 'Curso2', 'Curso3', 'Curso4', 'Curso5'],
            datasets: [
                {
                    data: [300, 50, 100, 78, 80],
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
        };

        this.egressosPorFaixaSalarial = {
            labels: ['Faixa1', 'Faixa2', 'Faixa3', 'Faixa4', 'Faixa5'],
            datasets: [
                {
                    data: [300, 50, 100, 78, 80],
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
        };

        this.graficoOptions = {
            plugins: {
                legend: {
                    labels: {
                        color: '#000',
                        font: {
                            size:16
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
    }

    render() {
        // quantitativo de egressos por cargo - pizza 
        // quantitativo de egressos por curso - rosca
        // quantitativo de egressos por faixa salarial - barras
        return (
            <div className={styles.cardDiv}>
                <div className={styles.cardGrafico}>
                    <div className="text-900 text-3xl font-medium mb-3">Egressos por cargo</div>
                    <Chart type="pie" data={this.egressosPorCargo} 
                        options={this.graficoOptions} />
                </div>
                <div className={styles.cardGrafico}>
                    <div className="text-900 text-3xl font-medium mb-3">Egressos por curso</div>
                    <Chart type="doughnut" data={this.egressosPorCurso} 
                        options={this.graficoOptions} />
                </div>
                <div className={styles.cardGrafico}>
                    <div className="text-900 text-3xl font-medium mb-3">Egressos por faixa salarial</div>
                    <Chart type="bar" data={this.egressosPorFaixaSalarial} 
                        options={this.graficoBarrasOptions} />
                </div>
            </div>
        )
    }
}

export default Informacoes