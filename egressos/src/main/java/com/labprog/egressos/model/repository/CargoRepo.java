package com.labprog.egressos.model.repository;

import java.util.List;

import com.labprog.egressos.model.Cargo;
import com.labprog.egressos.model.Egresso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CargoRepo 
    extends JpaRepository<Cargo, Long>{

        @Query("select c from Cargo c "+
                "join c.profsEgressos pe "+
                "join pe.egresso e "+
                "where pe.egresso = :egresso")
        List<Cargo> acharPorEgresso(
            @Param("egresso") Egresso egresso
        );

        @Query("select c from Cargo c where c.nome = :nomeCargo")
        Cargo obterCargoPorNome(
            @Param("nomeCargo") String nomeCargo
        );
}
