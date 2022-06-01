package com.labprog.egressos.model.repository;

import java.util.ArrayList;
import java.sql.Date;
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
public class CursoRepositoryTest {
    @Autowired
    CursoRepo cursoRepo;

    @Autowired
    EgressoRepo egressoRepo;

    @Autowired
    CursoEgressoRepo cursoEgressoRepo;

    @Test
    @Transactional
    public void deveSalvarCurso() {
        // cenário
        Curso curso = Curso.builder()
                .nome("curso teste")
                .nivel("nivel teste")
                .build();

        // ação
        Curso retorno = cursoRepo.save(curso);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(curso.getNome(), retorno.getNome());
        Assertions.assertEquals(curso.getNivel(), retorno.getNivel());

        // rollback
        cursoRepo.delete(retorno);
    }

    @Test
    @Transactional
    public void deveAtualizarCurso() {
        // cenário
        Curso curso = Curso.builder()
                .nome("curso teste")
                .nivel("nivel teste")
                .build();

        Curso retorno = cursoRepo.save(curso);

        // ação
        retorno.setNome("teste curso");
        retorno.setNivel("teste nivel");

        Curso salvo = cursoRepo.save(retorno);

        // verificação
        Assertions.assertNotNull(salvo);
        Assertions.assertEquals(retorno.getNome(), salvo.getNome());
        Assertions.assertEquals(retorno.getNivel(), salvo.getNivel());

        // rollback
        cursoRepo.delete(retorno);
    }

    @Test
    @Transactional
    public void deveRemoverCurso() {
        // cenário
        Curso curso = Curso.builder()
                .nome("curso teste")
                .nivel("nivel teste")
                .build();

        Curso retorno = cursoRepo.save(curso);

        // ação
        cursoRepo.deleteById(retorno.getId());

        // verificação
        Optional<Curso> temp = cursoRepo.findById(retorno.getId());
        Assertions.assertTrue(temp.isEmpty());

        // rollback
        cursoRepo.delete(retorno);
    }

    @Test
    @Transactional
    public void deveListarEgressosPorCurso() {
        // cenário
        Date data = new Date(1);

        List<Curso> cursos = new ArrayList<Curso>();
        for (int i = 0; i < 3; i++) {
            cursos.add(
                    Curso.builder()
                            .nome("curso teste" + (i + 1))
                            .nivel("nivel teste" + (i + 1))
                            .build());
        }

        List<Curso> retornoCurso = cursoRepo.saveAll(cursos);

        List<Egresso> egressos = new ArrayList<Egresso>();
        for (int i = 0; i < 3; i++) {
            egressos.add(Egresso.builder()
                    .nome("tuludan" + (i + 1))
                    .email("a@a.com" + (i + 1))
                    .cpf("1234" + (i + 1))
                    .resumo("lorem ipsum lore" + (i + 1))
                    .urlFoto("teste" + (i + 1))
                    .build());
        }

        List<Egresso> retornoEgresso = egressoRepo.saveAll(egressos);

        List<CursoEgressoPK> cursoEgressosPK = new ArrayList<CursoEgressoPK>();
        for (int i = 0; i < 3; i++) {
            cursoEgressosPK.add(new CursoEgressoPK(
                    retornoEgresso.get(i).getId(),
                    retornoCurso.get(i).getId()));

        }

        List<CursoEgresso> cursoEgressos = new ArrayList<CursoEgresso>();
        for (int i = 0; i < 3; i++) {
            cursoEgressos.add(CursoEgresso.builder()
                    .id(cursoEgressosPK.get(i))
                    .curso(retornoCurso.get(i))
                    .egresso(retornoEgresso.get(i))
                    .data_inicio(data)
                    .data_conclusao(data)
                    .build());
        }

        List<CursoEgresso> retornoCursoEgresso = cursoEgressoRepo.saveAll(cursoEgressos);

        // ação
        List<Egresso> retorno = cursoRepo.obterEgressosPorCurso(cursos.get(0).getId());

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(retorno.size(), 1);
        for (Egresso egresso : retorno) {
            Assertions.assertEquals(egresso.getId(), retornoCursoEgresso.get(0).getEgresso().getId());
            Assertions.assertEquals(egresso.getNome(), retornoCursoEgresso.get(0).getEgresso().getNome());
            Assertions.assertEquals(egresso.getCpf(), retornoCursoEgresso.get(0).getEgresso().getCpf());
        }

        // rollback
        cursoEgressoRepo.deleteAll(retornoCursoEgresso);
        cursoRepo.deleteAll(retornoCurso);
        egressoRepo.deleteAll(retornoEgresso);
    }

    @Test
    @Transactional
    public void deveListarQuantidadeDeEgressosPorCurso() {
        // cenário
        Date data = new Date(1);

        List<Curso> cursos = new ArrayList<Curso>();
        for (int i = 0; i < 3; i++) {
            cursos.add(
                    Curso.builder()
                            .nome("curso teste" + (i + 1))
                            .nivel("nivel teste" + (i + 1))
                            .build());
        }

        List<Curso> retornoCurso = cursoRepo.saveAll(cursos);

        List<Egresso> egressos = new ArrayList<Egresso>();
        for (int i = 0; i < 3; i++) {
            egressos.add(Egresso.builder()
                    .nome("tuludan" + (i + 1))
                    .email("a@a.com" + (i + 1))
                    .cpf("1234" + (i + 1))
                    .resumo("lorem ipsum lore" + (i + 1))
                    .urlFoto("teste" + (i + 1))
                    .build());
        }

        List<Egresso> retornoEgresso = egressoRepo.saveAll(egressos);

        List<CursoEgressoPK> cursoEgressosPK = new ArrayList<CursoEgressoPK>();
        for (int i = 0; i < 3; i++) {
            cursoEgressosPK.add(new CursoEgressoPK(
                    retornoEgresso.get(i).getId(),
                    retornoCurso.get(i).getId()));

        }

        List<CursoEgresso> cursoEgressos = new ArrayList<CursoEgresso>();
        for (int i = 0; i < 3; i++) {
            cursoEgressos.add(CursoEgresso.builder()
                    .id(cursoEgressosPK.get(i))
                    .curso(retornoCurso.get(i))
                    .egresso(retornoEgresso.get(i))
                    .data_inicio(data)
                    .data_conclusao(data)
                    .build());
        }

        List<CursoEgresso> retornoCursoEgresso = cursoEgressoRepo.saveAll(cursoEgressos);

        // ação
        int quantidadeEgressosCursoId1 = cursoRepo.obterQuantidadeDeEgressosPorCurso(cursos.get(0).getId());
        int quantidadeEgressosCursoId2 = cursoRepo.obterQuantidadeDeEgressosPorCurso(cursos.get(1).getId());
        int quantidadeEgressosCursoId3 = cursoRepo.obterQuantidadeDeEgressosPorCurso(cursos.get(2).getId());

        // verificação
        Assertions.assertNotNull(quantidadeEgressosCursoId1);
        Assertions.assertEquals(quantidadeEgressosCursoId1, 1);

        Assertions.assertNotNull(quantidadeEgressosCursoId2);
        Assertions.assertEquals(quantidadeEgressosCursoId2, 1);

        Assertions.assertNotNull(quantidadeEgressosCursoId3);
        Assertions.assertEquals(quantidadeEgressosCursoId3, 1);

        // rollback
        cursoEgressoRepo.deleteAll(retornoCursoEgresso);
        cursoRepo.deleteAll(retornoCurso);
        egressoRepo.deleteAll(retornoEgresso);
    }
}
