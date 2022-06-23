package com.labprog.egressos.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.labprog.egressos.model.Curso;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.repository.CursoRepo;
import com.labprog.egressos.service.exceptions.ServiceRuntimeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
    @Autowired
    private CursoRepo repo;

    @Transactional
    public Curso salvar(Curso curso) {
        verificarCurso(curso);
        return repo.save(curso);
    }

    @Transactional
    public Curso atualizar(Curso curso) {
        verificarCurso(curso);
        verificarId(curso);
        return repo.save(curso);
    }

    @Transactional
    public void remover(Curso curso) {
        verificarId(curso);
        repo.delete(curso);
    }

    @Transactional
    public List<Curso> buscar(Curso filtro) {
        Example<Curso> example = Example.of(filtro, ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING));

        return repo.findAll(example);
    }

    public List<Curso> buscar() {
        return repo.findAll();
    }

    public Optional<Curso> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public List<Curso> listar(Curso filtro) {
        Example<Curso> example =
                Example.of(filtro, ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(StringMatcher.CONTAINING)
                );

        return repo.findAll(example);
    }

    public List<Egresso> listarEgressosPorCurso(Curso curso) {
        verificarId(curso);
        return repo.obterEgressosPorCurso(curso.getId());
    }

    public int listarQuantidadeDeEgressosPorCurso(Curso curso){
        verificarId(curso);
        return repo.obterQuantidadeDeEgressosPorCurso(curso.getId());
    }

    private void verificarId(Curso curso) {
        if ((curso == null) || (curso.getId() == null) || (!repo.existsById(curso.getId()))) {
            throw new ServiceRuntimeException("ID de curso inválido");
        }
    }

    private void verificarCurso(Curso curso) {
        if (curso == null)
            throw new ServiceRuntimeException("Um curso válido deve ser informado");
        if ((curso.getNome() == null) || (curso.getNome().equals("")))
            throw new ServiceRuntimeException("Nome do curso deve ser informado");
        if ((curso.getNivel() == null) || (curso.getNivel().toString().equals("")))
            throw new ServiceRuntimeException("Nível do curso deve ser informada");
    }
}
