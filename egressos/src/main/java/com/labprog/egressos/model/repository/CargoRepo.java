package com.labprog.egressos.model.repository;

import com.labprog.egressos.model.Cargo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CargoRepo 
    extends JpaRepository<Cargo, Long>{
    
        @Query("select c from cargo c where c.nome = :nomeCargo")
        Cargo obterCargoPorNome(
            @Param("nomeCargo") String nomeCargo
        );
}
