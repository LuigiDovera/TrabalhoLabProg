package com.labprog.egressos.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @Column(name = "senha")
    private String senha;

    @OneToMany(mappedBy = "egresso", fetch=FetchType.LAZY)
    private List<ProfEgresso> profsEgressos;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "contato_egresso", 
        joinColumns = @JoinColumn(name = "egresso_id"), 
        inverseJoinColumns = @JoinColumn(name = "contato_id"))
    @JsonIgnore
    private List<Contato> contatos;
    
    @OneToMany(mappedBy = "egresso", cascade = CascadeType.ALL)
    @JsonIgnore
    List<CursoEgresso> egressoCursos;

    @OneToMany(mappedBy = "egresso", fetch = FetchType.LAZY)
    @Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
    private List<Depoimento> depoimentos;


}
