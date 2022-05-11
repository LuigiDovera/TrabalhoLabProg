package com.labprog.egressos.model.repository;

import com.labprog.egressos.model.ProfEgresso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfEgressoRepo
        extends JpaRepository<ProfEgresso, Long> {
            
            @Query("select p from prof_egresso p where p.empresa = :empresaProfEgresso")
            ProfEgresso obterProfEgressoPorEmpresa(
                @Param("empresaProfEgresso") String empresaProfEgresso
            );
}
