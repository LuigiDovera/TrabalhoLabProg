package com.labprog.egressos.model.repository;

import java.util.List;

import com.labprog.egressos.model.Contato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContatoRepo
    extends JpaRepository<Contato, Long> {

        List<Contato> findByNome(String nome);

        List<Contato> findByUrlLogo(String urlLogo);
        
}
