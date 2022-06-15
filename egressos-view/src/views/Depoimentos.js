import React from 'react'
import { Row, Col } from 'react-bootstrap'
import { Button } from 'primereact/button'
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
                            <Button className='text-end mx-2'>Mais Recentes</Button>
                            <Button className='text-end mx-2'>Mais Antigos</Button>
                        </Col>
                    </Row>
                    <DataScrollerDepoimentos />
                </div>
            </div>
        )
    }
}

export default Depoimento