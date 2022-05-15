package com.labprog.egressos.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
    @EmbeddedId
    CursoEgressoPK id;

    @ManyToOne
    @MapsId("curso_id")
    @JoinColumn(name = "curso_id")
    Curso curso;

    @ManyToOne
    @MapsId("egresso_id")
    @JoinColumn(name = "egresso_id")
    Egresso egresso;
    
    Date data_inicio;
    Date data_conclusao;
}    