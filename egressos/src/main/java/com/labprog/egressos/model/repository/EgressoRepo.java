package com.labprog.egressos.model.repository;

import com.labprog.egressos.model.Egresso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EgressoRepo 
    extends JpaRepository<Egresso, Long>{
    
        Egresso findByEmail(String email);
        boolean existsByEmail(String email);

        @Query("select e from egresso e where e.nome:=nomeEgresso")
        Egresso obterEgressoPorNome(
            @Param("nomeEgresso") String nomeEgresso
        );

}

