package com.labprog.egressos.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "cargo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cargo {
    @Id
    @Column(name = "id_cargo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "cargo")
    private List<ProfEgresso> profsEgressos;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;
    
}