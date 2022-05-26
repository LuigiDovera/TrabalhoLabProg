package com.labprog.egressos.service;

import java.util.List;
import java.util.Optional;

import com.labprog.egressos.model.Contato;
import com.labprog.egressos.model.CursoEgresso;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.repository.EgressoRepo;
import com.labprog.egressos.service.exceptions.ServiceRuntimeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EgressoService {
    
    @Autowired
    private EgressoRepo repo;

    @Transactional
    public Egresso salvar(Egresso egresso) {
        verificarEgresso(egresso);
        return repo.save(egresso);
    }

    @Transactional
    public List<Egresso> salvar(List<Egresso> egressos) {
        for (Egresso egresso : egressos) {
            verificarEgresso(egresso);
        }
        return repo.saveAll(egressos);
    }

    @Transactional
    public Egresso atualizar(Egresso egresso) {
        verificarEgresso(egresso);
        verificarId(egresso);
        return repo.save(egresso);
    }

    @Transactional
    public List<Egresso> atualizar(List<Egresso> egressos) {
        for (Egresso egresso : egressos) {
            verificarEgresso(egresso);
            verificarId(egresso);
        }
        return repo.saveAll(egressos);
    }

    @Transactional
    public void remover(Egresso egresso) {
        verificarId(egresso);
        repo.delete(egresso);
    }

    @Transactional
    public void remover(List<Egresso> egressos) {
        for (Egresso egresso : egressos) {
            verificarId(egresso);
        }
        repo.deleteAll(egressos);
    }

    public List<Egresso> buscar(Egresso filtro) {
        Example<Egresso> example =
                Example.of(filtro, ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(StringMatcher.CONTAINING)
                );

        return repo.findAll(example);
    }

    /*
    public Optional<Egresso> buscarPorEmail(Egresso egresso) {
        verificarEmail(egresso);
        return repo.findByEmail(egresso.getEmail());
    }
    */

    @Transactional
    public Egresso atualizarContatos(Egresso egresso, List<Contato> contatos) {
        verificarEgresso(egresso);
        verificarId(egresso);
        egresso.setContatos(contatos);
        return repo.save(egresso);
    }

    public List<Contato> buscarContatosEgresso(Egresso egresso) {
        verificarId(egresso);
        return egresso.getContatos();
    }

    @Transactional
    public Egresso atualizarCursos(Egresso egresso, List<CursoEgresso> cursos) {
        verificarEgresso(egresso);
        verificarId(egresso);
        egresso.setCursos_egressos(cursos);
        return repo.save(egresso);
    }

    private void verificarEgresso(Egresso egresso) {
        if (egresso == null)
            throw new ServiceRuntimeException("O egresso está nulo");                
        if ((egresso.getNome() == null) || (egresso.getNome().isEmpty()))
            throw new ServiceRuntimeException("Nome do egresso deve ser informado");    
        if ((egresso.getEmail() == null) || (egresso.getEmail().isEmpty()))
            throw new ServiceRuntimeException("Email do egresso deve ser informado");               
        if ((egresso.getCpf() == null) || (egresso.getCpf().isEmpty()))
            throw new ServiceRuntimeException("CPF do egresso deve ser informado");
    }

    private void verificarId(Egresso egresso) {
        if ((egresso == null) || (!repo.existsById(egresso.getId()))) {
            throw new ServiceRuntimeException("ID de egresso inválido");
        }
    }

    /*
    private void verificarEmail(Egresso egresso) {
        if ((egresso == null) || (egresso.getEmail() == null)) {
            throw new ServiceRuntimeException("Email de egresso inválido");
        }
    }
    */

}
