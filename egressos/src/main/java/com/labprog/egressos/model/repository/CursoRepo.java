package com.labprog.egressos.model.repository;

import java.util.List;
import java.util.Map;

import com.labprog.egressos.model.Curso;
import com.labprog.egressos.model.CursoEgresso;
import com.labprog.egressos.model.Egresso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CursoRepo
    extends JpaRepository<Curso, Long> {

        @Query("select c from Curso c where c.nome = :nomeCurso")
        Curso obterCursoPorNome(
            @Param("nomeCurso") String nomeCurso
        );


        @Query("select e from Egresso e JOIN e.egressoCursos c WHERE c.curso.id = :cursoId")
        List<Egresso> obterEgressosPorCurso(
            @Param("cursoId") Long cursoId
        );

        @Query("select COUNT(e) from Egresso e JOIN e.egressoCursos c WHERE c.curso.id = :cursoId")
        int obterQuantidadeDeEgressosPorCurso(
            @Param("cursoId") Long cursoId
        );
        
}
