package com.labprog.egressos.model.repository;

import java.util.List;

import com.labprog.egressos.model.Depoimento;
import com.labprog.egressos.model.Egresso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepoimentoRepo
    extends JpaRepository<Depoimento, Long> {

        
        List<Depoimento> findByTexto(String texto);

        @Query("select d from Depoimento d order by d.data desc")
        List<Depoimento> obterDepoimentosOrdenadosPeloMaisRecente();

        
        @Query("select d from Depoimento d where d.egresso = :egresso")
        List<Depoimento> obterDepoimentosPorEgresso(
            @Param("egresso") Egresso egresso
        );
        
}
