package com.labprog.egressos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "curso_egresso")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoEgresso {
    @Id
    @ManyToOne
    @JoinColumn(name = "egresso_id")
    private Egresso egresso;

    @Id
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Column(name = "data_inicio")
    private Data data_inicio;

    @Column(name = "data_conclusao")
    private Data data_conclusao;
}    