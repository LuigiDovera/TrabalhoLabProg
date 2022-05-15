package com.labprog.egressos.model.repository;



import com.labprog.egressos.model.FaixaSalario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FaixaSalarioRepo 
    extends JpaRepository<FaixaSalario, Long>{
    
        @Query("select f from FaixaSalario f where f.descricao = :descricaoFaixaSalario")
        FaixaSalario obterFaixaSalarioPorDescricao(
            @Param("descricaoFaixaSalario") String descricaoFaixaSalario
        );
}
