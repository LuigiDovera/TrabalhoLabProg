package com.labprog.egressos.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @JsonBackReference
    Curso curso;

    @ManyToOne
    @MapsId("egresso_id")
    @JoinColumn(name = "egresso_id")
    @JsonBackReference
    Egresso egresso;

    @Column(name="data_inicio")
    LocalDate data_inicio;
    
    @Column(name="data_conclusao")
    LocalDate data_conclusao;

}