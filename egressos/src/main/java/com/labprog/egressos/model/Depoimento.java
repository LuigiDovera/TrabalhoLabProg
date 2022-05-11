package com.labprog.egressos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "depoimento")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Depoimento {
    @Id
    @Column(name = "id_depoimento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "egresso_id")
    private Egresso egresso;

    @Column(name = "texto")
    private String texto;

    @Column(name = "data")
    private Data data;
}
