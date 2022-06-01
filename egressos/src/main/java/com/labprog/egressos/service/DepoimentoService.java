package com.labprog.egressos.service;

import java.util.List;

import com.labprog.egressos.model.Depoimento;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.repository.DepoimentoRepo;
import com.labprog.egressos.service.exceptions.ServiceRuntimeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepoimentoService {
    @Autowired
    private DepoimentoRepo repo;

    @Transactional
    public Depoimento salvar(Depoimento depoimento) {
        verificarDepoimento(depoimento);
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

    public List<Depoimento> listarDepoimentosOrdenadosPeloMaisRecente() {
        return repo.obterDepoimentosOrdenadosPeloMaisRecente();
    }

    public List<Depoimento> obterDepoimentosPorEgresso(Egresso egresso) {
        // verificar egresso
        return repo.obterDepoimentosPorEgresso(egresso);
    }

    private void verificarId(Depoimento depoimento) {
        if ((depoimento == null) || (depoimento.getId() == null)) {
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