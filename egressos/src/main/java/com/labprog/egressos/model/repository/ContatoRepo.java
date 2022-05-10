package com.labprog.egressos.model.repository;

import com.labprog.egressos.model.Contato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContatoRepo
    extends JpaRepository<Contato, Long> {

        @Query("select c from contato c where c.nome")
        Contato obterContatoPorNome(
            @Param("nomeEgresso") String nomeEgresso
        );
        
}
