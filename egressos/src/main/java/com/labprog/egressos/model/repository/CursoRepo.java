package com.labprog.egressos.model.repository;

import com.labprog.egressos.model.Curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CursoRepo
    extends JpaRepository<Curso, Long> {

        @Query("select c from curso c where c.nome")
        Curso obterCursoPorNome(
            @Param("nomeCurso") String nomeCurso
        );
        
}
