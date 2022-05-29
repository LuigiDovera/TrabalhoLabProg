package com.labprog.egressos.service;

import com.labprog.egressos.model.Depoimento;
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

    private void verificarDepoimento(Depoimento depoimento) {
        if (depoimento == null)
            throw new ServiceRuntimeException("O depoimento está nulo");                
        if ((depoimento.getTexto() == null) || (depoimento.getTexto().equals("")))
            throw new ServiceRuntimeException("Texto do depoimento deve ser informado");                   
    }

    private void verificarId(Depoimento depoimento) {
        if ((depoimento == null) || (depoimento.getId() == null)) {
            throw new ServiceRuntimeException("ID de depoimento inválido");
        }
    }
}
