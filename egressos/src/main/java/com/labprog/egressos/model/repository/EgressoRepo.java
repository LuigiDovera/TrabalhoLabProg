package com.labprog.egressos.model.repository;

import java.util.List;

import com.labprog.egressos.model.Egresso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EgressoRepo 
    extends JpaRepository<Egresso, Long>{

        List<Egresso> findByNome(String nome);
    
        Egresso findByEmail(String email);
        
        boolean existsByEmail(String email);

        Egresso findByCpf(String cpf);

        @Query("select e from Egresso e where e.nome = :nomeEgresso")
        Egresso obterEgressoPorNome(
            @Param("nomeEgresso") String nomeEgresso
        );

}

