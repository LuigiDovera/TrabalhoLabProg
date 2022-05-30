package com.labprog.egressos.service;

import java.util.List;

import com.labprog.egressos.model.Contato;
import com.labprog.egressos.model.repository.ContatoRepo;
import com.labprog.egressos.service.exceptions.ServiceRuntimeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContatoService {
    
    @Autowired 
    private ContatoRepo repo;

    @Transactional
    public Contato salvar(Contato contato) {
        verificarContato(contato);
        return repo.save(contato);
    }

    @Transactional
    public Contato atualizar(Contato contato) {
        verificarContato(contato);
        verificarId(contato);
        return repo.save(contato);
    }

    @Transactional
    public void remover(Contato contato) {
        verificarId(contato);
        repo.delete(contato);
    }

    public List<Contato> buscar(Contato filtro) {
        Example<Contato> example =
                Example.of(filtro, ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(StringMatcher.CONTAINING)
                );

        return repo.findAll(example);
    }

    private void verificarContato(Contato contato) {
        if (contato == null)
            throw new ServiceRuntimeException("O contato está nulo");                
        if ((contato.getNome() == null) || (contato.getNome().equals("")))
            throw new ServiceRuntimeException("Nome do contato deve ser informado");                   
    }

    private void verificarId(Contato contato) {
        if ((contato == null) || (contato.getId() == null)) {
            throw new ServiceRuntimeException("ID de contato inválido");
        }
    }

}
