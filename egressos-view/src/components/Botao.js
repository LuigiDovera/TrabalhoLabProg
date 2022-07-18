import React,{ Component } from "react";
import { Button } from "react-bootstrap";
import styles from './Botao.module.css';

export default class Botao extends Component {
    constructor(props){
        super(props);

        var titulo = this.props.title;
        var icone = this.props.icon;
        var className = this.props.className;
        this.state = { 
            title: titulo,
            icon: icone,
            className: className
        }
    }
    
    render(){
        return(
            <Button 
                className={`${styles.botao} ${this.props.className}`}
                onClick = {this.props.onClick}
                icon = {this.state.icon}
                >
                <span className={styles.titulo}>
                    {this.state.title}
                </span>
            </Button>
        );
    }
};

