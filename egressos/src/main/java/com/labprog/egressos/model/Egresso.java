package com.labprog.egressos.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="egresso")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Egresso {
    @Id
    @Column(name = "id_egresso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "resumo")
    private String resumo;

    @Column(name = "urlFoto")
    private String urlFoto;

    @OneToMany(mappedBy = "egresso", fetch=FetchType.EAGER)
    private List<ProfEgresso> profsEgressos;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "contato_egresso", 
        joinColumns = @JoinColumn(name = "egresso_id"), 
        inverseJoinColumns = @JoinColumn(name = "contato_id"))
    private List<Contato> contatos;
    
    @OneToMany(mappedBy = "egresso")
    List<CursoEgresso> cursos_egressos;

    @OneToMany(mappedBy = "egresso")
    private List<Depoimento> depoimentos;


}
