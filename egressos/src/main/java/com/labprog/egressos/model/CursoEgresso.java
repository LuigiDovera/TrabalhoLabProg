package com.labprog.egressos.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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

    public CursoEgressoPK getId() {
        if (id == null) {
            if (curso == null && egresso == null) {
                id = null;
            } else if (curso.getId() == null && egresso.getId() == null) {
                id = null;
            } else {
                var builder = CursoEgressoPK.builder();

                if (curso != null) {
                    if (curso.getId() != null)
                        builder = builder.curso_id(curso.getId());
                }
                if (egresso != null) {
                    if (egresso.getId() != null)
                        builder = builder.egresso_id(egresso.getId());
                }
                id = builder.build();
            }
        }
        return id;
    }

    @ManyToOne
    @MapsId("curso_id")
    @JoinColumn(name = "curso_id")
    Curso curso;
    public void setCurso(Curso curso) {
        if (id == null)
            id = CursoEgressoPK.builder().curso_id(curso.getId()).build();
        else
            id.setEgresso_id(curso.getId());
    }

    @ManyToOne
    @MapsId("egresso_id")
    @JoinColumn(name = "egresso_id")
    Egresso egresso;
    public void setEgresso(Egresso egresso) {
        if (id == null)
            id = CursoEgressoPK.builder().egresso_id(egresso.getId()).build();
        else
            id.setEgresso_id(egresso.getId());
    }

    @Column(name="data_inicio")
    Date data_inicio;
    
    @Column(name="data_conclusao")
    Date data_conclusao;

}