package com.labprog.egressos.service;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.labprog.egressos.model.Curso;
import com.labprog.egressos.model.CursoEgresso;
import com.labprog.egressos.model.CursoEgressoPK;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.repository.EgressoRepo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CursoServiceTest<cursoEgressoService> {
    @Autowired
    CursoService _sut;

    @Autowired
    EgressoService egressoService;

    @Autowired
    CursoEgressoService cursoEgressoService;

    @Test
    @Transactional
    public void deveSalvarCurso() {
        // cenário
        Curso curso = Curso.builder()
                .nome("curso teste")
                .nivel("nivel teste")
                .build();

        // ação
        Curso retorno = _sut.salvar(curso);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(curso.getNome(), retorno.getNome());
        Assertions.assertEquals(curso.getNivel(), retorno.getNivel());

        // rollback
        _sut.remover(retorno);
    }

    @Test
    @Transactional
    public void deveAtualizarCurso() {
        // cenário
        Curso curso = Curso.builder()
                .nome("curso teste")
                .nivel("nivel teste")
                .build();

        Curso retorno = _sut.salvar(curso);

        // ação
        retorno.setNome("teste curso");
        retorno.setNivel("teste nivel");

        Curso salvo = _sut.salvar(retorno);

        // verificação
        Assertions.assertNotNull(salvo);
        Assertions.assertEquals(retorno.getNome(), salvo.getNome());
        Assertions.assertEquals(retorno.getNivel(), salvo.getNivel());

        // rollback
        _sut.remover(retorno);
    }

    @Test
    @Transactional
    public void deveRemoverCurso() {
        // cenário
        Curso curso = Curso.builder()
                .nome("curso teste")
                .nivel("nivel teste")
                .build();

        Curso retorno = _sut.salvar(curso);

        // ação
        _sut.remover(retorno);
        List<Curso> temp = _sut.listar(retorno);

        // verificação
        Assertions.assertTrue(temp.isEmpty());
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

        List<Curso> retornoCurso = new ArrayList<Curso>();

        for (Curso curso : cursos) {
            retornoCurso.add(_sut.salvar(curso));
        }

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

        List<Egresso> retornoEgresso = new ArrayList<Egresso>();

        for (Egresso egresso : egressos) {
            retornoEgresso.add(egressoService.salvar(egresso));
        }

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

        List<CursoEgresso> retornoCursoEgresso = new ArrayList<CursoEgresso>();

        for (CursoEgresso cursoEgresso : cursoEgressos) {
            retornoCursoEgresso.add(cursoEgressoService.salvar(cursoEgresso));
        }

        // ação
        List<Egresso> retorno = _sut.listarEgressosPorCurso(cursos.get(0));

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(retorno.size(), 1);
        for (Egresso egresso : retorno) {
            Assertions.assertEquals(egresso.getId(), retornoCursoEgresso.get(0).getEgresso().getId());
            Assertions.assertEquals(egresso.getNome(), retornoCursoEgresso.get(0).getEgresso().getNome());
            Assertions.assertEquals(egresso.getCpf(), retornoCursoEgresso.get(0).getEgresso().getCpf());
        }

        // rollback
        for (CursoEgresso cursoEgresso : retornoCursoEgresso) {
            cursoEgressoService.remover(cursoEgresso);
        }

        for (Curso curso : retornoCurso) {
            _sut.remover(curso);
        }

        for (Egresso egresso : retornoEgresso) {
            egressoService.remover(egresso);
        }
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

        List<Curso> retornoCurso = new ArrayList<Curso>();

        for (Curso curso : cursos) {
            retornoCurso.add(_sut.salvar(curso));
        }

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

        List<Egresso> retornoEgresso = new ArrayList<Egresso>();

        for (Egresso egresso : egressos) {
            retornoEgresso.add(egressoService.salvar(egresso));
        }

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

        List<CursoEgresso> retornoCursoEgresso = new ArrayList<CursoEgresso>();

        for (CursoEgresso cursoEgresso : cursoEgressos) {
            retornoCursoEgresso.add(cursoEgressoService.salvar(cursoEgresso));
        }

        // ação
        int quantidadeEgressosCursoId1 = _sut.listarQuantidadeDeEgressosPorCurso(cursos.get(0));
        int quantidadeEgressosCursoId2 = _sut.listarQuantidadeDeEgressosPorCurso(cursos.get(1));
        int quantidadeEgressosCursoId3 = _sut.listarQuantidadeDeEgressosPorCurso(cursos.get(2));

        // verificação
        Assertions.assertNotNull(quantidadeEgressosCursoId1);
        Assertions.assertEquals(quantidadeEgressosCursoId1, 1);

        Assertions.assertNotNull(quantidadeEgressosCursoId2);
        Assertions.assertEquals(quantidadeEgressosCursoId2, 1);

        Assertions.assertNotNull(quantidadeEgressosCursoId3);
        Assertions.assertEquals(quantidadeEgressosCursoId3, 1);

        // rollback
        for (CursoEgresso cursoEgresso : retornoCursoEgresso) {
            cursoEgressoService.remover(cursoEgresso);
        }

        for (Curso curso : retornoCurso) {
            _sut.remover(curso);
        }

        for (Egresso egresso : retornoEgresso) {
            egressoService.remover(egresso);
        }
    }
}
