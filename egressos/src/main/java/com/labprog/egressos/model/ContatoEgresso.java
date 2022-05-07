package com.labprog.egressos.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="contato")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContatoEgresso {
    @Id
    @OneToMany(mappedBy = "egresso")
    private Long egresso_id;

    @Id
    @OneToMany(mappedBy = "contato")
    private Long contato_id;
}
