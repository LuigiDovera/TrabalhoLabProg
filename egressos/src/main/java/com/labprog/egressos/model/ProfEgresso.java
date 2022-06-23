package com.labprog.egressos.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prof_egresso")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfEgresso {
    @Id
    @Column(name = "id_prof_egresso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "egresso_id")
    @JsonBackReference
    private Egresso egresso;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "cargo_id")
    @JsonBackReference
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "faixa_salario_id")
    @JsonBackReference
    private FaixaSalario faixaSalario;

    @Column(name = "empresa")
    private String empresa;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_registro")
    private LocalDate dataRegistro;

    public String toString(){
        return this.id + " " + this.empresa + " " + this.descricao;
    }

}    