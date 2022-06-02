package com.labprog.egressos.model.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.labprog.egressos.model.Curso;
import com.labprog.egressos.model.CursoEgresso;
import com.labprog.egressos.model.CursoEgressoPK;
import com.labprog.egressos.model.Egresso;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CursoEgressoRepositoryTest {
    @Autowired
    CursoRepo cursoRepo;

    @Autowired
    EgressoRepo egressoRepo;

    @Autowired
    CursoEgressoRepo cursoEgressoRepo;

    @Test
    // @Transactional
    public void deveSalvarCursoDeEgresso() {
        // cenário
        Date data = new Date(1);

        Curso curso = Curso.builder()
                .nome("curso teste")
                .nivel("nivel teste")
                .build();

        Curso retornoCurso = cursoRepo.save(curso);

        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .urlFoto("teste")
                .build();

        Egresso retornoEgresso = egressoRepo.save(egresso);

        CursoEgressoPK idCursoEgresso = new CursoEgressoPK(
                retornoEgresso.getId(),
                retornoCurso.getId());

        CursoEgresso cursoEgresso1 = CursoEgresso.builder()
                .id(idCursoEgresso)
                .curso(retornoCurso)
                .egresso(retornoEgresso)
                .data_inicio(data)
                .data_conclusao(data)
                .build();

        // ação
        CursoEgresso retornoCursoEgresso = cursoEgressoRepo.save(cursoEgresso1);

        // verificação
        Assertions.assertNotNull(retornoCursoEgresso);
        Assertions.assertEquals(cursoEgresso1.getCurso().getNome(), retornoCursoEgresso.getCurso().getNome());
        Assertions.assertEquals(cursoEgresso1.getEgresso().getCpf(), retornoCursoEgresso.getEgresso().getCpf());
        Assertions.assertEquals(cursoEgresso1.getData_inicio().toString(),
                retornoCursoEgresso.getData_inicio().toString());
        Assertions.assertEquals(cursoEgresso1.getData_conclusao().toString(),
                retornoCursoEgresso.getData_conclusao().toString());

        // rollback
        cursoEgressoRepo.delete(cursoEgresso1);
        cursoRepo.delete(curso);
        egressoRepo.delete(egresso);

    }

    @Test
    // @Transactional
    public void deveAtualizarCursoEgresso() {
        // cenário
        Date data = new Date(1);

        Curso curso = Curso.builder()
                .nome("curso teste")
                .nivel("nivel teste")
                .build();

        Curso retornoCurso = cursoRepo.save(curso);

        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .urlFoto("teste")
                .build();

        Egresso retornoEgresso = egressoRepo.save(egresso);

        CursoEgressoPK idCursoEgresso = new CursoEgressoPK(
                retornoEgresso.getId(),
                retornoCurso.getId());

        CursoEgresso cursoEgresso1 = CursoEgresso.builder()
                .id(idCursoEgresso)
                .curso(retornoCurso)
                .egresso(retornoEgresso)
                .data_inicio(data)
                .data_conclusao(data)
                .build();

        // ação
        CursoEgresso retornoCursoEgresso = cursoEgressoRepo.save(cursoEgresso1);
        retornoCursoEgresso.setData_inicio(new Date(0));
        retornoCursoEgresso.setData_conclusao(new Date(0));
        CursoEgresso cursoEgressoAtualizado = cursoEgressoRepo.save(retornoCursoEgresso);

        //// verificação
        Assertions.assertNotNull(retornoCursoEgresso);
        Assertions.assertNotNull(retornoCursoEgresso.getId());
        Assertions.assertEquals(cursoEgresso1.getCurso().getNome(), retornoCursoEgresso.getCurso().getNome());
        Assertions.assertEquals(cursoEgresso1.getEgresso().getCpf(), retornoCursoEgresso.getEgresso().getCpf());
        Assertions.assertEquals(cursoEgresso1.getData_inicio().toString(),
                retornoCursoEgresso.getData_inicio().toString());
        Assertions.assertEquals(cursoEgresso1.getData_conclusao().toString(),
                retornoCursoEgresso.getData_conclusao().toString());

        // rollback
        cursoEgressoRepo.delete(cursoEgressoAtualizado);
        cursoRepo.delete(curso);
        egressoRepo.delete(egresso);
    }

    @Test
    // @Transactional
    public void deveRemoverCursoEgresso() {
        // cenário
        Date data = new Date(1);

        Curso curso = Curso.builder()
                .nome("curso teste")
                .nivel("nivel teste")
                .build();

        Curso retornoCurso = cursoRepo.save(curso);

        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .urlFoto("teste")
                .build();

        Egresso retornoEgresso = egressoRepo.save(egresso);

        CursoEgressoPK idCursoEgresso = CursoEgressoPK.builder()
                .curso_id(retornoCurso.getId())
                .egresso_id(retornoEgresso.getId())
                .build();

        CursoEgresso cursoEgresso1 = CursoEgresso.builder()
                .id(idCursoEgresso)
                .curso(retornoCurso)
                .egresso(retornoEgresso)
                .data_inicio(data)
                .data_conclusao(data)
                .build();

        CursoEgresso retornoCursoEgresso = cursoEgressoRepo.save(cursoEgresso1);

        Assertions.assertNotNull(retornoCursoEgresso);
        Assertions.assertEquals(cursoEgresso1.getCurso().getId(), retornoCursoEgresso.getCurso().getId());
        Assertions.assertEquals(cursoEgresso1.getEgresso().getId(), retornoCursoEgresso.getEgresso().getId());
        Assertions.assertEquals(cursoEgresso1.getData_inicio(), retornoCursoEgresso.getData_inicio());
        Assertions.assertEquals(cursoEgresso1.getData_conclusao(), retornoCursoEgresso.getData_conclusao());

        // ação
        cursoEgressoRepo.delete(retornoCursoEgresso);

        // verificação
        Optional<CursoEgresso> temp = cursoEgressoRepo.findById(retornoCursoEgresso.getId());
        Assertions.assertTrue(temp.isEmpty());

        // rollback
        cursoEgressoRepo.delete(retornoCursoEgresso);
        cursoRepo.delete(curso);
        egressoRepo.delete(egresso);
    }
}
