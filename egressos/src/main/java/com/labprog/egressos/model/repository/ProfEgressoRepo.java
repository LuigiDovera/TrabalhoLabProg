package com.labprog.egressos.model.repository;

import java.util.List;

import com.labprog.egressos.model.ProfEgresso;
import com.labprog.egressos.model.Cargo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfEgressoRepo
        extends JpaRepository<ProfEgresso, Long> {
            
            public List<ProfEgresso> findByCargo(Cargo cargo);

            @Query("select p from ProfEgresso p where p.empresa = :empresaProfEgresso")
            ProfEgresso obterProfEgressoPorEmpresa(
                @Param("empresaProfEgresso") String empresaProfEgresso
            );
}
