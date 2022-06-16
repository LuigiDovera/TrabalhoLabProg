package com.labprog.egressos.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.labprog.egressos.model.Depoimento;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.repository.DepoimentoRepo;
import com.labprog.egressos.service.exceptions.ServiceRuntimeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepoimentoService {
    @Autowired
    private DepoimentoRepo repo;

    @Transactional
    public Depoimento salvar(Depoimento depoimento) {
        verificarDepoimento(depoimento);
        depoimento.setData(LocalDate.now());
        return repo.save(depoimento);
    }

    @Transactional
    public Depoimento atualizar(Depoimento depoimento) {
        verificarDepoimento(depoimento);
        verificarId(depoimento);
        return repo.save(depoimento);
    }

    @Transactional
    public void remover(Depoimento depoimento) {
        verificarId(depoimento);
        repo.delete(depoimento);
    }

    @Transactional
    public List<Depoimento> buscar(Depoimento filtro) {
        Example<Depoimento> example = Example.of(filtro, ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING));

        return repo.findAll(example);
    }

    // @Transactional
    // public Optional<Depoimento> buscar(Depoimento depoimento) {
    // verificarId(depoimento);
    // return repo.findById(depoimento.getId());
    // }

    @Transactional
    public List<Depoimento> buscar() {
        return repo.findAll();
    }

    public List<Depoimento> listar(Depoimento filtro) {
        Example<Depoimento> example = Example.of(filtro, ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING));

        return repo.findAll(example);
    }

    public List<Depoimento> listarDepoimentosOrdenadosPeloMaisRecente() {
        return repo.obterDepoimentosOrdenadosPeloMaisRecente();
    }

    public List<Depoimento> obterDepoimentosPorEgresso(Egresso egresso) {
        return repo.obterDepoimentosPorEgresso(egresso);
    }

    private void verificarId(Depoimento depoimento) {
        if ((depoimento == null) || (depoimento.getId() == null) || (!repo.existsById(depoimento.getId()))) {
            throw new ServiceRuntimeException("ID de depoimento inválido");
        }
    }

    private void verificarDepoimento(Depoimento depoimento) {
        if (depoimento == null)
            throw new ServiceRuntimeException("Um depoimento válido deve ser informado");
        if ((depoimento.getTexto() == null) || (depoimento.getTexto().equals("")))
            throw new ServiceRuntimeException("Texto do depoimento deve ser informado");
        if ((depoimento.getData() == null) || (depoimento.getData().toString().equals("")))
            throw new ServiceRuntimeException("Data do depoimento deve ser informada");
        if ((depoimento.getEgresso() == null) || (depoimento.getEgresso().getCpf().toString().equals("")))
            throw new ServiceRuntimeException("Um egresso válido deve ser informado");
    }
}