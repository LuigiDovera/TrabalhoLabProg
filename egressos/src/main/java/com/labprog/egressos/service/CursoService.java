package com.labprog.egressos.service;

import java.util.List;

import javax.transaction.Transactional;

import com.labprog.egressos.model.Curso;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.repository.CursoRepo;
import com.labprog.egressos.service.exceptions.ServiceRuntimeException;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Egresso> listarEgressosPorCurso(Curso curso) {
        verificarCurso(curso);
        return repo.obterEgressosPorCurso(curso.getId());
    }

    private void verificarId(Curso Curso) {
        if ((Curso == null) || (Curso.getId() == null)) {
            throw new ServiceRuntimeException("ID de Curso inválido");
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
