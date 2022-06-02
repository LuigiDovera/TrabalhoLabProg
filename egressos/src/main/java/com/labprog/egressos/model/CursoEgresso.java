package com.labprog.egressos.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "curso_egresso")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoEgresso{
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

    @Column(name="data_inicio")
    Date data_inicio;
    
    @Column(name="data_conclusao")
    Date data_conclusao;

}