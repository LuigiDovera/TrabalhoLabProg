import React from 'react'
import { Row, Col } from 'react-bootstrap'
import Botao from '../components/Botao';
import { DataScrollerDepoimentos } from '../components/DataScrollerDepoimentos'
import styles from './Home.module.css'

class Depoimento extends React.Component {
    render() {
        return (
            <div className={styles.mainDiv}>
                <div className={styles.secaoDiv}>
                    <h1 className='titulo mt-3'>Depoimentos</h1>
                    <Row className='text-end'>
                        <Col>
                            <Botao className="mx-2" title="Mais Recentes" icon="pi pi-user" />
                            <Botao className="mx-2" title="Mais Antigos" icon="pi pi-user" />
                        </Col>
                    </Row>
                    <DataScrollerDepoimentos />
                </div>
            </div>
        )
    }
}

export default Depoimento