package com.labprog.egressos.model;

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
@Table(name="contato_egresso")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContatoEgresso {
    @EmbeddedId
    private ContatoEgressoPK id;

    @ManyToOne
    @JoinColumn(name="egresso_id")
    private Egresso egresso;
    
    @ManyToOne
    @JoinColumn(name="contato_id")
    private Contato contato;
}
