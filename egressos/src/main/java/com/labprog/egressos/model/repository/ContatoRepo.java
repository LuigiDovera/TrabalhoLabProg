package com.labprog.egressos.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labprog.egressos.model.Contato;
import com.labprog.egressos.model.Egresso;

public interface ContatoRepo
    extends JpaRepository<Contato, Long> {

        List<Contato> findByNome(String nome);

        List<Contato> findByUrlLogo(String urlLogo);

        List<Contato> findByEgressos(Egresso egresso);
        
}
