package com.labprog.egressos.service;

import java.util.List;

import javax.transaction.Transactional;

import com.labprog.egressos.model.Curso;
import com.labprog.egressos.model.CursoEgresso;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.repository.CursoEgressoRepo;
import com.labprog.egressos.model.repository.CursoRepo;
import com.labprog.egressos.service.exceptions.ServiceRuntimeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoEgressoService {
    @Autowired
    private CursoEgressoRepo repo;

    @Transactional
    public CursoEgresso salvar(CursoEgresso cursoEgresso) {
        verificarCursoEgresso(cursoEgresso);
        return repo.save(cursoEgresso);
    }

    @Transactional
    public CursoEgresso atualizar(CursoEgresso cursoEgresso) {
        verificarCursoEgresso(cursoEgresso);
        verificarId(cursoEgresso);
        return repo.save(cursoEgresso);
    }

    @Transactional
    public void remover(CursoEgresso cursoEgresso) {
        verificarId(cursoEgresso);
        repo.delete(cursoEgresso);
    }

    private void verificarId(CursoEgresso cursoEgresso) {
        if ((cursoEgresso == null) || (cursoEgresso.getId() == null)) {
            throw new ServiceRuntimeException("ID de curso egresso inválido");
        }
    }

    private void verificarCursoEgresso(CursoEgresso cursoEgresso) {
        if (cursoEgresso == null)
            throw new ServiceRuntimeException("Um curso válido deve ser informado");
        if ((cursoEgresso.getData_inicio() == null) || (cursoEgresso.getData_inicio().equals("")))
            throw new ServiceRuntimeException("Data de início no curso deve ser informado");
        if ((cursoEgresso.getData_conclusao() == null) || (cursoEgresso.getData_conclusao().toString().equals("")))
            throw new ServiceRuntimeException("Data de conclusão do curso deve ser informada");
    }
}
