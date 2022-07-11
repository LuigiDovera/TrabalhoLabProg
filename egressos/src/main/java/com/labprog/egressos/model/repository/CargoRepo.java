package com.labprog.egressos.model.repository;

import java.util.List;

import com.labprog.egressos.model.Cargo;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.ProfEgresso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CargoRepo 
    extends JpaRepository<Cargo, Long>{

        public Cargo findByProfsEgressos(ProfEgresso profEgresso);

        @Query("select c from Cargo c "+
                "join c.profsEgressos pe "+
                "join pe.egresso e "+
                "where pe.egresso = :egresso")
        List<Cargo> acharPorEgresso(
            @Param("egresso") Egresso egresso
        );

        @Query("select COUNT(e) from Egresso e "+
                "join e.profsEgressos pe "+
                "join pe.cargo c "+
                "where pe.cargo = :cargo")
        int quantidadeEgressoPorCargo(
            @Param("cargo") Cargo cargo
        );

        @Query("select c from Cargo c where c.nome = :nomeCargo")
        Cargo obterCargoPorNome(
            @Param("nomeCargo") String nomeCargo
        );
}
