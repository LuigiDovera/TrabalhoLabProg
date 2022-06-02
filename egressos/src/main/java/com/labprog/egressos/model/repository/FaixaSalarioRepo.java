package com.labprog.egressos.model.repository;



import com.labprog.egressos.model.FaixaSalario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FaixaSalarioRepo 
    extends JpaRepository<FaixaSalario, Long>{
        
        @Query("select COUNT(e) from Egresso e "+
                "join e.profsEgressos pe "+
                "join pe.faixaSalario fs "+
                "where pe.faixaSalario = :faixaSalario")
        int quantidadeEgressoPorFaixaSalario(
            @Param("faixaSalario") FaixaSalario faixaSalario
        );

        @Query("select f from FaixaSalario f where f.descricao = :descricaoFaixaSalario")
        FaixaSalario obterFaixaSalarioPorDescricao(
            @Param("descricaoFaixaSalario") String descricaoFaixaSalario
        );
}
