package com.labprog.egressos.model.repository;

import java.util.List;
import java.util.Optional;

import com.labprog.egressos.model.Contato;
import com.labprog.egressos.model.Egresso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EgressoRepo 
    extends JpaRepository<Egresso, Long>{

        List<Egresso> findByNome(String nome);
    
        Optional<Egresso> findByEmail(String email);
        
        boolean existsByEmail(String email);

        Optional<Egresso> findByCpf(String cpf);

        boolean existsByCpf(String cpf);

}

